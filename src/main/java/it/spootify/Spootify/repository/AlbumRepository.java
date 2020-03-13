package it.spootify.Spootify.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.spootify.Spootify.model.Album;

public interface AlbumRepository extends CrudRepository<Album, Long>, QueryByExampleExecutor<Album> {

	@Query("select a from Album a left join a.autore left join a.brani where a.id=?1")
	Album caricaSingoloEager(Long id);
	}
