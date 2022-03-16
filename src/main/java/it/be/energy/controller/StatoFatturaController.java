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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.be.energy.exception.StatoFatturaException;
import it.be.energy.model.StatoFattura;
import it.be.energy.service.StatoFatturaService;

@SecurityRequirement(name = "bearerAuth")

@RestController
@RequestMapping("/statofattura")
public class StatoFatturaController {

	
	@Autowired
	StatoFatturaService statoFatturaService;
	
	
	@Operation(summary = "Trova tutti gli stati fattura", description = "Trova tutti gli stati fattura")
	@GetMapping(value = "/trovatutti")
	public ResponseEntity<List<StatoFattura>> trovaTutti() {
		List<StatoFattura> trovaTutti = statoFatturaService.findAll();
		if (!trovaTutti.isEmpty()) {
			return new ResponseEntity<>(trovaTutti, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	
	@Operation(summary = "Trova uno stato fattura con un id", description = "Trova uno stato fattura con un id")
	@GetMapping(value = "/trovastatofatturabyid/{id}")
	public ResponseEntity<StatoFattura> trovaById(@PathVariable Long id) throws StatoFatturaException {
		Optional<StatoFattura> statoFatturaTrovato = statoFatturaService.trovaStatoFatturaById(id);
		if (statoFatturaTrovato.isPresent()) {
			return new ResponseEntity<>(statoFatturaTrovato.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Inserisci uno stato fattura", description = "Inserire uno stato fattura")
	@PostMapping(value = "/inseriscistatofattura", produces = MediaType.APPLICATION_JSON_VALUE)
	public String addStatoFattura(@RequestBody StatoFattura statoFattura) {
		statoFatturaService.inserisciStatoFattura(statoFattura);
		return "Stato fattura salvato!";


	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Aggiorna uno stato fattura", description = "Aggiorna uno sttao fattura")
	@PutMapping(value = "/aggiornastatofattura/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String updateStatoFattura(@RequestBody StatoFattura statoFattura, @PathVariable Long id) throws StatoFatturaException {
		statoFatturaService.modificaStatoFattura(statoFattura, id);
		return "Aggiornamento effettuato!";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Cancella uno stato fattura", description = "Cancella uno stato fattura")
	@DeleteMapping("/cancellastatofattura/{id}")
	public String deleteStatoFattura(@PathVariable Long id) {
		statoFatturaService.cancellaStatoFatturaById(id);
		return "Stato fattura cancellato!";

	}
	
}
