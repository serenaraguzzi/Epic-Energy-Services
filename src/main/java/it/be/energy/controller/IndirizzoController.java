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

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova tutti gli indirizzi", description = "Trova tutti gli indirizzi")
	@GetMapping(value = "/trovatutti")
	public ResponseEntity<List<Indirizzo>> findAll() {
		log.info("*** INIZIO RICERCA INDIRIZZI ***");
		List<Indirizzo> trovaTutti = indirizzoService.findAll();
		if (!trovaTutti.isEmpty()) {
			log.info("*** FINE RICERCA INDIRIZZI ***");
			return new ResponseEntity<>(trovaTutti, HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova un indirizzo con un ID", description = "Inserire un ID di un indirizzo da trovare")
	@GetMapping(value = "/trovaindirizzobyid/{id}")
	public ResponseEntity<Indirizzo> findIndirizzoById(@PathVariable Long id) throws IndirizzoException {
		log.info("*** INIZIO RICERCA INDIRIZZO ***");
		Optional<Indirizzo> indirizzoTrovato = indirizzoService.findIndirizzoById(id);
		if (indirizzoTrovato.isPresent()) {
			log.info("*** FINE RICERCA INDIRIZZO ***");
			return new ResponseEntity<>(indirizzoTrovato.get(), HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Inserisci un indirizzo", description = "Inserire i prametri richiesti")
	@PostMapping(value = "/inserisciindirizzo", produces = MediaType.APPLICATION_JSON_VALUE)
	public String addIndirizzo(@RequestBody Indirizzo indirizzo) {
		log.info("*** INIZIO INSERIMENTO INDIRIZZO ***");
		indirizzoService.addIndirizzo(indirizzo);
		log.info("*** FINE INSERIMENTO INDIRIZZO ***");
		return "Indirizzo salvato!";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Aggiorna un indirizzo", description = "Inserire i parametri da modificare")
	@PutMapping(value = "/aggiornaindirizzo/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String updateIndirizzo(@RequestBody Indirizzo indirizzo, @PathVariable Long id) throws IndirizzoException {
		log.info("*** INIZIO AGGIORNAMENTO INDIRIZZO ***");
		indirizzoService.updateIndirizzo(indirizzo, id);
		log.info("*** FINE AGGIORNAMENTO INDIRIZZO ***");
		return "Aggiornamento effettuato!";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Cancella un indirizzo", description = "Inserire un ID di un indirizzo da cancellare")
	@DeleteMapping("/cancellaindirizzo/{id}")
	public String deleteIndirizzoById(@PathVariable Long id) {
		log.info("*** INIZIO CANCELLAZIONE INDIRIZZO ***");
		indirizzoService.deleteIndirizzoById(id);
		log.info("*** FINE CANCELLAZIONE INDIRIZZO ***");
		return "Indirizzo cancellato!";
	}

}