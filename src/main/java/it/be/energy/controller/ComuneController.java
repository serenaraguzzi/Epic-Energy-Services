package it.be.energy.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.be.energy.exception.ComuneException;
import it.be.energy.model.Comune;
import it.be.energy.service.ComuneService;
import lombok.extern.slf4j.Slf4j;

@SecurityRequirement(name = "bearerAuth")
@Slf4j
@RestController
@RequestMapping("/comune")
public class ComuneController {

	@Autowired
	ComuneService comuneService;

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova tutti i comuni", description = "Trova tutti i comuni")
	@GetMapping(value = "/trovatutti")
	public ResponseEntity<List<Comune>> findAll() {
		log.info("*** INIZIO RICERCA COMUNI ***");
		List<Comune> trovaTutti = comuneService.findAll();
		if (!trovaTutti.isEmpty()) {
			log.info("*** FINE RICERCA COMUNI ***");
			return new ResponseEntity<>(trovaTutti, HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova un comune con un ID", description = "Inserire un ID di un comune da trovare")
	@GetMapping(value = "/trovacomunebyid/{id}")
	public ResponseEntity<Comune> findComuneById(@PathVariable Long id) throws ComuneException {
		log.info("*** INIZIO RICERCA COMUNE ***");
		Optional<Comune> comuneTrovato = comuneService.findComuneById(id);
		if (comuneTrovato.isPresent()) {
			log.info("*** FINE RICERCA COMUNE ***");
			return new ResponseEntity<>(comuneTrovato.get(), HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
}