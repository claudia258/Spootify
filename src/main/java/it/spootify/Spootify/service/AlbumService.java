package it.spootify.Spootify.service;

import it.spootify.Spootify.model.Album;

public interface AlbumService extends IBaseService<Album>{

	Album caricaSingoloEager(Long id);

}
