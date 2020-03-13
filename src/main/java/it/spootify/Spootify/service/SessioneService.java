package it.spootify.Spootify.service;

import it.spootify.Spootify.model.Sessione;

public interface SessioneService extends IBaseService<Sessione>{

	Sessione findByCodiceSessione(String codiceSessione);

}
