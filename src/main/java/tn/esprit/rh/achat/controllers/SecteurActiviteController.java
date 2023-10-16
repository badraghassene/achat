package tn.esprit.rh.achat.controllers;

import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.entitiesDTO.CategorieProduitDTO;
import tn.esprit.rh.achat.entitiesDTO.SecteurActiviteDTO;
import tn.esprit.rh.achat.services.ISecteurActiviteService;

import java.util.List;

@RestController
@Api(tags = "Gestion des secteurs activites")
@RequestMapping("/secteurActivite")
@CrossOrigin("*")
public class SecteurActiviteController {

	@Autowired
	ISecteurActiviteService secteurActiviteService;
	@Autowired
	private ModelMapper modelMapper;
	
	// http://localhost:8089/SpringMVC/secteurActivite/retrieve-all-secteurActivite
	@GetMapping("/retrieve-all-secteurActivite")
	@ResponseBody
	public List<SecteurActivite> getSecteurActivite() {
		return secteurActiviteService.retrieveAllSecteurActivite();
	}

	// http://localhost:8089/SpringMVC/secteurActivite/retrieve-secteurActivite/8
	@GetMapping("/retrieve-secteurActivite/{secteurActivite-id}")
	@ResponseBody
	public SecteurActivite retrieveSecteurActivite(@PathVariable("secteurActivite-id") Long secteurActiviteId) {
		return secteurActiviteService.retrieveSecteurActivite(secteurActiviteId);
	}

	// http://localhost:8089/SpringMVC/secteurActivite/add-secteurActivite
	@PostMapping("/add-secteurActivite")
	@ResponseBody
	public SecteurActiviteDTO addSecteurActivite(@RequestBody SecteurActiviteDTO secteurActiviteDTO) {
		SecteurActivite secteurActiviteRequest = modelMapper.map(secteurActiviteDTO, SecteurActivite.class);
		SecteurActivite secteurActivite = secteurActiviteService.addSecteurActivite(secteurActiviteRequest);
		// convert entity to DTO
		return modelMapper.map(secteurActivite, SecteurActiviteDTO.class);
	}

	@DeleteMapping("/remove-secteurActivite/{secteurActivite-id}")
	@ResponseBody
	public void removeSecteurActivite(@PathVariable("secteurActivite-id") Long secteurActiviteId) {
		secteurActiviteService.deleteSecteurActivite(secteurActiviteId);
	}

	// http://localhost:8089/SpringMVC/secteurActivite/modify-secteurActivite
	@PutMapping("/modify-secteurActivite")
	@ResponseBody
	public SecteurActiviteDTO modifySecteurActivite(@RequestBody SecteurActiviteDTO secteurActiviteDTO) {
		SecteurActivite secteurActiviteRequest = modelMapper.map(secteurActiviteDTO, SecteurActivite.class);
		SecteurActivite secteurActivite = secteurActiviteService.updateSecteurActivite(secteurActiviteRequest);
		// convert entity to DTO
		return modelMapper.map(secteurActivite, SecteurActiviteDTO.class);
	}

	
}
