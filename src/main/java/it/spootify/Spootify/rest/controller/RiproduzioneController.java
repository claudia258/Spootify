package it.spootify.Spootify.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/riproduzione")
public class RiproduzioneController {
	
//	@Autowired
//	private HttpServletRequest http;
//	
//	@Autowired
//	private UtenteService utenteService;
//		
//	@GetMapping("/{id}/play")
//	public ResponseEntity<BranoDTO> play(@PathVariable(value = "id") Long id) {
//		String codiceSessione = http.getHeader("codiceSessione"); // ricevo il token per identificare l'utente
//		if (codiceSessione == null) {
//			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
//					"Codice Sessione non valido");
//		}
//		Utente utente = utenteService.caricaUtenteInSessione(codiceSessione);
//		if (utente == null) {
//			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Codice Sessione Non Valido");
//		}
//	}
	
	
}
