package it.spootify.Spootify.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.spootify.Spootify.model.Ruolo;
import it.spootify.Spootify.repository.RuoloRepository;

@Service
public class RuoloServiceImpl implements RuoloService{
	
	@Autowired
	private RuoloRepository ruoloRepository;

	@Override
	public List<Ruolo> listAll() {
		return (List<Ruolo>) ruoloRepository.findAll();
	}

	@Override
	public Ruolo caricaConId(Long id) {
		return ruoloRepository.findById(id).orElse(null);
	}

	@Override
	public void aggiorna(Ruolo instance) {
		ruoloRepository.save(instance);
	}

	@Override
	public void inserisci(Ruolo instance) {
		ruoloRepository.save(instance);
	}

	@Override
	public void elimina(Ruolo instance) {
		ruoloRepository.delete(instance);
	}

	@Override
	public List<Ruolo> cercaPerEsempio(Ruolo example) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
