package it.spootify.Spootify.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Ruolo {
	
	public static final String ADMIN = "ADMIN";
	public static final String CUSTOMER = "CUSTOMER";

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descrizione;
	private String codice;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "ruoli")
	private List<Utente>utenti = new ArrayList<Utente>();

	public Ruolo() {
		super();
	}

	public Ruolo(Long id, String descrizione, String codice, List<Utente> utenti) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.codice = codice;
		this.utenti = utenti;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public List<Utente> getUtenti() {
		return utenti;
	}

	public void setUtenti(List<Utente> utenti) {
		this.utenti = utenti;
	}

	
}
