package it.spootify.Spootify.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import it.spootify.Spootify.dto.UtenteLoginDTO;
import it.spootify.Spootify.model.Sessione;
import it.spootify.Spootify.model.Utente;
import it.spootify.Spootify.service.SessioneService;
import it.spootify.Spootify.service.UtenteService;


@RestController
@RequestMapping(value = "/login")
public class LoginController {
	
	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private SessioneService sessioneService;
	
	@GetMapping
	public ModelAndView goHome() {
		return new ModelAndView("index.html");
	}
	
	
	@PostMapping
	public ResponseEntity<UtenteLoginDTO> login(@RequestBody UtenteLoginDTO utenteDTO){
		Utente utente = utenteService.eseguiAccesso(utenteDTO.getUsername(), utenteDTO.getPassword());
		if(utente == null) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Accessono non Autorizzato");
		}
		utenteDTO.setStato(utenteService.caricaConId(utente.getId()).getStato());
		Sessione sessione = new Sessione(5);
		utente.setSessione(sessione);
		sessioneService.inserisci(sessione);
		utenteService.aggiorna(utente);
		
		return ResponseEntity.ok(utenteDTO);
	}
}
