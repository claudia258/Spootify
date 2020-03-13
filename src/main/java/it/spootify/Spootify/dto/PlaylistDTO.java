package it.spootify.Spootify.dto;

import java.util.ArrayList;
import java.util.List;

import it.spootify.Spootify.model.Playlist;

public class PlaylistDTO {

	private Long id;
	private String titolo;
	private UtenteLoginDTO utenteLoginDTO;
	private List<BranoDTO> braniDTO = new ArrayList<>();

	public PlaylistDTO() {
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

	public UtenteLoginDTO getUtenteLoginDTO() {
		return utenteLoginDTO;
	}

	public void setUtenteLoginDTO(UtenteLoginDTO utenteLoginDTO) {
		this.utenteLoginDTO = utenteLoginDTO;
	}

	public List<BranoDTO> getBraniDTO() {
		return braniDTO;
	}

	public void setBraniDTO(List<BranoDTO> braniDTO) {
		this.braniDTO = braniDTO;
	}

	public static Playlist buildPlaylistModelFromDTO(PlaylistDTO playlistDTO, boolean brani) {
		Playlist playlist = new Playlist();
		playlist.setId(playlistDTO.getId());
		playlist.setTitolo(playlistDTO.getTitolo());
		playlist.setCreatore(UtenteLoginDTO.buildUtenteModelFromDTO(playlistDTO.getUtenteLoginDTO()));
		if (brani)
			playlist.setBrani(BranoDTO.buildListBraniModelFromDTO(playlistDTO.getBraniDTO()));

		return playlist;
	}

	public static PlaylistDTO buildPlaylistDTOFromModel(Playlist playlist, boolean brani) {
		PlaylistDTO playlistDTO = new PlaylistDTO();
		playlistDTO.setId(playlist.getId());
		playlistDTO.setTitolo(playlist.getTitolo());
		playlistDTO.setUtenteLoginDTO(UtenteLoginDTO.buildUtenteDTOFromModel(playlist.getCreatore()));
		if (brani)
			playlistDTO.setBraniDTO(BranoDTO.buildListBraniDTOFromModel(playlist.getBrani()));
		return playlistDTO;
	}

	public static List<Playlist> buildListBranoModelFromDTO(List<PlaylistDTO> playlistDTO) {
		List<Playlist> playlist = new ArrayList<>();
		for (PlaylistDTO playlistDto : playlistDTO) {
			playlist.add(PlaylistDTO.buildPlaylistModelFromDTO(playlistDto, false));
		}
		return playlist;
	}

	public static List<PlaylistDTO> buildListBranoDTOFromModel(List<Playlist> playlist) {
		List<PlaylistDTO> playlistDTO = new ArrayList<>();
		for (Playlist playlistmodel : playlist) {
			playlistDTO.add(PlaylistDTO.buildPlaylistDTOFromModel(playlistmodel, false));
		}
		return playlistDTO;
	}

}
