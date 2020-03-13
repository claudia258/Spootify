package it.spootify.Spootify.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.spootify.Spootify.model.Sessione;
import it.spootify.Spootify.repository.SessioneRepository;

@Service
public class SessioneServiceImpl implements SessioneService{

	
	@Autowired
	private SessioneRepository sessioneRepository;
	
	@Override
	public List<Sessione> listAll() {
		return (List<Sessione>)sessioneRepository.findAll();
	}

	@Override
	public Sessione caricaConId(Long id) {
		return sessioneRepository.findById(id).orElse(null);
	}

	@Override
	public void aggiorna(Sessione instance) {
		sessioneRepository.save(instance);
	}

	@Override
	public void inserisci(Sessione instance) {
		sessioneRepository.save(instance);	
		
	}

	@Override
	public void elimina(Sessione instance) {
		sessioneRepository.delete(instance);
	}

	@Override
	public List<Sessione> cercaPerEsempio(Sessione example) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Sessione findByCodiceSessione(String codiceSessione) {
		return sessioneRepository.findByCodiceSessione(codiceSessione);
	}

}
