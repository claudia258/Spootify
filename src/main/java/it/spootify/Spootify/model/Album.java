package it.spootify.Spootify.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Album {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titolo;
	private String genere;
	private Date dataUscita;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "autore_id", nullable = false)
	private Artista autore;
	@OneToMany(mappedBy = "album", orphanRemoval = true)
	private List<Brano> brani = new ArrayList<>();
	@OneToMany(mappedBy = "album", orphanRemoval = true)
	private List<Riproduzione> riproduzione = new ArrayList<>();
	
	public Album() {
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

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public Date getDataUscita() {
		return dataUscita;
	}

	public void setDataUscita(Date dataUscita) {
		this.dataUscita = dataUscita;
	}

	public Artista getAutore() {
		return autore;
	}

	public void setAutore(Artista autore) {
		this.autore = autore;
	}

	public List<Brano> getBrani() {
		return brani;
	}

	public void setBrani(List<Brano> brani) {
		this.brani = brani;
	}

	public List<Riproduzione> getRiproduzione() {
		return riproduzione;
	}

	public void setRiproduzione(List<Riproduzione> riproduzione) {
		this.riproduzione = riproduzione;
	}
	
	public Brano getPrimoBrano() {
 		if (brani == null || brani.isEmpty()) {
 			return null;
 		}
 		return brani.get(0);
 	}
	
	
}
