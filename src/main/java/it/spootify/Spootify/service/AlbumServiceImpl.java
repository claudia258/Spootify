package it.spootify.Spootify.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.spootify.Spootify.model.Album;
import it.spootify.Spootify.repository.AlbumRepository;
import it.spootify.Spootify.utility.StringUtils;

@Service
public class AlbumServiceImpl implements AlbumService{

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private AlbumRepository albumRepository;
	
	@Override
	public List<Album> listAll() {
		return (List<Album>) albumRepository.findAll();
	}

	@Override
	public Album caricaConId(Long id) {
		return albumRepository.findById(id).orElse(null);
	}

	@Override
	public void aggiorna(Album Instance) {
		albumRepository.save(Instance);
		
	}

	@Override
	public void inserisci(Album Instance) {
		albumRepository.save(Instance);
	}

	@Override
	public void elimina(Album Instance) {
		albumRepository.delete(Instance);
	}

	@Override
	public List<Album> cercaPerEsempio(Album example) {
		String q = "select distinct a from Album left join autore au where 1=1";
		if(StringUtils.isBlank(example.getTitolo())) {
			q += "and a.titolo like '%"+example.getTitolo()+"%'";
		}
		if(StringUtils.isBlank(example.getGenere())) {
			q += "and a.genere like '%"+example.getGenere()+"%'";		
		}
		if(StringUtils.isBlank(example.getAutore().getCognome())) {
			q += "and au.cognome like '%"+example.getAutore().getCognome()+"%'";		
		}
		if(StringUtils.isBlank(example.getAutore().getNome())) {
			q += "and au.nome like '%"+example.getAutore().getNome()+"%'";		
		}
		return entityManager.createQuery(q, Album.class).getResultList();
	}
	
	@Override
	public Album caricaSingoloEager(Long id) {
		return albumRepository.caricaSingoloEager(id);
	}

}
