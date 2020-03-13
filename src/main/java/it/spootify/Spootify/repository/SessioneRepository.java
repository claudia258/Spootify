package it.spootify.Spootify.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.spootify.Spootify.model.Sessione;

public interface SessioneRepository extends CrudRepository<Sessione, Long>, QueryByExampleExecutor<Sessione>{
	
	Sessione findByCodiceSessione(String codiceSessione);
}
