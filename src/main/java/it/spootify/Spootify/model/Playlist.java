package it.spootify.Spootify.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Playlist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titolo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="utente_id", nullable = false)
	private Utente creatore;
	
	@ManyToMany(cascade ={CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinTable(name = "playlist_brano", joinColumns = @JoinColumn(name = "playlist_id", referencedColumnName = "ID"),inverseJoinColumns = @JoinColumn(name = "brano_id", referencedColumnName = "ID"))
	private List<Brano> brani = new ArrayList<>();

	public Playlist() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public Utente getCreatore() {
		return creatore;
	}

	public void setCreatore(Utente creatore) {
		this.creatore = creatore;
	}

	public List<Brano> getBrani() {
		return brani;
	}

	public void setBrani(List<Brano> brani) {
		this.brani = brani;
	}
	
	public Brano getPrimoBrano() {
 		if (brani == null || brani.isEmpty()) {
 			return null;
 		}
 		return brani.get(0);
 	}
	
}
