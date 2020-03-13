package it.spootify.Spootify.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.spootify.Spootify.model.Riproduzione;

public interface RiproduzioneRepository extends CrudRepository<Riproduzione, Long> , QueryByExampleExecutor<Riproduzione>{

	
	@Query("from Riproduzione r left join fetch r.playlist p left join fetch p.brani where r.playlist.id = ?1 and r.utenteInAscolto.id = ?2")
 	Riproduzione findRiproduzioneUtentePlaylist(Long idPlaylist, Long idUtente);

 	@Query("from Riproduzione r left join fetch r.album a left join fetch a.brani where r.album.id = ?1 and r.utenteInAscolto.id = ?2")
 	Riproduzione findRiproduzioneUtenteAlbum(Long idAlbum, Long idUtente);

}
