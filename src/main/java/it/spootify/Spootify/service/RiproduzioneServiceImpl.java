package it.spootify.Spootify.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.spootify.Spootify.model.Album;
import it.spootify.Spootify.model.Playlist;
import it.spootify.Spootify.model.Riproduzione;
import it.spootify.Spootify.model.Utente;
import it.spootify.Spootify.repository.RiproduzioneRepository;
import it.spootify.Spootify.utility.StringUtils;

@Service
public class RiproduzioneServiceImpl implements RiproduzioneService{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private RiproduzioneRepository riproduzioneRepository;
	
	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private AlbumService albumService;
	@Autowired
	private PlaylistService playlistService;
	
	@Autowired
	private HttpServletRequest http;
	
	@Override
	public List<Riproduzione> listAll() {
		return (List<Riproduzione>)riproduzioneRepository.findAll();
	}

	@Override
	public Riproduzione caricaConId(Long id) {
		return riproduzioneRepository.findById(id).orElse(null);
	}

	@Override
	public void aggiorna(Riproduzione instance) {
		riproduzioneRepository.save(instance);
		
	}

	@Override
	public void inserisci(Riproduzione instance) {
		riproduzioneRepository.save(instance);		
	}

	@Override
	public void elimina(Riproduzione instance) {
		riproduzioneRepository.delete(instance);		
	}

	@Override
	public List<Riproduzione> cercaPerEsempio(Riproduzione example) {
		String q = "select distinct r from Riproduzione r left join brano b left join utente u left join playlist p left join album a where 1=1 ";
		if(StringUtils.isBlank(example.getBrano().getTraccia())){
			q+= "and b.traccia like '%"+example.getBrano().getTraccia()+"%'";
		}
		if(StringUtils.isBlank(example.getAlbum().getTitolo())) {
			q+= "and a.titolo like '%"+example.getAlbum().getTitolo()+"%'";
		}
		if(StringUtils.isBlank(example.getPlaylist().getTitolo())) {
			q += "and p.titolo like '%"+example.getPlaylist().getTitolo()+"%'";
		}
		if(StringUtils.isBlank(example.getUtenteInAscolto().getUsername())) {
			q+= "and u.username like '%"+example.getUtenteInAscolto().getUsername()+"%'";
		}
			return entityManager.createQuery(q, Riproduzione.class).getResultList();
	}
	@Override
	public Riproduzione prossimoBrano(Long idRaccolta, Long idUtente, boolean album) {
		Riproduzione riproduzione = null;
		
		if(album)
			riproduzione = riproduzioneRepository.findRiproduzioneUtenteAlbum(idRaccolta, idUtente);
		else
			riproduzione = riproduzioneRepository.findRiproduzioneUtentePlaylist(idRaccolta, idUtente);
		
		if(riproduzione != null) {
			riproduzione.next();
			aggiorna(riproduzione);
			return riproduzione;
		}
		
		Riproduzione creaRiproduzione = new Riproduzione();
		creaRiproduzione.setUtenteInAscolto(utenteService.caricaConId(idUtente));
		if(album) {
			creaRiproduzione.setAlbum(albumService.caricaConId(idRaccolta));
			creaRiproduzione.setBrano(creaRiproduzione.getAlbum().getPrimoBrano());
		}else {
			creaRiproduzione.setPlaylist(playlistService.caricaConId(idRaccolta));
			creaRiproduzione.setBrano(creaRiproduzione.getPlaylist().getPrimoBrano());
		}
		
		return riproduzioneRepository.save(creaRiproduzione);
	}
		
	@Override
	public Riproduzione branoPrecendente(Long idRaccolta, Long idUtente, boolean album) {
		Riproduzione riproduzione = null;
		
		if(album)
			riproduzione = riproduzioneRepository.findRiproduzioneUtenteAlbum(idRaccolta, idUtente);
		else
			riproduzione = riproduzioneRepository.findRiproduzioneUtentePlaylist(idRaccolta, idUtente);
		
		if(riproduzione != null) {
			riproduzione.previous();
			aggiorna(riproduzione);
			return riproduzione;
		}
		
		Riproduzione creaRiproduzione = new Riproduzione();
		creaRiproduzione.setUtenteInAscolto(utenteService.caricaConId(idUtente));
		if(album) {
			creaRiproduzione.setAlbum(albumService.caricaConId(idRaccolta));
			creaRiproduzione.setBrano(creaRiproduzione.getAlbum().getPrimoBrano());
		}else {
			creaRiproduzione.setPlaylist(playlistService.caricaConId(idRaccolta));
			creaRiproduzione.setBrano(creaRiproduzione.getPlaylist().getPrimoBrano());
		}
		
		return riproduzioneRepository.save(creaRiproduzione);
	}
	
	@Override
	public Riproduzione caricaRiproduzioneIdUtente(Long idRaccolta, Long idUtente, boolean album) {
		Riproduzione riproduzionePersist = null;
		if (album) {
			riproduzionePersist = riproduzioneRepository.findRiproduzioneUtenteAlbum(idRaccolta, idUtente);
					
		} else {
			riproduzionePersist = riproduzioneRepository.findRiproduzioneUtentePlaylist(idRaccolta, idUtente);
					
		}

		return riproduzionePersist;
	}
	
	@Override
	public Riproduzione caricaRiproduzioneCodiceSessione(Long idRaccolta, String codiceSessione, boolean album) {
		Utente utenteInSessione = utenteService.caricaUtenteInSessione(http.getHeader("codiceSessione"));
		Riproduzione riproduzioneAttiva = caricaRiproduzioneIdUtente(idRaccolta, utenteInSessione.getId(), album);;
		if(riproduzioneAttiva != null) {
			return riproduzioneAttiva;
		}
		
		return creaRiproduzione(idRaccolta, utenteInSessione.getId(), album);
	}
	
	@Override
	public Riproduzione creaRiproduzione(Long idRaccolta, Long idUtente, boolean album) {
		Riproduzione nuovaRiproduzione = new Riproduzione();
		nuovaRiproduzione.setUtenteInAscolto(utenteService.caricaConId(idUtente));
		if (album) {
			Album albumInRiproduzione = albumService.caricaSingoloEager(idRaccolta);
			nuovaRiproduzione.setAlbum(albumInRiproduzione);
			nuovaRiproduzione.setBrano(albumInRiproduzione.getPrimoBrano());
		} else {
			Playlist playlistInRiproduzione = playlistService.caricaSingoloEager(idRaccolta);
			nuovaRiproduzione.setPlaylist(playlistInRiproduzione);
			nuovaRiproduzione.setBrano(playlistInRiproduzione.getPrimoBrano());
		}

		inserisci(nuovaRiproduzione);
		
		return nuovaRiproduzione;
	}

	
}
