package it.be.energy.controller;

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

import it.be.energy.exception.IndirizzoException;
import it.be.energy.model.Indirizzo;
import it.be.energy.service.IndirizzoService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/indirizzo")
public class IndirizzoController {

	@Autowired
	IndirizzoService indirizzoService;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova tutte le fat", description = "Trova tutte le fatture")
	@GetMapping(value = "/trovatutte")
	public ResponseEntity<Page<Indirizzo>> trovaTutti(Pageable pageable) {
		Page<Indirizzo> trovaTutti = indirizzoService.findAll(pageable);
		if (trovaTutti.hasContent()) {
			return new ResponseEntity<>(trovaTutti, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}


	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova tutti i clienti con un id", description = "Trova tutti i clienti con un id")
	@GetMapping(value = "/trovaclientebyid")
	public ResponseEntity<Indirizzo> trovaById(@PathVariable Long id) throws IndirizzoException {
		Optional<Indirizzo> indirizzoTrovato = indirizzoService.trovaIndirizzoById(id);
		if (indirizzoTrovato.isPresent()) {
			return new ResponseEntity<>(indirizzoTrovato.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Inserisci un cliente", description = "Inserire un cliente")
	@PostMapping(value = "/inseriscicliente", produces = MediaType.APPLICATION_JSON_VALUE)
	public String addIndirizzo(@RequestBody Indirizzo indirizzo) {
	//	@log.info("*** INSERIMENTO CLIENTE IN CORSO ***");
		indirizzoService.inserisciIndirizzo(indirizzo);
		return "Indirizzo salvato!";
	//	 @log.info("*** INSERIMENTO CLIENTE COMPLETATO ***");

	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Aggiorna un indirizzo", description = "Aggiorna un indirizzo")
	@PutMapping(value = "/aggiornaindirizzo", produces = MediaType.APPLICATION_JSON_VALUE)
	public String updateIndirizzo(@RequestBody Indirizzo indirizzo, @PathVariable Long id) throws IndirizzoException{
		indirizzoService.modificaIndirizzo(indirizzo, id);
		return "Aggiornamento effettuato!";
	}

	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Cancella un cliente", description = "Cancella un cliente")
	@DeleteMapping("/cancellacliente")
	public String deleteIndirizzo(@RequestParam Long id) {
		indirizzoService.cancellaIndirizzoById(id);
		return "Indirizzo cancellato!";

	}
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

