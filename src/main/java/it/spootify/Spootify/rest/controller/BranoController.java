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

import it.spootify.Spootify.dto.BranoDTO;
import it.spootify.Spootify.model.Brano;
import it.spootify.Spootify.service.BranoService;

@RestController
@RequestMapping(value = "/brano")
public class BranoController {

	@Autowired
	private BranoService branoService;
	
	@GetMapping("/{id}")
	public ResponseEntity<BranoDTO> getBrano(@PathVariable(value = "id") Long id){
		Brano brano = branoService.caricaConId(id);
		BranoDTO branoDTO = BranoDTO.buildBranoDTOFromModel(brano);
		return ResponseEntity.ok(branoDTO);
	}
	@GetMapping
	public ResponseEntity<List<BranoDTO>> listAll(){
		List<BranoDTO> branoDTO = BranoDTO.buildListBraniDTOFromModel(branoService.listAll());
		return ResponseEntity.ok(branoDTO);
	}
	@PostMapping
	public ResponseEntity<BranoDTO> inserisciBrano(@RequestBody BranoDTO branoDTO){
		Brano brano = BranoDTO.buildBranoModelFromFTO(branoDTO);
		branoService.inserisci(brano);
		return ResponseEntity.ok(branoDTO);
	}
	@PutMapping("/{id}")
	public ResponseEntity<BranoDTO> modificaBrano(@PathVariable(value = "id") Long id,@RequestBody BranoDTO branoDTO){
		branoDTO.setId(id);
		Brano banoDaModificare = BranoDTO.buildBranoModelFromFTO(branoDTO);
		branoService.aggiorna(banoDaModificare);
		
		return ResponseEntity.ok(branoDTO);	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<BranoDTO> eliminaBrano(@PathVariable(value = "id") Long id){
		Brano branoDaEliminare = branoService.caricaConId(id);
		branoService.elimina(branoDaEliminare);
		BranoDTO branoEliminato = BranoDTO.buildBranoDTOFromModel(branoDaEliminare);
		return ResponseEntity.ok(branoEliminato);	
	}
	
	@GetMapping("/find")
	public ResponseEntity<List<BranoDTO>> findByExample(BranoDTO branoDTO){
		Brano brano = BranoDTO.buildBranoModelFromFTO(branoDTO);
		List<BranoDTO> braniDTO = BranoDTO.buildListBraniDTOFromModel(branoService.cercaPerEsempio(brano));
		return ResponseEntity.ok(braniDTO);
	}
}
