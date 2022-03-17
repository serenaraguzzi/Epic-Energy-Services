package it.be.energy.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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
import it.be.energy.exception.StatoFatturaException;
import it.be.energy.model.StatoFattura;
import it.be.energy.service.StatoFatturaService;
import lombok.extern.slf4j.Slf4j;

@SecurityRequirement(name = "bearerAuth")
@Slf4j
@RestController
@RequestMapping("/statofattura")
public class StatoFatturaController {

	@Autowired
	StatoFatturaService statoFatturaService;

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova tutti gli stati fattura", description = "Trova tutti gli stati fattura")
	@GetMapping(value = "/trovatutti")
	public ResponseEntity<List<StatoFattura>> findAll() {
		log.info("*** INIZIO RICERCA STATI FATTURA ***");
		List<StatoFattura> trovaTutti = statoFatturaService.findAll();
		if (!trovaTutti.isEmpty()) {
			log.info("*** FINE RICERCA STATI FATTURA ***");
			return new ResponseEntity<>(trovaTutti, HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova uno stato fattura con un ID", description = "Inserire un ID di uno stato fattura da trovare")
	@GetMapping(value = "/trovastatofatturabyid/{id}")
	public ResponseEntity<StatoFattura> findStatoFatturaById(@PathVariable Long id) throws StatoFatturaException {
		log.info("*** INIZIO RICERCA STATO FATTURA ***");
		Optional<StatoFattura> statoFatturaTrovato = statoFatturaService.findStatoFatturaById(id);
		if (statoFatturaTrovato.isPresent()) {
			log.info("*** FINE RICERCA STATO FATTURA ***");
			return new ResponseEntity<>(statoFatturaTrovato.get(), HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Inserisci uno stato fattura", description = "Inserire i parametri richiesti")
	@PostMapping(value = "/inseriscistatofattura", produces = MediaType.APPLICATION_JSON_VALUE)
	public String addStatoFattura(@RequestBody StatoFattura statoFattura) {
		log.info("*** INIZIO INSERIMENTO STATO FATTURA ***");
		statoFatturaService.inserisciStatoFattura(statoFattura);
		log.info("*** FINE INSERIMENTO STATO FATTURA ***");
		return "Stato fattura salvato!";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Aggiorna uno stato fattura", description = "Inserire i parametri da modificare")
	@PutMapping(value = "/aggiornastatofattura/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String updateStatoFattura(@RequestBody StatoFattura statoFattura, @PathVariable Long id)
			throws StatoFatturaException {
		log.info("*** INIZIO AGGIORNAMENTO STATO FATTURA ***");
		statoFatturaService.modificaStatoFattura(statoFattura, id);
		log.info("*** FINE AGGIORNAMENTO STATO FATTURA ***");
		return "Aggiornamento effettuato!";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Cancella uno stato fattura", description = "Inserire un ID di uno sttao fattura da cancellare")
	@DeleteMapping("/cancellastatofattura/{id}")
	public String deleteStatoFatturaById(@PathVariable Long id) {
		log.info("*** INIZIO CANCELLAZIONE STATO FATTURA ***");
		statoFatturaService.deleteStatoFatturaById(id);
		log.info("*** FINE CANCELLAZIONE STATO FATTURA ***");
		return "Stato fattura cancellato!";
	}

}