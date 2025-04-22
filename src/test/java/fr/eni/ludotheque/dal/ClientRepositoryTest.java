package fr.eni.ludotheque.dal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import fr.eni.ludotheque.bo.Client;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClientRepositoryTest {
    @Autowired
    private ClientRepository clientRepository;

    @Test
    @DisplayName("test positif de creation d'un client en BD")
    @Transactional
    public void testCreationClient() {
        //Arrange
        Client client = new Client("Stiller", "Ben", "ben.stiller@eni.fr");
        client.setNoTelephone("0101010101");

        //Act
        Client clientBD = clientRepository.save(client);

        //Assert
        assertNotNull(clientBD);
        assertNotNull(clientBD.getNoClient());
        assertEquals("Stiller", clientBD.getNom());
        assertEquals("Ben", clientBD.getPrenom());
        assertEquals("ben.stiller@eni.fr", clientBD.getEmail());
        assertEquals("0101010101", clientBD.getNoTelephone());
    }

}
