package it.spootify.Spootify.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.spootify.Spootify.model.Ruolo;

public interface RuoloRepository extends CrudRepository<Ruolo, Long> , QueryByExampleExecutor<Ruolo>{

}
