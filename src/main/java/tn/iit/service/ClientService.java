package tn.iit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.iit.dao.ClientDaoSpringData;
import tn.iit.entity.Client;

@Service
public class ClientService {

	private final ClientDaoSpringData clientDaoSpringData;

	@Autowired
	public ClientService(ClientDaoSpringData clientDaoSpringData) {
		super();
		this.clientDaoSpringData = clientDaoSpringData;

	}

	public void saveOrUpdate(Client client) {
		clientDaoSpringData.saveAndFlush(client);
	}

	public Client findById(String cin) {
		Optional<Client> clientOptional = clientDaoSpringData.findById(cin);
		if (clientOptional.isPresent()) {
			return clientOptional.get();
		} else {
			return null;
		}
	}

	public void delete(String cin) {
		clientDaoSpringData.deleteById(cin);
	}

	public List<Client> findAll() {
		return clientDaoSpringData.findAll();
	}
}
