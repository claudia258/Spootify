package it.spootify.Spootify.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.spootify.Spootify.dto.UtenteDTO;
import it.spootify.Spootify.dto.UtenteInsertDTO;
import it.spootify.Spootify.dto.UtenteLoginDTO;
import it.spootify.Spootify.model.Utente;
import it.spootify.Spootify.service.UtenteService;

@RestController
@RequestMapping(value = "/utente")
public class UtenteController {
	
	@Autowired
	private UtenteService utenteService;
	
	
	@GetMapping("/admin/{id}")
	public ResponseEntity<UtenteDTO> getUtente (@PathVariable(value = "id") Long id){
		Utente utente = utenteService.caricaUtenteEager(id);
		UtenteDTO utenteDTO = UtenteDTO.buildUtenteDTOFromModel(utente, true);
		return ResponseEntity.ok(utenteDTO);
		
	}
	
	@GetMapping("/admin")
	public ResponseEntity<List<UtenteLoginDTO>> listAll(){
		List<UtenteLoginDTO> utentiDTO = UtenteLoginDTO.buildListUtentiDTOFromModel(utenteService.listAll());
		return ResponseEntity.ok(utentiDTO);
	}
	
	@PostMapping
	public ResponseEntity<UtenteInsertDTO> registaUtente(@RequestBody UtenteInsertDTO utenteDTO){
		Utente utente = UtenteInsertDTO.buildUtenteModelFromDTO(utenteDTO);
		utenteService.inserisci(utente);
		UtenteInsertDTO utenteInserito = UtenteInsertDTO.buildUtenteDTOFromModel(utente);
		return ResponseEntity.ok(utenteInserito);
	}

	
	@PutMapping("/admin/{id}")
	public ResponseEntity<UtenteDTO> aggiornaUtente(@PathVariable(value = "id") Long id,@RequestBody UtenteDTO utenteDTO){
		utenteDTO.setId(id);
		Utente utenteDaModificare = UtenteDTO.buildUtenteModelFromDTO(utenteDTO, true);
		Utente utenteModificato = utenteService.aggiornaUtenteConRuoli(utenteDaModificare);
		UtenteDTO utenteModificatoDTO = UtenteDTO.buildUtenteDTOFromModel(utenteModificato, true);

		return ResponseEntity.ok(utenteModificatoDTO);

	}
	
	@GetMapping("/admin/find")
	public ResponseEntity<List<UtenteDTO>> findByExample(UtenteDTO utenteDTO){
		Utente utente = UtenteDTO.buildUtenteModelFromDTO(utenteDTO, true);
		List<UtenteDTO> utentiDTO = UtenteDTO.buildListUtentiDTOFromModel(utenteService.cercaPerEsempio(utente));
		return ResponseEntity.ok(utentiDTO);
	}
	
	@PutMapping("/admin/{id}/attiva")
	public ResponseEntity<UtenteDTO> activateUtente(@PathVariable(value = "id") Long id) {
		Utente utenteAttivato = utenteService.attivaUtenteDaId(id);
		UtenteDTO utenteAttivatoDTO = UtenteDTO.buildUtenteDTOFromModel(utenteAttivato, true);

		return ResponseEntity.ok(utenteAttivatoDTO);
	}
	
	
	
	
	
	
	}
