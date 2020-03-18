package it.spootify.Spootify.rest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import it.spootify.Spootify.dto.RiproduzioneDTO;
import it.spootify.Spootify.model.Album;
import it.spootify.Spootify.model.Playlist;
import it.spootify.Spootify.model.Riproduzione;
import it.spootify.Spootify.model.Utente;
import it.spootify.Spootify.service.AlbumService;
import it.spootify.Spootify.service.PlaylistService;
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
	
	@Autowired
	private AlbumService albumService;
	
	@Autowired
	private PlaylistService playlistService;

	public ResponseEntity<RiproduzioneDTO> playnext(Long idRaccolta, boolean isAlbum) {
		Utente utenteInAscolto = utenteService.caricaUtenteInSessione(http.getHeader("codiceSessione"));
		RiproduzioneNotFound(idRaccolta, isAlbum);
		Riproduzione riproduzioneAggiornata = riproduzioneService.prossimoBrano(idRaccolta, utenteInAscolto.getId(),
				isAlbum);
		return ResponseEntity.ok(RiproduzioneDTO.buildRiproduzioneDTOFromModel(riproduzioneAggiornata));
	}


	public ResponseEntity<RiproduzioneDTO> playprevius(Long idRaccolta, boolean isAlbum) {
		Utente utenteInAscolto = utenteService.caricaUtenteInSessione(http.getHeader("codiceSessione"));
		RiproduzioneNotFound(idRaccolta, isAlbum);
		Riproduzione riproduzioneAggiornata = riproduzioneService.branoPrecendente(idRaccolta, utenteInAscolto.getId(),
				isAlbum);
		return ResponseEntity.ok(RiproduzioneDTO.buildRiproduzioneDTOFromModel(riproduzioneAggiornata));
	}
	
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
	
	private void RiproduzioneNotFound(Long idRaccolta, boolean isAlbum) {
		if (isAlbum) {
			Album album = albumService.caricaConId(idRaccolta);
			if (album == null) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "album con id " + idRaccolta + " non trovato");
			}
			if (album.getBrani().size() <= 0) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "album con id " + idRaccolta + " vuoto!");
			}
		} else {
			Playlist playlist = playlistService.caricaConId(idRaccolta);
			if (playlist == null) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,
						"playlist con id " + idRaccolta + " non trovata");
			}
			if (playlist.getBrani().size() <= 0) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "playlist con id " + idRaccolta + " vuota!");
			}
		}
	}

}
