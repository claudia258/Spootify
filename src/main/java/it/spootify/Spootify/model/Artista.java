package it.spootify.Spootify.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Artista {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cognome;
	
	
	@OneToMany(mappedBy = "autore", orphanRemoval = true)
	private List<Album> album = new ArrayList<>();

	public Artista() {
		super();
	}

	public Artista(Long id, String nome, String cognome, List<Album> album) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.album = album;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public List<Album> getAlbum() {
		return album;
	}

	public void setAlbum(List<Album> album) {
		this.album = album;
	}
	
	
}
