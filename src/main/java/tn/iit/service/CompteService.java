package tn.iit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.iit.dao.CompteDaoSpringData;
import tn.iit.entity.Compte;

@Service
public class CompteService {

	private final CompteDaoSpringData compteDaoSpringData;

	@Autowired
	public CompteService(CompteDaoSpringData compteDaoSpringData) {
		super();
		this.compteDaoSpringData = compteDaoSpringData;

	}

	public void saveOrUpdate(Compte compte) {
		compteDaoSpringData.saveAndFlush(compte);
	}

	public Compte findById(Long rib) {
		Optional<Compte> compteOptional = compteDaoSpringData.findById(rib);
		if (compteOptional.isPresent()) {
			return compteOptional.get();
		} else {
			return null;
		}
	}

	public void delete(Long rib) {
		compteDaoSpringData.deleteById(rib);
	}

	public List<Compte> findAll() {
		return compteDaoSpringData.findAll();
	}
}
