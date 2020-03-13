package it.spootify.Spootify.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Brano {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String traccia;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "album_id", nullable = false)
	private Album album;
	
	@OneToMany(mappedBy = "brano", orphanRemoval = true)
	private List<Riproduzione> braniInAscolto = new ArrayList<>();	
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "brani")
	private List<Playlist> playlist = new ArrayList<>();

	public Brano() {
		super();
	}

	public Brano(Long id, String traccia, Album album, List<Playlist> playlist) {
		super();
		this.id = id;
		this.traccia = traccia;
		this.album = album;
		this.playlist = playlist;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTraccia() {
		return traccia;
	}

	public void setTraccia(String traccia) {
		this.traccia = traccia;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public List<Playlist> getPlaylist() {
		return playlist;
	}

	public void setPlaylist(List<Playlist> playlist) {
		this.playlist = playlist;
	}

	public List<Riproduzione> getBraniInAscolto() {
		return braniInAscolto;
	}

	public void setBraniInAscolto(List<Riproduzione> braniInAscolto) {
		this.braniInAscolto = braniInAscolto;
	}
	
	
}
