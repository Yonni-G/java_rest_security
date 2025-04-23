package fr.eni.ludotheque.bll;

import org.springframework.stereotype.Service;

import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dal.ClientRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ClientServiceImpl implements ClientService{
	@NonNull
	private ClientRepository clientRepository;
	
	@Override
	public Client ajouterClient(Client client) {
		
		return clientRepository.save(client);
	}

}
