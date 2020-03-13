package it.spootify.Spootify.service;

import it.spootify.Spootify.model.Artista;

public interface ArtistaService extends IBaseService<Artista>{

	Artista caricaSingoloEager(Long id);

}
