package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dto.ClientDTO;

import java.util.List;

public interface ClientService {

	public Client ajouterClient(ClientDTO clientDto);

	public List<Client> trouverClientsParNom(String nom);
}
