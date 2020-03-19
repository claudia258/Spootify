package it.spootify.Spootify.service;

import org.springframework.http.ResponseEntity;

import it.spootify.Spootify.dto.RiproduzioneDTO;
import it.spootify.Spootify.model.Riproduzione;

public interface RiproduzioneService extends IBaseService<Riproduzione> {

	Riproduzione prossimoBrano(Long idRaccolta, Long idUtente, boolean album);

	Riproduzione branoPrecendente(Long idRaccolta, Long idUtente, boolean album);

	Riproduzione caricaRiproduzioneIdUtente(Long idRaccolta, Long idUtente, boolean isAlbum);

	Riproduzione caricaRiproduzioneCodiceSessione(Long idRaccolta, String codiceSessione, boolean album);

	Riproduzione creaRiproduzione(Long idRaccolta, Long idUtente, boolean album);

	ResponseEntity<RiproduzioneDTO> playnext(Long idRaccolta, boolean isAlbum);

	ResponseEntity<RiproduzioneDTO> playprevius(Long idRaccolta, boolean isAlbum);


}
