package it.spootify.Spootify.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import it.spootify.Spootify.model.Artista;
import it.spootify.Spootify.repository.ArtistaRepository;

@Service
public class ArtistaServiceImpl implements ArtistaService {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ArtistaRepository artistaRepository;
	
	@Override
	public List<Artista> listAll() {
		return (List<Artista>)artistaRepository.findAll();
	}

	@Override
	public Artista caricaConId(Long id) {
		return artistaRepository.findById(id).orElse(null);
	}

	@Override
	public void aggiorna(Artista instance) {
		artistaRepository.save(instance);
	}

	@Override
	public void inserisci(Artista instance) {
		artistaRepository.save(instance);
	}

	@Override
	public void elimina(Artista instance) {
		artistaRepository.delete(instance);
	}

	@Override
	public List<Artista> cercaPerEsempio(Artista example) {
		ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(StringMatcher.CONTAINING);
		return (List<Artista>) artistaRepository.findAll(Example.of(example, matcher));
	}
	@Override
	public Artista caricaSingoloEager(Long id) {
		return artistaRepository.caricaSingoloEager(id);
	}
	

}
