package it.spootify.Spootify.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.spootify.Spootify.model.Ruolo;
import it.spootify.Spootify.model.StatoUtente;
import it.spootify.Spootify.model.Utente;
import it.spootify.Spootify.repository.UtenteRepository;
import it.spootify.Spootify.utility.StringUtils;

@Service
public class UtenteServiceImpl implements UtenteService{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Autowired
	private RuoloService ruoloService;

	
	@Override
	public List<Utente> listAll() {
		return (List<Utente>)utenteRepository.findAll();
	}

	@Override
	public Utente caricaConId(Long id) {
		return utenteRepository.findById(id).orElse(null);
	}

	@Override
	public void aggiorna(Utente instance) {
		utenteRepository.save(instance);		
	}

	@Override
	public void inserisci(Utente instance) {
		utenteRepository.save(instance);		
	}

	@Override
	public void elimina(Utente instance) {
		utenteRepository.delete(instance);		
	}

	@Override
	public List<Utente> cercaPerEsempio(Utente example) {
		String q="select distinct u from Utente u where 1=1 ";
		if(StringUtils.isNotBlank(example.getNome())) {
			q+=" and u.nome like '%"+example.getNome()+"%'";
		}
		if(StringUtils.isNotBlank(example.getCognome())) {
			q+=" and u.cognome like '%"+example.getCognome()+"%'";
		}
		if(StringUtils.isNotBlank(example.getUsername())) {
			q+=" and u.username like '%"+example.getUsername()+"%'";
		}
		
		System.out.println(q);
		return entityManager.createQuery(q, Utente.class).getResultList();
	}
	
	@Override
	public Utente eseguiAccesso(String username, String password) {
		return utenteRepository.findByUsernameAndPassword(username, password);
	}
	
	@Override
	public Utente caricaUtenteEager(Long idUtente) {
		return utenteRepository.findByIdEager(idUtente);
	}

	@Override
	public Utente aggiornaUtenteConRuoli(Utente utenteInstance) {
		Utente utentePersist = this.caricaConId(utenteInstance.getId());
		List<Ruolo> RuoliPersist = new ArrayList<>();

		for (Ruolo ruolo : utenteInstance.getRuoli()) {
			RuoliPersist.add(ruoloService.caricaConId(ruolo.getId()));
		}
		utentePersist.setNome(utenteInstance.getNome());
		utentePersist.setCognome(utenteInstance.getCognome());
		
		utentePersist.setStato(utenteInstance.getStato());
		utentePersist.setRuoli(RuoliPersist);

		return utentePersist;
	}
	
	@Override
	public Utente attivaUtenteDaId(Long id) {
		Utente utenteDaAttivare = this.caricaUtenteEager(id);
		utenteDaAttivare.setStato(StatoUtente.ATTIVO);
		return utenteDaAttivare;
	}
	
	@Override
	public Utente caricaUtenteInSessione(String codiceSessione) {
		return utenteRepository.caricaUtenteInSessione(codiceSessione);
	}
}
