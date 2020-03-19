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

import it.spootify.Spootify.dto.AlbumDTO;
import it.spootify.Spootify.dto.RiproduzioneDTO;
import it.spootify.Spootify.model.Album;
import it.spootify.Spootify.service.AlbumService;
import it.spootify.Spootify.service.RiproduzioneService;

@RestController
@RequestMapping(value = "/album")
public class AlbumController {

	@Autowired
	private AlbumService albumService;
	
	@Autowired
	private RiproduzioneService riproduzioneService;
	
	@GetMapping("/{id}")
	public ResponseEntity<AlbumDTO> getAlbum (@PathVariable(value = "id") Long id){
		Album album = albumService.caricaSingoloEager(id);
		AlbumDTO albumDTO = AlbumDTO.buildAlbumDTOFromModel(album, true, true);
		return ResponseEntity.ok(albumDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<AlbumDTO>> listAll(){
		List<AlbumDTO> albumDTO = AlbumDTO.buildListAlbumDTOFromModel(albumService.listAll());
		return ResponseEntity.ok(albumDTO);
	}
	
	@PostMapping
	public ResponseEntity<AlbumDTO> inserisciArtista(@RequestBody AlbumDTO albumDTO){
		Album album = AlbumDTO.buildAlbumModelFromDTO(albumDTO, false, false);
		albumService.inserisci(album);
		AlbumDTO albumInserito = AlbumDTO.buildAlbumDTOFromModel(album, false, false);
		return ResponseEntity.ok(albumInserito);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AlbumDTO> modificaArtista(@PathVariable(value = "id") Long id, @RequestBody AlbumDTO albumDTO){
		albumDTO.setId(id);
		Album albumDaModificare = AlbumDTO.buildAlbumModelFromDTO(albumDTO, false, false);
		albumService.aggiorna(albumDaModificare);
		AlbumDTO albumModificatoDTO = AlbumDTO.buildAlbumDTOFromModel(albumDaModificare, false, false);
		return ResponseEntity.ok(albumModificatoDTO);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<AlbumDTO> eliminaArtista(@PathVariable(value ="id") Long id){
		Album albumDaEliminare = albumService.caricaConId(id);
		albumService.elimina(albumDaEliminare);
		AlbumDTO albumEliminato = AlbumDTO.buildAlbumDTOFromModel(albumDaEliminare, false, false);
		return ResponseEntity.ok(albumEliminato);
	}
	
	@GetMapping("/find")
	public ResponseEntity<List<AlbumDTO>> findByExample(AlbumDTO albumDTO){
		Album album = AlbumDTO.buildAlbumModelFromDTO(albumDTO, false, false);
		List<AlbumDTO> listalbumDTO = AlbumDTO.buildListAlbumDTOFromModel(albumService.cercaPerEsempio(album));
		return ResponseEntity.ok(listalbumDTO);
	}
	
	@PostMapping("/{id}/play")
	public ResponseEntity<RiproduzioneDTO> play(@PathVariable(value = "id") Long id) {
		return riproduzioneService.playnext(id, true);
	}

	@PostMapping("/{id}/playPrevious")
	public ResponseEntity<RiproduzioneDTO> playPrevious(@PathVariable(value = "id") Long id) {
		return riproduzioneService.playprevius(id, true);
	}
	
	
}
