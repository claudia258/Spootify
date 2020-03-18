package it.spootify.Spootify.dto;

import it.spootify.Spootify.model.Riproduzione;

public class RiproduzioneDTO {
	
	public Long idRiproduzione;
	public UtenteLoginDTO utenteInAscolto;
	private PlaylistDTO playlist;
	private AlbumDTO album;
	private BranoDTO brano;
	
	public RiproduzioneDTO() {
		super();
	}

	public Long getId() {
		return idRiproduzione;
	}

	public void setId(Long id) {
		this.idRiproduzione = id;
	}

	public UtenteLoginDTO getUtenteInAscolto() {
		return utenteInAscolto;
	}

	public void setUtenteInAscolto(UtenteLoginDTO utenteInAscolto) {
		this.utenteInAscolto = utenteInAscolto;
	}

	public PlaylistDTO getPlaylist() {
		return playlist;
	}

	public void setPlaylist(PlaylistDTO playlist) {
		this.playlist = playlist;
	}

	public AlbumDTO getAlbum() {
		return album;
	}

	public void setAlbum(AlbumDTO album) {
		this.album = album;
	}

	public BranoDTO getBrano() {
		return brano;
	}

	public void setBrano(BranoDTO brano) {
		this.brano = brano;
	}

	public static Riproduzione buildRiproduzioneModelFromDTO(RiproduzioneDTO riproduzioneDTO) {
		Riproduzione riproduzione = new Riproduzione();
		riproduzione.setId(riproduzioneDTO.getId());
		riproduzione.setUtenteInAscolto(UtenteLoginDTO.buildUtenteModelFromDTO(riproduzioneDTO.getUtenteInAscolto()));
		if(riproduzioneDTO.getAlbum() != null)
		riproduzione.setAlbum(AlbumDTO.buildAlbumModelFromDTO(riproduzioneDTO.getAlbum(), true, true));
		if(riproduzioneDTO.getPlaylist() != null)
		riproduzione.setPlaylist(PlaylistDTO.buildPlaylistModelFromDTO(riproduzioneDTO.getPlaylist(), true));
		riproduzione.setBrano(BranoDTO.buildBranoModelFromFTO(riproduzioneDTO.getBrano()));
	
		return riproduzione;
	}
	public static RiproduzioneDTO buildRiproduzioneDTOFromModel(Riproduzione riproduzione) {
		RiproduzioneDTO riproduzioneDTO = new RiproduzioneDTO();
		riproduzioneDTO.setId(riproduzione.getId());
		riproduzioneDTO.setUtenteInAscolto(UtenteLoginDTO.buildUtenteDTOFromModel(riproduzione.getUtenteInAscolto()));
		if(riproduzione.getAlbum() != null)
		riproduzioneDTO.setAlbum(AlbumDTO.buildAlbumDTOFromModel(riproduzione.getAlbum(), true, true));
		if(riproduzione.getPlaylist() != null)
		riproduzioneDTO.setPlaylist(PlaylistDTO.buildPlaylistDTOFromModel(riproduzione.getPlaylist(), true));
		riproduzioneDTO.setBrano(BranoDTO.buildBranoDTOFromModel(riproduzione.getBrano()));
		
		return riproduzioneDTO;
	}
}
