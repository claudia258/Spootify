package it.spootify.Spootify.service;

import java.util.List;

public interface IBaseService<T> {
	
	public List<T> listAll();
	public T caricaConId(Long id);
	public void aggiorna(T Instance);
	public void inserisci(T Instance);
	public void elimina(T Instance);
	public List<T> cercaPerEsempio(T example);

}
