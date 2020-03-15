package it.spootify.Spootify.rest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.spootify.Spootify.dto.BranoDTO;
import it.spootify.Spootify.dto.RiproduzioneDTO;
import it.spootify.Spootify.model.Brano;
import it.spootify.Spootify.model.Riproduzione;
import it.spootify.Spootify.model.Utente;
import it.spootify.Spootify.service.RiproduzioneService;
import it.spootify.Spootify.service.UtenteService;

@RestController
@RequestMapping(value = "/riproduzione")
public class RiproduzioneController {

	@Autowired
	private HttpServletRequest http;

	@Autowired
	private UtenteService utenteService;

	@Autowired
	private RiproduzioneService riproduzioneService;

	@GetMapping("{id}/play")
	public ResponseEntity<BranoDTO> play(@PathVariable(value = "id") Long id) {


		Riproduzione riproduzione = riproduzioneService.caricaConId(id);
		RiproduzioneDTO riproduzioneDTO = RiproduzioneDTO.buildRiproduzioneDTOFromModel(riproduzione);
		BranoDTO branoInRiproduzione = riproduzioneDTO.getBrano();

		return ResponseEntity.ok(branoInRiproduzione);
	}

	@GetMapping("{id}/playnextalbum")
	public ResponseEntity<BranoDTO> playnextalbum(@PathVariable(value = "id") Long id) {
		Riproduzione riproduzione = riproduzioneService.caricaConId(id);

		riproduzione = riproduzioneService.prossimoBrano(riproduzione.getAlbum().getId(),
				riproduzione.getUtenteInAscolto().getId(), true);
		riproduzioneService.aggiorna(riproduzione);
		Brano brano = riproduzione.getBrano();
		BranoDTO branoDTO = BranoDTO.buildBranoDTOFromModel(brano);
		return ResponseEntity.ok(branoDTO);
	}

	@GetMapping("{id}/playnextplaylist")

	public ResponseEntity<BranoDTO> playnextplaylist(@PathVariable(value = "id") Long id) {
		Riproduzione riproduzione = riproduzioneService.caricaConId(id);
		riproduzione = riproduzioneService.prossimoBrano(riproduzione.getPlaylist().getId(),
				riproduzione.getUtenteInAscolto().getId(), false);
		riproduzioneService.aggiorna(riproduzione);
		Brano brano = riproduzione.getBrano();
		BranoDTO branoDTO = BranoDTO.buildBranoDTOFromModel(brano);
		return ResponseEntity.ok(branoDTO);
	}

	@GetMapping("{id}/playpreviousalbum")
	public ResponseEntity<BranoDTO> playpreviousalbum(@PathVariable(value = "id") Long id) {
		Riproduzione riproduzione = riproduzioneService.caricaConId(id);
		riproduzione = riproduzioneService.branoPrecendente(riproduzione.getAlbum().getId(),
				riproduzione.getUtenteInAscolto().getId(), true);
		riproduzioneService.aggiorna(riproduzione);
		Brano brano = riproduzione.getBrano();
		BranoDTO branoDTO = BranoDTO.buildBranoDTOFromModel(brano);
		return ResponseEntity.ok(branoDTO);
	}

	@GetMapping("{id}/playpreviousplaylist")

	public ResponseEntity<BranoDTO> playpreviousplaylist(@PathVariable(value = "id") Long id) {
		Riproduzione riproduzione = riproduzioneService.caricaConId(id);
		riproduzione = riproduzioneService.branoPrecendente(riproduzione.getPlaylist().getId(),
				riproduzione.getUtenteInAscolto().getId(), false);
		riproduzioneService.aggiorna(riproduzione);
		Brano brano = riproduzione.getBrano();
		BranoDTO branoDTO = BranoDTO.buildBranoDTOFromModel(brano);
		return ResponseEntity.ok(branoDTO);
	}
	
	@PostMapping
	public ResponseEntity<RiproduzioneDTO> creaRiproduzione(@RequestBody RiproduzioneDTO riproduzioneDTO){
		Utente utenteInAscolto = utenteService.caricaUtenteInSessione(http.getHeader("codiceSessione"));
		Riproduzione riproduzione = RiproduzioneDTO.buildRiproduzioneModelFromDTO(riproduzioneDTO);
		riproduzione.setUtenteInAscolto(utenteInAscolto);
		riproduzioneService.inserisci(riproduzione);
		return ResponseEntity.ok(riproduzioneDTO);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<RiproduzioneDTO> eliminaRiproduzione(@PathVariable(value ="id") Long id){
		Riproduzione riproduzione = riproduzioneService.caricaConId(id);
		riproduzioneService.elimina(riproduzione);
		
		RiproduzioneDTO riproduzioneDTO = RiproduzioneDTO.buildRiproduzioneDTOFromModel(riproduzione);
		return ResponseEntity.ok(riproduzioneDTO);
		
	}
	
}

