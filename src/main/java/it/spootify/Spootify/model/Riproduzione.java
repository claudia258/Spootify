package it.spootify.Spootify.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Riproduzione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "utenteInAscolto_id", nullable = false)
	private Utente utenteInAscolto;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "playlist_id", nullable = false)
	private Playlist playlist;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "album_id", nullable = false)
	private Album album;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "brano_id", nullable = false)
	private Brano brano;
	
	
	public Riproduzione() {
		super();
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Utente getUtenteInAscolto() {
		return utenteInAscolto;
	}
	public void setUtenteInAscolto(Utente utenteInAscolto) {
		this.utenteInAscolto = utenteInAscolto;
	}
	public Playlist getPlaylist() {
		return playlist;
	}
	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}
	public Album getAlbum() {
		return album;
	}
	public void setAlbum(Album album) {
		this.album = album;
	}
	public Brano getBrano() {
		return brano;
	}
	public void setBrano(Brano brano) {
		this.brano = brano;
	}
	
	public Brano next() {
 		List<Brano> listaBrani = getListaBrani();
 		Brano branoInRiproduzione = this.getBrano();

 		this.brano = prossimoBrano(listaBrani, branoInRiproduzione);
 		return this.brano;
 	}
	
	private Brano prossimoBrano(List<Brano> listaBrani, Brano branoInRiproduzione) {
 		for (int i = 0; i < listaBrani.size(); i++) {
 			if (listaBrani.get(i).getId() == branoInRiproduzione.getId()) {
 				Brano prossimoBrano = listaBrani.get((i + 1) % listaBrani.size());
 				return prossimoBrano;
 			}
 		}
 		return null;
 	}
	
	private List<Brano> getListaBrani() {
 		if (album != null && playlist != null) {
 			return null;
 		}
 		if (playlist != null) {
 			return this.getPlaylist().getBrani();
 		}
 		if (album != null) {
 			return this.getAlbum().getBrani();
 		}

 		return null;
 	}

}
