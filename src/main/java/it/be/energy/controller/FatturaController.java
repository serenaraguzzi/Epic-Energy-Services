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
import it.be.energy.exception.ClienteException;
import it.be.energy.exception.FatturaException;
import it.be.energy.model.Cliente;
import it.be.energy.model.Fattura;
import it.be.energy.service.FatturaService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/fattura")
public class FatturaController {
	
	
	@Autowired
	FatturaService fatturaService;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova tutte le fatture", description = "Trova tutte le fatture")
	@GetMapping(value = "/trovatutte")
	public ResponseEntity<Page<Fattura>> trovaTutte(Pageable pageable) {
		Page<Fattura> trovaTutte = fatturaService.findAll(pageable);
		if (trovaTutte.hasContent()) {
			return new ResponseEntity<>(trovaTutte, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}


	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova tutti i clienti con un id", description = "Trova tutti i clienti con un id")
	@GetMapping(value = "/trovaclientebyid")
	public ResponseEntity<Fattura> trovaById(@PathVariable Long id) throws FatturaException {
		Optional<Fattura> fatturaTrovata = fatturaService.trovaFatturaById(id);
		if (fatturaTrovata.isPresent()) {
			return new ResponseEntity<>(fatturaTrovata.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Inserisci un cliente", description = "Inserire un cliente")
	@PostMapping(value = "/inseriscicliente", produces = MediaType.APPLICATION_JSON_VALUE)
	public String addFattura(@RequestBody Fattura fattura) {
	//	@log.info("*** INSERIMENTO CLIENTE IN CORSO ***");
		fatturaService.inserisciFattura(fattura);
		return "Fattura salvata!";
	//	 @log.info("*** INSERIMENTO CLIENTE COMPLETATO ***");

	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Aggiorna un cliente", description = "Aggiorna un cliente")
	@PutMapping(value = "/aggiornacliente", produces = MediaType.APPLICATION_JSON_VALUE)
	public String updateFattura(@RequestBody Fattura fattura, @PathVariable Long id) throws FatturaException {
		fatturaService.modificaFattura(fattura, id);
		return "Aggiornamento effettuato!";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Cancella un cliente", description = "Cancella un cliente")
	@DeleteMapping("/cancellacliente")
	public String deleteFattura(@RequestParam Long id) {
		fatturaService.cancellaFatturaById(id);
		return "Fattura cancellata!";

	}
	
	
	
	
	
	
	
	
	
}