package it.be.energy.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.be.energy.exception.IndirizzoException;
import it.be.energy.model.Indirizzo;
import it.be.energy.service.IndirizzoService;
import lombok.extern.slf4j.Slf4j;


@SecurityRequirement(name = "bearerAuth")
@Slf4j
@RestController
@RequestMapping("/indirizzo")
public class IndirizzoController {

	@Autowired
	IndirizzoService indirizzoService;
	
	
	@Operation(summary = "Trova tutti gli indirizzi", description = "Trova tutti gli indirizzi")
	@GetMapping(value = "/trovatutti")
	public ResponseEntity<List<Indirizzo>> trovaTutti() {
		List<Indirizzo> trovaTutti = indirizzoService.findAll();
		if (!trovaTutti.isEmpty()) {
			return new ResponseEntity<>(trovaTutti, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}


	
	@Operation(summary = "Trova un indirizzo con un id", description = "Trova un indirizzo con un id")
	@GetMapping(value = "/trovaindirizzobyid/{id}")
	public ResponseEntity<Indirizzo> trovaById(@PathVariable Long id) throws IndirizzoException {
		Optional<Indirizzo> indirizzoTrovato = indirizzoService.trovaIndirizzoById(id);
		if (indirizzoTrovato.isPresent()) {
			return new ResponseEntity<>(indirizzoTrovato.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Inserisci un indirizzo", description = "Inserire un indirizzo")
	@PostMapping(value = "/inserisciindirizzo", produces = MediaType.APPLICATION_JSON_VALUE)
	public String addIndirizzo(@RequestBody Indirizzo indirizzo) {
		indirizzoService.inserisciIndirizzo(indirizzo);
		return "Indirizzo salvato!";
	

	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Aggiorna un indirizzo", description = "Aggiorna un indirizzo")
	@PutMapping(value = "/aggiornaindirizzo{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String updateIndirizzo(@RequestBody Indirizzo indirizzo, @PathVariable Long id) throws IndirizzoException{
		indirizzoService.modificaIndirizzo(indirizzo, id);
		return "Aggiornamento effettuato!";
	}

	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Cancella un indirizzo", description = "Cancella un indirizzo")
	@DeleteMapping("/cancellaindirizzo")
	public String deleteIndirizzo(@RequestParam Long id) {
		indirizzoService.cancellaIndirizzoById(id);
		return "Indirizzo cancellato!";

	}
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

