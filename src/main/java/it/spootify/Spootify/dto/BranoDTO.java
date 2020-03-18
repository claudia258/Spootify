package it.spootify.Spootify.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.spootify.Spootify.model.Brano;

public class BranoDTO {
	
	private Long id;
	private String traccia;
	
	@JsonIgnoreProperties(value= {"braniDTO"})
	private AlbumDTO album;
	
	
	public BranoDTO() {
		super();
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


	public AlbumDTO getAlbum() {
		return album;
	}


	public void setAlbum(AlbumDTO album) {
		this.album = album;
	}
	
	public static Brano buildBranoModelFromFTO(BranoDTO branoDTO) {
		Brano brano = new Brano();
		brano.setId(branoDTO.getId());
		brano.setTraccia(branoDTO.getTraccia());
		brano.setAlbum(AlbumDTO.buildAlbumModelFromDTO(branoDTO.getAlbum(), true, false));
		
		return brano;
	}
	public static BranoDTO buildBranoDTOFromModel(Brano brano) {
		BranoDTO branoDTO = new BranoDTO();
		branoDTO.setId(brano.getId());
		branoDTO.setTraccia(brano.getTraccia());
		branoDTO.setAlbum(AlbumDTO.buildAlbumDTOFromModel(brano.getAlbum(), true, false));
	
		return branoDTO;
	}
	
	public static List<Brano> buildListBraniModelFromDTO(List<BranoDTO> braniDTO){
		List<Brano> brani = new ArrayList<Brano>();
		for(BranoDTO branoDTO: braniDTO) {
			brani.add(BranoDTO.buildBranoModelFromFTO(branoDTO));
		}
		return brani;
	}
	public static List<BranoDTO> buildListBraniDTOFromModel(List<Brano> brani){
		List<BranoDTO> braniDTO = new ArrayList<>();
		for(Brano brano : brani) {
			braniDTO.add(BranoDTO.buildBranoDTOFromModel(brano));
		}
		return braniDTO;
	}
	

}
