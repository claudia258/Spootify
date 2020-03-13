package it.spootify.Spootify.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataRegistrazione;
	@Enumerated(EnumType.STRING)
	private StatoUtente stato = StatoUtente.CREATO;
	
	@OneToOne
    @JoinColumn(name = "sessione_id", referencedColumnName = "id")
	private Sessione sessione;
	@OneToMany(mappedBy = "creatore", orphanRemoval = true)
	private List<Playlist> playlist = new ArrayList<>();
	@OneToMany(mappedBy = "utenteInAscolto", orphanRemoval = true)
	private List<Riproduzione> braniInAscolto = new ArrayList<>();
	@ManyToMany(cascade ={CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinTable(name = "utente_ruolo", joinColumns = @JoinColumn(name = "utente_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ruolo_id", referencedColumnName = "ID"))
	private List<Ruolo> ruoli = new ArrayList<>();
	
	public Utente() {
		super();
	}

	public Utente(Long id, String nome, String cognome, String username, String password, Date dataRegistrazione,
			StatoUtente stato, Sessione sessione, List<Playlist> playlist, List<Riproduzione> braniInAscolto,
			List<Ruolo> ruoli) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.dataRegistrazione = dataRegistrazione;
		this.stato = stato;
		this.sessione = sessione;
		this.playlist = playlist;
		this.braniInAscolto = braniInAscolto;
		this.ruoli = ruoli;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDataRegistrazione() {
		return dataRegistrazione;
	}

	public void setDataRegistrazione(Date dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}

	public StatoUtente getStato() {
		return stato;
	}

	public void setStato(StatoUtente stato) {
		this.stato = stato;
	}

	public Sessione getSessione() {
		return sessione;
	}

	public void setSessione(Sessione sessione) {
		this.sessione = sessione;
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

	public List<Ruolo> getRuoli() {
		return ruoli;
	}

	public void setRuoli(List<Ruolo> ruoli) {
		this.ruoli = ruoli;
	}
	
	public boolean isAdmin() {
		for (Ruolo ruoloItem : this.ruoli) {
			if (ruoloItem.getDescrizione().equals(Ruolo.ADMIN))
				return true;
		}
		return false;
	}
	
	
	

}
