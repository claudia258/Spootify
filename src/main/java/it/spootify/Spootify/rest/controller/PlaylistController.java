package it.spootify.Spootify.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.spootify.Spootify.dto.PlaylistDTO;
import it.spootify.Spootify.model.Playlist;
import it.spootify.Spootify.service.PlaylistService;

@RestController
@RequestMapping(value = "/playlist")
public class PlaylistController {

	@Autowired
	private PlaylistService playlistService;

	@GetMapping("/{id}")
	public ResponseEntity<PlaylistDTO> getPlaylist(@PathVariable(value = "id") Long id) {
		Playlist playlist = playlistService.caricaConId(id);
		PlaylistDTO playlistDTO = PlaylistDTO.buildPlaylistDTOFromModel(playlist, true);
		return ResponseEntity.ok(playlistDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<PlaylistDTO>> listAll(){
		List<PlaylistDTO> playlistDTO = PlaylistDTO.buildListBranoDTOFromModel(playlistService.listAll());
		return ResponseEntity.ok(playlistDTO);
	}
	
	@PostMapping
	public ResponseEntity<PlaylistDTO> inserisciPlaylist(@RequestBody PlaylistDTO playlistDTO){
		Playlist playlist = PlaylistDTO.buildPlaylistModelFromDTO(playlistDTO, false);
		playlistService.inserisci(playlist);
		return  ResponseEntity.ok(playlistDTO); 
	}
	@PutMapping("/{id}")
	public ResponseEntity<PlaylistDTO> modificaPlaylist(@PathVariable(value = "id") Long id, @RequestBody PlaylistDTO playlistDTO){
		playlistDTO.setId(id);
		Playlist playlistDaModificare = PlaylistDTO.buildPlaylistModelFromDTO(playlistDTO, false);
		playlistService.aggiorna(playlistDaModificare);
		return  ResponseEntity.ok(playlistDTO); 
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<PlaylistDTO> eliminaPlaylist(@PathVariable(value = "id") Long id){
		Playlist playlistDaEliminare = playlistService.caricaConId(id);
		playlistService.elimina(playlistDaEliminare);
		PlaylistDTO playlistEliminata = PlaylistDTO.buildPlaylistDTOFromModel(playlistDaEliminare, true);
		return ResponseEntity.ok(playlistEliminata);
	}
	@GetMapping("/find")
	public ResponseEntity<List<PlaylistDTO>> findByExample(PlaylistDTO playlistDTO){
		Playlist playlistModel = PlaylistDTO.buildPlaylistModelFromDTO(playlistDTO, false);
		List<PlaylistDTO> playlistDto = PlaylistDTO.buildListBranoDTOFromModel(playlistService.cercaPerEsempio(playlistModel));
		return ResponseEntity.ok(playlistDto);
	}
}
