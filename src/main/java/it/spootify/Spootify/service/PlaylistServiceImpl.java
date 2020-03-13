package it.spootify.Spootify.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.spootify.Spootify.model.Playlist;
import it.spootify.Spootify.repository.PlaylistRepository;
import it.spootify.Spootify.utility.StringUtils;

@Service
public class PlaylistServiceImpl implements PlaylistService{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private PlaylistRepository playlistRepository;
	
	@Override
	public List<Playlist> listAll() {
		return (List<Playlist>)playlistRepository.findAll();
	}

	@Override
	public Playlist caricaConId(Long id) {
		return playlistRepository.findById(id).orElse(null);
	}

	@Override
	public void aggiorna(Playlist instance) {
		playlistRepository.save(instance);
	}

	@Override
	public void inserisci(Playlist instance) {
		playlistRepository.save(instance);		
	}

	@Override
	public void elimina(Playlist instance) {
		playlistRepository.delete(instance);
		
	}

	@Override
	public List<Playlist> cercaPerEsempio(Playlist example) {
		String q = "select distrinct p from Playlist p left join utente u where 1=1";
		if(StringUtils.isBlank(example.getTitolo())) {
			q+= "and p.titolo like '%"+example.getTitolo()+"%'";
		}
		if(StringUtils.isBlank(example.getCreatore().getUsername())) {
			q+= "and u.username like '%"+example.getCreatore().getUsername()+"%'";
		}
		return entityManager.createQuery(q, Playlist.class).getResultList();
	}
	

}
