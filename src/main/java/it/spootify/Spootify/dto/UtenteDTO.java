package it.spootify.Spootify.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.spootify.Spootify.model.Sessione;
import it.spootify.Spootify.model.StatoUtente;
import it.spootify.Spootify.model.Utente;

public class UtenteDTO {
	
	private Long id;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private Date dataRegistrazione;
	private StatoUtente stato;
	
	@JsonIgnoreProperties(value= {"utente"})
	private Sessione sessione;
	
	@JsonIgnoreProperties(value= {"utenti"})
	private List<RuoloDTO> ruoli = new ArrayList<RuoloDTO>();
	

	public UtenteDTO() {
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

	public Date getDataRegistrazione() {
		return dataRegistrazione;
	}

	public void setDataRegistrazione(Date dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}

	public StatoUtente getStato() {
		return stato;
	}

	public void setStato(StatoUtente stato) {
		this.stato = stato;
	}

	public Sessione getSessione() {
		return sessione;
	}

	public void setSessione(Sessione sessione) {
		this.sessione = sessione;
	}

	public List<RuoloDTO> getRuoli() {
		return ruoli;
	}

	public void setRuoli(List<RuoloDTO> ruoli) {
		this.ruoli = ruoli;
	}
	
	public static Utente buildUtenteModelFromDTO(UtenteDTO utenteDTO, boolean ruoli) {
		Utente utente = new Utente();
		utente.setId(utenteDTO.getId());
		utente.setNome(utenteDTO.getNome());
		utente.setCognome(utenteDTO.getCognome());
		utente.setUsername(utenteDTO.getUsername());
		utente.setPassword(utenteDTO.getPassword());
		utente.setDataRegistrazione(utenteDTO.getDataRegistrazione());
		utente.setStato(utenteDTO.getStato());
		utente.setSessione(utenteDTO.getSessione());
		
		if(ruoli)
			utente.setRuoli(RuoloDTO.buildListRuoliModelFromDTO(utenteDTO.getRuoli()));
		
		return utente;
	}
	
	public static UtenteDTO buildUtenteDTOFromModel(Utente utente, boolean ruoli) {
		UtenteDTO utenteDTO = new UtenteDTO();
		utenteDTO.setId(utente.getId());
		utenteDTO.setNome(utente.getNome());
		utenteDTO.setCognome(utente.getCognome());
		utenteDTO.setUsername(utente.getUsername());
		utenteDTO.setPassword(utente.getPassword());
		utenteDTO.setDataRegistrazione(utente.getDataRegistrazione());
		utenteDTO.setStato(utente.getStato());
		utenteDTO.setSessione(utente.getSessione());
		if(ruoli)
			utenteDTO.setRuoli(RuoloDTO.buildListRuoliDTOFromModel(utente.getRuoli()));
		return utenteDTO;
	}
	
	public static List<Utente> buildListUtentiModelFromDTO(List<UtenteDTO>utentiDTO){
		List<Utente> utenti = new ArrayList<Utente>();
		for(UtenteDTO utenteDTO:utentiDTO) {
			utenti.add(UtenteDTO.buildUtenteModelFromDTO(utenteDTO, false));
		}
		return utenti;
	}
	public static List<UtenteDTO> buildListUtentiDTOFromModel(List<Utente>utenti){
		List<UtenteDTO> utentiDTO = new ArrayList<UtenteDTO>();
		for(Utente utente:utenti) {
			utentiDTO.add(UtenteDTO.buildUtenteDTOFromModel(utente , false));
		}
		return utentiDTO;
	}
	
	

}
