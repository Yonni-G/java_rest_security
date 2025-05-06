package fr.eni.ludotheque.rest;

import fr.eni.ludotheque.bll.ClientService;
import fr.eni.ludotheque.bll.JeuService;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.bo.Jeu;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jeux")
public class JeuRestController {
    private final JeuService jeuService;

    public JeuRestController(JeuService jeuService) {
        this.jeuService = jeuService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Jeu>>> getlisteJeuxCatalogue(@RequestParam String titre) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Liste des jeux disponibles", jeuService.listeJeuxCatalogue(titre)));
    }

}
