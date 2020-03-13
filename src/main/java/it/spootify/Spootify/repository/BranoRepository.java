package it.spootify.Spootify.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.spootify.Spootify.model.Brano;

public interface BranoRepository extends CrudRepository<Brano, Long>, QueryByExampleExecutor<Brano> {
	
	@Query("Select distinct b from Brano b left join fetch b.album a where b.id = ?1")
 	Brano findByIdEagerAlbum(Long id);

 	@Query("Select distinct b from Brano b left join fetch b.playlist p where p.id = ?1")
 	List<Brano> findByIdEagerPlaylist(Long idPlaylist);
 	
	@Query("from Brano b left join b.braniInAscolto r where r.playlist.id = ?1 and r.utenteInAscolto.id = ?2")
 	Brano findLastBranoRiprodottoPlaylist(Long idPlaylist, Long idUtente);

 	@Query("from Brano b left join b.braniInAscolto r where r.album.id = ?1 and r.utenteInAscolto.id = ?2")
 	Brano findLastBranoRiprodottoAlbum(Long idAlbum, Long idUtente);
 	
 	

}
