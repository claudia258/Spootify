package it.spootify.Spootify.service;

import java.util.List;

import it.spootify.Spootify.model.Playlist;

public interface PlaylistService extends IBaseService<Playlist> {

	List<Playlist> findPlaylistByUtente(Long id);

	Playlist caricaSingoloEager(Long id);

}
