package it.spootify.Spootify.service;

import it.spootify.Spootify.model.Riproduzione;

public interface RiproduzioneService extends IBaseService<Riproduzione> {

	Riproduzione prossimoBrano(Long idRaccolta, Long idUtente, boolean album);

	Riproduzione branoPrecendente(Long idRaccolta, Long idUtente, boolean album);

	Riproduzione caricaRiproduzioneIdUtente(Long idRaccolta, Long idUtente, boolean isAlbum);

	Riproduzione caricaRiproduzioneCodiceSessione(Long idRaccolta, String codiceSessione, boolean album);

	Riproduzione creaRiproduzione(Long idRaccolta, Long idUtente, boolean album);

}
