package tn.iit.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
@Table(name = "t_compte")
public class Compte implements Serializable /* Spec JEE */ {
	private static final long serialVersionUID = 1L;

	@Id // PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
	@Include
	private Long rib;
	private float solde;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_client")
	private Client client;
	public String getNomAndPrenom ()
	{
		return client.getNom()+" "+client.getPrenom();
	}
	//Fetch: 
	//EAGER : charge le client avec le chargement du compte
	//LAZY: ne charge pas le client lors du chargement du compte
	//LAZY: chargement du client lors de l'appel de la méthode getClient sur le compte chargé
	//JPA default fetch: 
	// 1 --> EAGER
	// * --> LAZY

	public Compte(float solde, Client client) {
		super();
		this.solde = solde;
		this.client = client;
	}
}