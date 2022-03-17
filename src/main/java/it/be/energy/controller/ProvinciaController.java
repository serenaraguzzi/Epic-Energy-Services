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
import it.be.energy.exception.ProvinciaException;
import it.be.energy.model.Provincia;
import it.be.energy.service.ProvinciaService;
import lombok.extern.slf4j.Slf4j;

@SecurityRequirement(name = "bearerAuth")
@Slf4j
@RestController
@RequestMapping("/provincia")
public class ProvinciaController {

	@Autowired
	ProvinciaService provinciaService;

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova tutte le province", description = "Trova tutte le province")
	@GetMapping(value = "/trovatutte")
	public ResponseEntity<List<Provincia>> findAll() {
		log.info("*** INIZIO RICERCA PROVINCE ***");
		List<Provincia> trovaTutte = provinciaService.findAll();
		if (!trovaTutte.isEmpty()) {
			log.info("*** FINE RICERCA PROVINCE ***");
			return new ResponseEntity<>(trovaTutte, HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova una provincia con un ID", description = "TInserire un ID di una provincia da trovare")
	@GetMapping(value = "/trovaprovinciabyid/{id}")
	public ResponseEntity<Provincia> findProvinciaById(@PathVariable Long id) throws ProvinciaException {
		log.info("*** INIZIO RICERCA PROVINCIA ***");
		Optional<Provincia> provinciaTrovata = provinciaService.findProvinciaById(id);
		if (provinciaTrovata.isPresent()) {
			log.info("*** FINE RICERCA PROVINCIA ***");
			return new ResponseEntity<>(provinciaTrovata.get(), HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

}