package fr.eni.ludotheque.rest;

import fr.eni.ludotheque.bll.ExemplaireService;
import fr.eni.ludotheque.bll.LocationService;
import fr.eni.ludotheque.bo.Exemplaire;
import fr.eni.ludotheque.bo.Location;
import fr.eni.ludotheque.dto.LocationDTO;
import fr.eni.ludotheque.exceptions.DataNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/locations")
public class LocationRestController {

    private final LocationService locationService;
    private final ExemplaireService exemplaireService;

    LocationRestController(LocationService locationService, ExemplaireService exemplaireService) {
        this.locationService = locationService;
        this.exemplaireService = exemplaireService;
    }

    // S3027 - API REST pour enregistrer une nouvelle location depuis le scan du codebarre exemplaire
    @PostMapping
    public ResponseEntity<ApiResponse<Location>> ajouterLocationDepuisCodeBarre(@RequestBody LocationDTO locationDto) {
        Location location = null;
        try {
            // on tente d'ajouter une location
            location = locationService.ajouterLocation(locationDto);
        } catch(DataNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, e.getMessage(), null));
        }

        return ResponseEntity.ok(new ApiResponse<>(true, "Nouvelle location créé", location));
    }
}
