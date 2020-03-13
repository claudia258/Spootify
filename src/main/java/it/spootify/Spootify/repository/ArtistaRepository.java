package it.spootify.Spootify.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.spootify.Spootify.model.Artista;

public interface ArtistaRepository extends CrudRepository<Artista, Long>, QueryByExampleExecutor<Artista> {

	@Query("select a from Artista a left join a.album where a.id=?1")
	Artista caricaSingoloEager(Long id);
	
}
