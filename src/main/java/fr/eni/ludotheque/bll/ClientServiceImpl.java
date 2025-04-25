package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Adresse;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dal.ClientRepository;
import fr.eni.ludotheque.dto.ClientDTO;
import fr.eni.ludotheque.exceptions.DataNotFound;
import fr.eni.ludotheque.exceptions.EmailClientAlreadyExistException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClientServiceImpl implements ClientService{
	@NonNull
	private ClientRepository clientRepository;
	
	@Override
	public Client ajouterClient(ClientDTO clientDto)  {

		Client client = new Client();
		Adresse adresse = new Adresse();
		BeanUtils.copyProperties(clientDto, client);
		BeanUtils.copyProperties(clientDto, adresse);
		client.setAdresse(adresse);
		try {
			clientRepository.save(client);
		}catch(DataIntegrityViolationException ex) {
			throw new EmailClientAlreadyExistException();
		}
		Client newClient = clientRepository.findById(client.getNoClient()).orElseThrow(()->new DataNotFound("Client", client.getNoClient()));

		return newClient;
	}

	@Override
	public List<Client> trouverClientsParNom(String nom) {

		return clientRepository.findByNomStartsWith(nom);
	}
}
