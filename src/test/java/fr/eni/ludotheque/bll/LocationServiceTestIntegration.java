package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.bo.Jeu;
import fr.eni.ludotheque.bo.Location;
import fr.eni.ludotheque.dal.ClientRepository;
import fr.eni.ludotheque.dto.LocationDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class LocationServiceTestIntegration {

    @Autowired
    private LocationService locationService;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    @Transactional
    public void testAjoutLocation() {
        //Arrange
        Client client = clientRepository.findByNoTelephone("123456789");

        LocationDTO locationDTO = new LocationDTO(client.getNoClient(), "6666666666666");

        //Act
        Location location = locationService.ajouterLocation(locationDTO);

        //Assert
        assertThat(location).isNotNull();
        assertThat(location.getDateDebut()).isNotNull();
        assertThat(location.getDateRetour()).isNull();
        assertThat(location.getTarifJour()).isEqualTo(9.3f);
    }



}
