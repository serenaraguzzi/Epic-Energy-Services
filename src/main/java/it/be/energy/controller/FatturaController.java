package it.be.energy.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.be.energy.exception.FatturaException;
import it.be.energy.model.Fattura;
import it.be.energy.service.FatturaService;
import lombok.extern.slf4j.Slf4j;

@SecurityRequirement(name = "bearerAuth")
@Slf4j
@RestController
@RequestMapping("/fattura")
public class FatturaController {

	@Autowired
	FatturaService fatturaService;

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova tutte le fatture", description = "Trova tutte le fatture")
	@GetMapping(value = "/trovatutte")
	public ResponseEntity<List<Fattura>> findAll() {
		log.info("*** INIZIO RICERCA FATTURE ***");
		List<Fattura> trovaTutte = fatturaService.findAll();
		if (!trovaTutte.isEmpty()) {
			log.info("*** FINE RICERCA FATTURE ***");
			return new ResponseEntity<>(trovaTutte, HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova una fattura con un ID", description = "Inserire un ID di una fattura da trovare")
	@GetMapping(value = "/trovafatturabyid/{id}")
	public ResponseEntity<Fattura> findFatturaById(@PathVariable Long id) throws FatturaException {
		log.info("*** INIZIO RICERCA FATTURA ***");
		Optional<Fattura> fatturaTrovata = fatturaService.findFatturaById(id);
		if (fatturaTrovata.isPresent()) {
			log.info("*** FINE RICERCA FATTURA ***");
			return new ResponseEntity<>(fatturaTrovata.get(), HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Inserisci una fattura", description = "Inserire i parametri richiesti")
	@PostMapping(value = "/inseriscifattura", produces = MediaType.APPLICATION_JSON_VALUE)
	public String addFattura(@RequestBody Fattura fattura) {
		log.info("*** INIZIO INSERIMENTO FATTURA ***");
		fatturaService.addFattura(fattura);
		log.info("*** FINE INSERIMENTO FATTURA ***");
		return "Fattura salvata!";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Aggiorna una fattura", description = "Inserire i parametri da modificare")
	@PutMapping(value = "/aggiornafattura/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String updateFattura(@RequestBody Fattura fattura, @PathVariable Long id) throws FatturaException {
		log.info("*** INIZIO AGGIORNAMENTO FATTURA ***");
		fatturaService.updateFattura(fattura, id);
		log.info("*** FINE AGGIORNAMENTO FATTURA ***");
		return "Aggiornamento effettuato!";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Cancella una fattura", description = "Inserire un ID di una fattura da cancellare")
	@DeleteMapping("/cancellafattura/{id}")
	public String deleteFatturaById(@PathVariable Long id) {
		log.info("*** INIZIO CANCELLAZIONE FATTURA ***");
		fatturaService.deleteFatturaById(id);
		log.info("*** FINE CANCELLAZIONE FATTURA ***");
		return "Fattura cancellata!";
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/trovafattureperragionesociale/{ragioneSociale}")
	@Operation(summary = "Trova una fattura per ragione sociale", description = "Inserire una ragione sociale di una fattura")
	public ResponseEntity<List<Fattura>> findByClienteRagioneSocialeLike(@PathVariable String ragioneSociale) {
		log.info("*** INIZIO RICERCA FATTURE ***");
		List<Fattura> trovate = fatturaService.findByClienteRagioneSocialeLike(ragioneSociale);
		if (!trovate.isEmpty()) {
			log.info("*** FINE RICERCA FATTURE ***");
			return new ResponseEntity<>(trovate, HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>(trovate, HttpStatus.NO_CONTENT);
		}
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova una fattura per stato fattura", description = "Inserire un ID di uno stato fattura di una fattura")
	@GetMapping("/trovafattureperstatofattura/{id}")
	public ResponseEntity<List<Fattura>> findByStatoFatturaId(@PathVariable Long id) {
		log.info("*** INIZIO RICERCA FATTURE ***");
		List<Fattura> trovate = fatturaService.findByStatoFatturaId(id);
		if (!trovate.isEmpty()) {
			log.info("*** FINE RICERCA FATTURE ***");
			return new ResponseEntity<>(trovate, HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>(trovate, HttpStatus.NO_CONTENT);
		}
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova una fattura per data fattura", description = "Inserire una data di una fattura")
	@GetMapping("/trovafattureperdatafattura/{data}")
	public ResponseEntity<List<Fattura>> findByDataFattura(
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data) {
		log.info("*** INIZIO RICERCA FATTURE ***");
		List<Fattura> trovate = fatturaService.findByDataFattura(data);
		if (!trovate.isEmpty()) {
			log.info("*** FINE RICERCA FATTURE ***");
			return new ResponseEntity<>(trovate, HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>(trovate, HttpStatus.NO_CONTENT);
		}
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova una fattura per anno fattura", description = "Inserire un anno di una fattura")
	@GetMapping("/trovafattureperannofattura/{anno}")
	public ResponseEntity<List<Fattura>> findByAnnoFattura(@PathVariable Integer anno) {
		log.info("*** INIZIO RICERCA FATTURE ***");
		List<Fattura> trovate = fatturaService.findByAnnoFattura(anno);
		if (!trovate.isEmpty()) {
			log.info("*** FINE RICERCA FATTURE ***");
			return new ResponseEntity<>(trovate, HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>(trovate, HttpStatus.NO_CONTENT);
		}
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova una fattura per range importo", description = "Inserire un range di importi di una fattura")
	@GetMapping("/trovafattureperrangeimporto/{importoMin}/{importoMax}")
	public ResponseEntity<List<Fattura>> findByImportoBetween(@PathVariable BigDecimal importoMin,
			@PathVariable BigDecimal importoMax) {
		log.info("*** INIZIO RICERCA FATTURE ***");
		List<Fattura> trovate = fatturaService.findByImportoBetween(importoMin, importoMax);
		if (!trovate.isEmpty()) {
			log.info("*** FINE RICERCA FATTURE ***");
			return new ResponseEntity<>(trovate, HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>(trovate, HttpStatus.NO_CONTENT);
		}
	}

}