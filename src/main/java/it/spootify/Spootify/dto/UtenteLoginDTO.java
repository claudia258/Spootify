package it.spootify.Spootify.dto;

import java.util.ArrayList;
import java.util.List;

import it.spootify.Spootify.model.StatoUtente;
import it.spootify.Spootify.model.Utente;

public class UtenteLoginDTO {
	
	private Long id;
	private String username;
	private String pwd;
	private StatoUtente stato;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return pwd;
	}
	public void setPassword(String pwd) {
		this.pwd = pwd;
	}
	
	public StatoUtente getStato() {
		return stato;
	}
	public void setStato(StatoUtente stato) {
		this.stato = stato;
	}
	public static Utente buildUtenteModelFromDTO(UtenteLoginDTO utentelog) {
		Utente utente = new Utente();
		utente.setId(utentelog.getId());
		utente.setUsername(utentelog.getUsername());
		utente.setPassword(utentelog.getPassword());
		utente.setStato(utentelog.getStato());
		
		return utente;
	}
	
	public static UtenteLoginDTO buildUtenteDTOFromModel(Utente utente) {
		UtenteLoginDTO utenteDTO = new UtenteLoginDTO();
		utenteDTO.setId(utente.getId());
		utenteDTO.setUsername(utente.getUsername());
		utenteDTO.setPassword(utente.getPassword());
		utenteDTO.setStato(utente.getStato());
		
		return utenteDTO;
		
	}
	public static List<Utente> buildListUtentiModelFromDTO(List<UtenteLoginDTO>utentiDTO){
		List<Utente> utenti = new ArrayList<Utente>();
		for(UtenteLoginDTO utenteDTO:utentiDTO) {
			utenti.add(UtenteLoginDTO.buildUtenteModelFromDTO(utenteDTO));
		}
		return utenti;
	}
	public static List<UtenteLoginDTO> buildListUtentiDTOFromModel(List<Utente>utenti){
		List<UtenteLoginDTO> utentiDTO = new ArrayList<UtenteLoginDTO>();
		for(Utente utente:utenti) {
			utentiDTO.add(UtenteLoginDTO.buildUtenteDTOFromModel(utente));
		}
		return utentiDTO;
	}

	
	

}
