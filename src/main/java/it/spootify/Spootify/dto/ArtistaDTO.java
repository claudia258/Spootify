package it.spootify.Spootify.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.spootify.Spootify.model.Artista;

public class ArtistaDTO {
	
	private Long id;
	private String nome;
	private String cognome;
	
	@JsonIgnoreProperties(value= {"autore"})
	private List<AlbumDTO> albumDTO = new ArrayList<>();

	public ArtistaDTO() {
		super();
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

	public void setCognome(String congome) {
		this.cognome = congome;
	}

	public List<AlbumDTO> getAlbumDTO() {
		return albumDTO;
	}

	public void setAlbumDTO(List<AlbumDTO> albumDTO) {
		this.albumDTO = albumDTO;
	}
	
	public static Artista buildArtistaModelFromDTO(ArtistaDTO artistaDTO, boolean album) {
		Artista artista = new Artista();
		artista.setId(artistaDTO.getId());
		artista.setNome(artistaDTO.getNome());
		artista.setCognome(artistaDTO.getCognome());
		if(album)
			artista.setAlbum(AlbumDTO.buildListAlbumModelFromDTO(artistaDTO.getAlbumDTO()));
		return artista;
	}
	public static ArtistaDTO buildArtistaDTOFromModel(Artista artista, boolean album) {
		ArtistaDTO artistaDTO = new ArtistaDTO();
		artistaDTO.setId(artista.getId());
		artistaDTO.setNome(artista.getNome());
		artistaDTO.setCognome(artista.getCognome());
		if(album)
			artistaDTO.setAlbumDTO(AlbumDTO.buildListAlbumDTOFromModel(artista.getAlbum()));
		return artistaDTO;
	}
	
	public static List<Artista> buildListArtistaModelFromDTO(List<ArtistaDTO> artistiDTO){
		List<Artista> artisti = new ArrayList<>();
		for(ArtistaDTO artistaDTO : artistiDTO) {
			artisti.add(ArtistaDTO.buildArtistaModelFromDTO(artistaDTO, false));
		}
		return artisti;
	}
	
	public static List<ArtistaDTO> buildListArtistaDTOFormModel(List<Artista> artisti){
		List<ArtistaDTO> artistiDTO = new ArrayList<>();
		for(Artista artista: artisti) {
			artistiDTO.add(ArtistaDTO.buildArtistaDTOFromModel(artista, false));
		}
		return artistiDTO;
	}

}
