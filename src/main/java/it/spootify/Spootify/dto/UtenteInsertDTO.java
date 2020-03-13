package it.spootify.Spootify.dto;

import java.util.Date;

import it.spootify.Spootify.model.StatoUtente;
import it.spootify.Spootify.model.Utente;

public class UtenteInsertDTO {
	
	private Long id;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private Date dataregistrazione;
	private StatoUtente stato;
	
	public UtenteInsertDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDataregistrazione() {
		return dataregistrazione;
	}

	public void setDataregistrazione(Date dataregistrazione) {
		this.dataregistrazione = dataregistrazione;
	}

	public StatoUtente getStato() {
		return stato;
	}

	public void setStato(StatoUtente stato) {
		this.stato = stato;
	}
	
	public static Utente buildUtenteModelFromDTO(UtenteInsertDTO utenteDTO) {
		Utente utente = new Utente();
		utente.setId(utenteDTO.getId());
		utente.setNome(utenteDTO.getNome());
		utente.setCognome(utenteDTO.getCognome());
		utente.setUsername(utenteDTO.getUsername());
		utente.setPassword(utenteDTO.getPassword());
		utente.setDataRegistrazione(new Date());
		utente.setStato(StatoUtente.CREATO);
	
		return utente;
	}
	
	public static UtenteInsertDTO buildUtenteDTOFromModel(Utente utente) {
		UtenteInsertDTO utenteDTO = new UtenteInsertDTO();
		utenteDTO.setId(utente.getId());
		utenteDTO.setNome(utente.getNome());
		utenteDTO.setCognome(utente.getCognome());
		utenteDTO.setUsername(utente.getUsername());
		utenteDTO.setPassword(utente.getPassword());
		utenteDTO.setDataregistrazione(new Date());
		utenteDTO.setStato(StatoUtente.CREATO);
		
		return utenteDTO;
	}

}
