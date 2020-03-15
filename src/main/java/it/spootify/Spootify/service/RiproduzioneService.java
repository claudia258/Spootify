package it.spootify.Spootify.service;

import it.spootify.Spootify.model.Riproduzione;

public interface RiproduzioneService extends IBaseService<Riproduzione> {

	Riproduzione prossimoBrano(Long idRaccolta, Long idUtente, boolean album);

	Riproduzione branoPrecendente(Long idRaccolta, Long idUtente, boolean album);

}
