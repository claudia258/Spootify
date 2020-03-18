package it.spootify.Spootify.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.spootify.Spootify.model.Playlist;

public interface PlaylistRepository extends CrudRepository<Playlist, Long> , QueryByExampleExecutor<Playlist>{
	
	@Query("from Playlist p left join fetch p.creatore u WHERE u.id = ?1 ")
	List<Playlist> findAllPlaylistsByUtenteId(Long id);
	
	@Query("from Playlist p left join fetch p.brani b left join fetch b.album a left join fetch a.autore left join fetch p.creatore  where p.id =?1")
	Playlist caricaPlaylistEager(Long id);
}
