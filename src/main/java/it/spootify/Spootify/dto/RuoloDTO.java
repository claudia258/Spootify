package it.spootify.Spootify.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.spootify.Spootify.model.Ruolo;

public class RuoloDTO {
	
	private Long id;
	private String descrizione;
	private String codice;
	
	@JsonIgnoreProperties(value= {"ruoli"})
	private List<UtenteDTO>utenti = new ArrayList<UtenteDTO>();

	public RuoloDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public List<UtenteDTO> getUtenti() {
		return utenti;
	}

	public void setUtenti(List<UtenteDTO> utenti) {
		this.utenti = utenti;
	}
	
	public static Ruolo buildRuoloModelFromDTO(RuoloDTO ruoloDTO) {
		Ruolo ruolo = new Ruolo();
		ruolo.setId(ruoloDTO.getId());
		ruolo.setCodice(ruoloDTO.getCodice());
		ruolo.setDescrizione(ruoloDTO.getDescrizione());
		return ruolo;
	}
	public static RuoloDTO buildRuoloDTOFromModel(Ruolo ruolo) {
		RuoloDTO ruoloDTO = new RuoloDTO();
		ruoloDTO.setId(ruolo.getId());
		ruoloDTO.setCodice(ruolo.getCodice());
		ruoloDTO.setDescrizione(ruolo.getDescrizione());
		return ruoloDTO;
	}
	public static List<Ruolo> buildListRuoliModelFromDTO(List<RuoloDTO> ruoliDTO){
		List<Ruolo> ruoli = new ArrayList<Ruolo>();
		for(RuoloDTO ruoloDTO:ruoliDTO) {
			ruoli.add(buildRuoloModelFromDTO(ruoloDTO));
		}
		return ruoli;
	}
	public static List<RuoloDTO> buildListRuoliDTOFromModel(List<Ruolo> ruoli){
		List<RuoloDTO> ruoliDTO = new ArrayList<RuoloDTO>();
		for(Ruolo ruolo:ruoli) {
			ruoliDTO.add(buildRuoloDTOFromModel(ruolo));
		}
		return ruoliDTO;
	}

}
