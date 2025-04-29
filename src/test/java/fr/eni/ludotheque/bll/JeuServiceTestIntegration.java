package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Genre;
import fr.eni.ludotheque.bo.Jeu;
import fr.eni.ludotheque.dal.JeuRepository;
import fr.eni.ludotheque.exceptions.DataNotFound;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@Slf4j
public class JeuServiceTestIntegration {

	@Autowired
	private JeuService jeuService;

	@Autowired
	private JeuRepository jeuRepository;

	
	//@Test
	@DisplayName("Test ajout jeu")
	@Transactional
	public void testAjoutJeu() {
		// Arrange
		Jeu jeu = new Jeu("Welcome", "refWelcome", 10.2f);
		jeu.addGenre(new Genre(1, ""));
		jeu.addGenre(new Genre(2, ""));

		// Act
		jeuService.ajouterJeu(jeu);

		// Assert
		Optional<Jeu> 	jeuDB = jeuRepository.findById(jeu.getNoJeu());
		if(jeuDB.isEmpty()){
			fail();
			return;
		}
		
		assertThat(jeuDB.get()).isEqualTo(jeu);
		assertThat(jeuDB.get().getGenres().size()).isEqualTo(jeu.getGenres().size());
	}


	//@Test
	//@DisplayName("Trouver un jeu par son numéro de jeu
	public void testTrouverJeuParNoJeu() {
		Jeu jeuDB = null;
		try {
			jeuDB = jeuService.trouverJeuParNoJeu(1);
		}catch(DataNotFound dnf) {
			fail();
			return;
		}
		log.info(jeuDB.toString());

	}

	@Test
	@DisplayName("Test trouver les jeux et le nb d'exemplaires disponible")
	public void testTrouverJeuxDisponibles() {
		
		List<Jeu> jeux = jeuService.listeJeuxCatalogue("TOUS");
		
		jeux.forEach(System.out::println);
		log.info(jeux.toString());
	}


}
