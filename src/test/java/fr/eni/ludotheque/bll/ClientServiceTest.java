package fr.eni.ludotheque.bll;

//import static org.mockito.Mockito.doAnswer;

import fr.eni.ludotheque.bo.Adresse;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dal.ClientRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ClientServiceTest {
	
	@Autowired
	private ClientService clientService;
	
	@MockitoBean
	private ClientRepository clientRepository;
	
	@Test
	@DisplayName("Ajout d'un client cas positif")
	public void testAjouterClientCasPositif() {
		//Arrange
		Adresse adresse = new Adresse("rue des Cormorans", "44860", "Saint Aignan Grand Lieu");
		Client client = new Client("n1", "p1", "e1", adresse);
		client.setNoTelephone("0102030405");
		Client resClient = new Client();
		BeanUtils.copyProperties(client, resClient);
		resClient.setNoClient(999);

		/* Autre version :
		org.mockito.Mockito.doAnswer((invocation) -> {
			Client cli = invocation.getArgument(0);
			cli.setNoClient(999);
			return cli;
						}).when(clientRepository).save(client);
		*/
		when(clientRepository.save(client)).thenReturn(resClient);

		//Act
		Client clientActual = clientService.ajouterClient(client);

		//Assert
		assertThat(clientActual).isNotNull();
		assertThat(clientActual.getNoClient()).isEqualTo(999);
		assertThat(clientActual).isEqualTo(client);

	}

}



