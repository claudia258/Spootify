package it.spootify.Spootify.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.spootify.Spootify.model.Album;

public class AlbumDTO {
	
	private Long id;
	private String titolo;
	private String genere;
	private Date dataUscita;
	
	@JsonIgnoreProperties(value= {"albumDTO"})
	private ArtistaDTO autore;
	
	@JsonIgnoreProperties(value= {"album"})
	private List<BranoDTO> braniDTO = new ArrayList<>();

	public AlbumDTO() {
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

	public ArtistaDTO getAutore() {
		return autore;
	}

	public void setAutore(ArtistaDTO autore) {
		this.autore = autore;
	}

	public List<BranoDTO> getBraniDTO() {
		return braniDTO;
	}

	public void setBraniDTO(List<BranoDTO> braniDTO) {
		this.braniDTO = braniDTO;
	}
	
	public static Album buildAlbumModelFromDTO(AlbumDTO albumDTO,boolean autore, boolean brani) {
		Album album = new Album ();
		album.setId(albumDTO.getId());
		album.setTitolo(albumDTO.getTitolo());
		album.setGenere(albumDTO.getGenere());
		album.setDataUscita(albumDTO.getDataUscita());
		if(autore)
			album.setAutore(ArtistaDTO.buildArtistaModelFromDTO(albumDTO.getAutore(), false));
		if(brani)
			album.setBrani(BranoDTO.buildListBraniModelFromDTO(albumDTO.getBraniDTO()));
		return album;
	}
	
	public static AlbumDTO buildAlbumDTOFromModel(Album album, boolean autore, boolean brani) {
		AlbumDTO albumDTO = new AlbumDTO();
		albumDTO.setId(album.getId());
		albumDTO.setTitolo(album.getTitolo());
		albumDTO.setGenere(album.getGenere());
		albumDTO.setDataUscita(album.getDataUscita());
		if(autore)
			albumDTO.setAutore(ArtistaDTO.buildArtistaDTOFromModel(album.getAutore(), false));
		if(brani)
			albumDTO.setBraniDTO(BranoDTO.buildListBraniDTOFromModel(album.getBrani()));
		
		return albumDTO;
	}
	
	
	public static List<Album> buildListAlbumModelFromDTO(List<AlbumDTO> albumDto){
		List<Album> album = new ArrayList<>();
		for(AlbumDTO albumDTO: albumDto) {
			album.add(AlbumDTO.buildAlbumModelFromDTO(albumDTO, false , false));
		}
		return album;
	}
	
	public static List<AlbumDTO> buildListAlbumDTOFromModel(List<Album> albumModel){
		List<AlbumDTO> albumDTO = new ArrayList<>();
		for(Album album: albumModel) {
			albumDTO.add(AlbumDTO.buildAlbumDTOFromModel(album, false, false));
		}
		return albumDTO;
	}
	
	
}
