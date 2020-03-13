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

import it.spootify.Spootify.dto.ArtistaDTO;
import it.spootify.Spootify.model.Artista;
import it.spootify.Spootify.service.ArtistaService;

@RestController
@RequestMapping(value = "/artista")
public class ArtistaController {
	
	@Autowired
	private ArtistaService artistaService;
	
	@GetMapping("/{id}")
	public ResponseEntity<ArtistaDTO> getArtista(@PathVariable(value = "id") Long id) {
		Artista artistaModel = artistaService.caricaSingoloEager(id);
		ArtistaDTO artistaDTO = ArtistaDTO.buildArtistaDTOFromModel(artistaModel, true);
		return ResponseEntity.ok(artistaDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<ArtistaDTO>> listAll(){
		List<ArtistaDTO> artistiDTO = ArtistaDTO.buildListArtistaDTOFormModel(artistaService.listAll());
		return ResponseEntity.ok(artistiDTO);
	}
	
	@PostMapping
	public ResponseEntity<ArtistaDTO> inserisciArtista(@RequestBody ArtistaDTO artistaDTO) {
		Artista artistaModel = ArtistaDTO.buildArtistaModelFromDTO(artistaDTO, false);
		artistaService.inserisci(artistaModel);
		ArtistaDTO artistaDTOInserito = ArtistaDTO.buildArtistaDTOFromModel(artistaModel, false);
		return ResponseEntity.ok(artistaDTOInserito);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ArtistaDTO> aggiornaArtista(@PathVariable(value = "id") Long id,@RequestBody ArtistaDTO artistaDTO) {
		artistaDTO.setId(id);
		Artista artistaDaModificare = ArtistaDTO.buildArtistaModelFromDTO(artistaDTO, false);
		artistaService.aggiorna(artistaDaModificare);
		ArtistaDTO artistaModificatoDTO = ArtistaDTO.buildArtistaDTOFromModel(artistaDaModificare, false);
		return ResponseEntity.ok(artistaModificatoDTO);
	}
	
	@GetMapping("/find")
	public ResponseEntity<List<ArtistaDTO>> findByExample(ArtistaDTO artistaDTO) {
		Artista artistaModel = ArtistaDTO.buildArtistaModelFromDTO(artistaDTO, false);
		List<ArtistaDTO> resultDTO = ArtistaDTO.buildListArtistaDTOFormModel(artistaService.cercaPerEsempio(artistaModel));
		return ResponseEntity.ok(resultDTO);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ArtistaDTO> eliminaArtista(@PathVariable(value ="id") Long id){
		Artista artistaDaEliminare = artistaService.caricaConId(id);
		artistaService.elimina(artistaDaEliminare);
		ArtistaDTO artistaEliminato = ArtistaDTO.buildArtistaDTOFromModel(artistaDaEliminare, false);
		return ResponseEntity.ok(artistaEliminato);
	}
	
}
