package fr.eni.ludotheque.rest;

import fr.eni.ludotheque.bll.ClientService;
import fr.eni.ludotheque.bo.Adresse;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dto.AdresseDTO;
import fr.eni.ludotheque.dto.ClientDTO;
import fr.eni.ludotheque.exceptions.DataNotFound;
import fr.eni.ludotheque.exceptions.EmailClientAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientRestController {

    private final ClientService clientService;

    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Client>> addClient(@RequestBody ClientDTO client) {
        Client clientNew = new Client();
        try {
            clientNew = clientService.ajouterClient(client);
        } catch(EmailClientAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse<>(false, e.getMessage(), null));
        }
        return ResponseEntity.ok(new ApiResponse<>(true, "Client ajouté", clientNew));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Client>> deleteClient(@PathVariable(name="id") Integer id) {
        try {
            clientService.supprimerClient(id);
            return ResponseEntity.ok(new ApiResponse<>(true, " Client supprimé avec succès", null));
        } catch(DataNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Client>> modifyClient(@PathVariable(name="id") Integer id, @RequestBody ClientDTO client) {
        Client clientUp = new Client();
        try {
            clientUp = clientService.modifierClient(id, client);
            return ResponseEntity.ok(new ApiResponse<>(true, "Client entièrement modifié avec succès", clientUp));
        } catch(DataNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @PatchMapping("/{id}/adresse")
    // S3022 - API REST pour modifier uniquement l'adresse du client
    public ResponseEntity<ApiResponse<Client>> patchClient(@PathVariable(name="id") Integer id, @RequestBody AdresseDTO adresse) {
        try {
            Client clientUp = clientService.modifierAdresse(id, adresse);
            return ResponseEntity.ok(new ApiResponse<>(true, "Adresse du client modifiée avec succès", clientUp));
        } catch(DataNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @GetMapping
    // S3023 - API REST pour trouver les clients dont le nom commence par la chaine fournie
    public ResponseEntity<ApiResponse<List<Client>>> getClientsByName(@RequestParam String nom) {
        List<Client> clients = clientService.trouverClientsParNom(nom);
        return ResponseEntity.ok(new ApiResponse<>(true, "Liste des clients correspondants", clients));
    }

    @GetMapping("/{id}")
    // S3024 - API REST pour trouver un client par id
    public ResponseEntity<ApiResponse<Client>> getClientById(@PathVariable Integer id) {
        try {
            Client client = clientService.trouverClientParId(id);
            return ResponseEntity.ok(new ApiResponse<>(true, "Client trouvé", client));
        } catch(DataNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    // S3025 - API REST pour trouver les jeux et pour chaque jeux le nombre d'exemplaires disponible
    //( c'est dire louable et non loué au moment de la recherche )

}
