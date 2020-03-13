package it.spootify.Spootify.service;

import it.spootify.Spootify.model.Utente;

public interface UtenteService extends IBaseService<Utente>{

	Utente eseguiAccesso(String username, String password);

	Utente caricaUtenteEager(Long idUtente);

	Utente aggiornaUtenteConRuoli(Utente utenteInstance);

	Utente attivaUtenteDaId(Long id);

	Utente caricaUtenteInSessione(String codiceSessione);

}
