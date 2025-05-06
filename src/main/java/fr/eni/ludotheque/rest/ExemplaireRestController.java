package fr.eni.ludotheque.rest;

import fr.eni.ludotheque.bll.ExemplaireService;
import fr.eni.ludotheque.bo.Exemplaire;
import fr.eni.ludotheque.bo.Jeu;
import fr.eni.ludotheque.dal.ExemplaireRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/exemplaires")
public class ExemplaireRestController {

    private final ExemplaireService exemplaireService;
    ExemplaireRestController(ExemplaireService repo) {
        this.exemplaireService = repo;
    }

    // S3026 - recherche des informations d'un exemplaire connaissant le codebarre (codebarre scanné par l'employé)
    // 2 Tests : cas positif : le codebarre est connu / cas négatif : le codebarre est inconnu
    @GetMapping
    public ResponseEntity<ApiResponse<Exemplaire>> getExemplaireByCodeBarre(@RequestParam String codebarre) {
        Optional<Exemplaire> exemplaire = exemplaireService.getExemplaireByCodeBarre(codebarre);
        return exemplaire.map(value -> ResponseEntity.ok(new ApiResponse<>(true, "Informations de l'exemplaire", value))).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
