package it.spootify.Spootify.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.spootify.Spootify.model.Utente;

public interface UtenteRepository extends CrudRepository<Utente, Long> , QueryByExampleExecutor<Utente>{
	
	@EntityGraph(attributePaths = { "ruoli" }) 
	Utente findByUsernameAndPassword(String username,String password);
	
	@Query("from Utente u join fetch u.ruoli where u.id=?1")
	Utente findByIdEager(Long id);
	
	@Query("from Utente u join fetch u.ruoli join fetch u.sessione s where s.codiceSessione =?1")
	Utente caricaUtenteInSessione(String codiceSessione);
	
	

}
