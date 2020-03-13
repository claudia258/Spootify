package it.spootify.Spootify.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.spootify.Spootify.model.Brano;
import it.spootify.Spootify.repository.BranoRepository;
import it.spootify.Spootify.utility.StringUtils;

@Service
public class BranoServiceImpl implements BranoService {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private BranoRepository branoRepository;

	@Override
	public List<Brano> listAll() {
		return (List<Brano>) branoRepository.findAll();
	}

	@Override
	public Brano caricaConId(Long id) {
		return branoRepository.findById(id).orElse(null);
	}

	@Override
	public void aggiorna(Brano instance) {
		branoRepository.save(instance);

	}

	@Override
	public void inserisci(Brano instance) {
		branoRepository.save(instance);
	}

	@Override
	public void elimina(Brano instance) {
		branoRepository.delete(instance);

	}

	@Override
	public List<Brano> cercaPerEsempio(Brano example) {
		String q = "Select distinct b from Brano b left join album a where 1=1";
		if (StringUtils.isBlank(example.getTraccia())) {
			q += "and b.traccia like '%" + example.getTraccia() + "%'";
		}
		if (StringUtils.isBlank(example.getAlbum().getTitolo())) {
			q += "and a.titolo like '%" + example.getAlbum().getTitolo() + "%'";
		}

		return entityManager.createQuery(q, Brano.class).getResultList();
	}

}
