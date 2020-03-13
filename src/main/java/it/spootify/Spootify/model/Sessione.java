package it.spootify.Spootify.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.spootify.Spootify.utility.DataUtils;

@Entity
public class Sessione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String codiceSessione;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataScandeza;
	
	@OneToOne(mappedBy = "sessione")
	private Utente utente;

	public Sessione() {
		super();
	}
	
	public Sessione(int durata) {
		Date dataFine = DataUtils.addMinutesToDate(durata, new Date());
		this.setDataScandeza(dataFine);
		this.setCodiceSessione(UUID.randomUUID().toString());
	}

	public Sessione(Long id, String codiceSessione, Date dataScandeza, Utente utente) {
		super();
		this.id = id;
		this.codiceSessione = codiceSessione;
		this.dataScandeza = dataScandeza;
		this.utente = utente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodiceSessione() {
		return codiceSessione;
	}

	public void setCodiceSessione(String codiceSessione) {
		this.codiceSessione = codiceSessione;
	}

	public Date getDataScandeza() {
		return dataScandeza;
	}

	public void setDataScandeza(Date dataScandeza) {
		this.dataScandeza = dataScandeza;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	
	
	

}
