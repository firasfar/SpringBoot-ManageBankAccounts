package tn.iit.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import tn.iit.entity.Compte;

@Transactional
@Repository
public class CompteDaoJpa {
	@PersistenceContext
	private EntityManager em;

	public void save(Compte compte) {
		em.persist(compte);
	}

	public List<Compte> findAll() {
		return em.createQuery("from Compte", Compte.class).getResultList();
	}
}
