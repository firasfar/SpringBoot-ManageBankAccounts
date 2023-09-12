package tn.iit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.iit.entity.Compte;

@Repository
public interface CompteDaoSpringData extends JpaRepository<Compte, Long> {
	
	List<Compte> findByClientCin(String cin);
	// List<Compte> findBySoldeGreaterThan(float solde);

	// List<Compte> findBySoldeGreaterThanAndNomClient(float solde, String
	// nomClient);

}
