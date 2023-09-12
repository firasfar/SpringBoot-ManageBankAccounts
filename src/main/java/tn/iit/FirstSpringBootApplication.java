package tn.iit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tn.iit.dao.ClientDaoSpringData;
import tn.iit.dao.CompteDaoSpringData;

@SpringBootApplication
public class FirstSpringBootApplication {

	@Autowired
	CompteDaoSpringData daoCompte;
	@Autowired
	ClientDaoSpringData daoClient;

	public static void main(String[] args) {
		SpringApplication.run(FirstSpringBootApplication.class, args);
	}

}
