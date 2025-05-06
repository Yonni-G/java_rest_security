package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.bo.Exemplaire;
import fr.eni.ludotheque.bo.Facture;
import fr.eni.ludotheque.bo.Location;
import fr.eni.ludotheque.dal.*;
import fr.eni.ludotheque.dto.LocationDTO;
import fr.eni.ludotheque.exceptions.DataNotFound;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService{
	@NonNull
	final private LocationRepository locationRepository;

	@NonNull
	final private JeuRepository jeuRepository;

	@NonNull
	final private ExemplaireRepository exemplaireRepository;

	@NonNull
	final private FactureRepository factureRepository;

	@NonNull
	final private ClientRepository clientRepository;


	@Override
	public Location ajouterLocation(LocationDTO locationDto) {
		Exemplaire exemplaire = exemplaireRepository.findByCodebarre(locationDto.getCodebarre());
		if (exemplaire == null) {
			throw new DataNotFound("Exemplaire", locationDto.getCodebarre());
		}

		Client client = clientRepository.findById(locationDto.getNoClient())
				.orElseThrow(() -> new DataNotFound("Client", locationDto.getNoClient()));

		if (exemplaire.getJeu() == null) {
			throw new DataNotFound("Jeu", "lié à l'exemplaire " + locationDto.getCodebarre());
		}

		Float tarifJour = jeuRepository.findTarifJour(exemplaire.getJeu().getNoJeu());

		Location location = new Location(LocalDateTime.now(), client, exemplaire);
		location.setTarifJour(tarifJour);

		return locationRepository.save(location);
	}


	@Override
	@Transactional
	public Facture retourExemplaires(List<String> codebarres) {
		Facture facture = new Facture();
		//facture
		Location location = null;
		float prix = 0;
		for(String codebarre : codebarres) {
			location = locationRepository.findLocationByCodebarreWithJeu(codebarre);
			location.setDateRetour(LocalDateTime.now());
			// il faut sauver la location avec sa date de retour
			locationRepository.save(location);

			facture.addLocation(location);

			long nbJours = ChronoUnit.DAYS.between(location.getDateDebut(), location.getDateRetour()) +1;
			prix += (nbJours * location.getTarifJour());
		}
		facture.setPrix(prix);
        return factureRepository.save(facture);
	}

	public Facture payerFacture( Integer noFacture){
		Facture facture = factureRepository.findById(noFacture).orElse(null);
		facture.setDatePaiement(LocalDateTime.now());
		Facture factureBD = factureRepository.save(facture);
		return factureBD;
	}

	@Override
	public void trouverLocationParExemplaireCodebarre(String codebarre) {
		// TODO Auto-generated method stub
		
	}

}
