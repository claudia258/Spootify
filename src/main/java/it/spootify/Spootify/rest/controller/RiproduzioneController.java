package it.spootify.Spootify.rest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.spootify.Spootify.dto.RiproduzioneDTO;
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
	
	@PostMapping("/{tipoRaccolta}/{idRaccolta}")
	public ResponseEntity<RiproduzioneDTO> caricaRiproduzione(
			@PathVariable(value = "tipoRaccolta") String tipoRaccolta, 
			@PathVariable(value = "idRaccolta") Long idRaccolta) {
		
		boolean isAlbum;
		if (tipoRaccolta.equals("album"))
			isAlbum = true;
		else
			isAlbum = false;
		
		String codiceSessione = http.getHeader("codiceSessione");
		Riproduzione riproduzione = riproduzioneService.caricaRiproduzioneCodiceSessione(idRaccolta,codiceSessione, isAlbum);
		RiproduzioneDTO riproduzioneDTO = RiproduzioneDTO.buildRiproduzioneDTOFromModel(riproduzione);

		return ResponseEntity.ok(riproduzioneDTO);
	}

	@PostMapping
	public ResponseEntity<RiproduzioneDTO> creaRiproduzione(@RequestBody RiproduzioneDTO riproduzioneDTO) {
		Utente utenteInAscolto = utenteService.caricaUtenteInSessione(http.getHeader("codiceSessione"));
		Riproduzione riproduzione = RiproduzioneDTO.buildRiproduzioneModelFromDTO(riproduzioneDTO);
		riproduzione.setUtenteInAscolto(utenteInAscolto);
		riproduzioneService.inserisci(riproduzione);
		return ResponseEntity.ok(riproduzioneDTO);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<RiproduzioneDTO> eliminaRiproduzione(@PathVariable(value = "id") Long id) {
		Riproduzione riproduzione = riproduzioneService.caricaConId(id);
		riproduzioneService.elimina(riproduzione);

		RiproduzioneDTO riproduzioneDTO = RiproduzioneDTO.buildRiproduzioneDTOFromModel(riproduzione);
		return ResponseEntity.ok(riproduzioneDTO);

	}

}
