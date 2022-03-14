package it.be.energy.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.be.energy.exception.ComuneException;
import it.be.energy.model.Comune;
import it.be.energy.service.ComuneService;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/comune")
public class ComuneController {
	
	@Autowired
	ComuneService comuneService;
	
	
	
	@Operation(summary = "Trova tutti i comuni", description = "Trova tutti i comuni")
	@GetMapping(value = "/trovatutti")
	public ResponseEntity<Page<Comune>> trovaTutti(Pageable pageable) {
		Page<Comune> trovaTutti = comuneService.findAll(pageable);
		if (trovaTutti.hasContent()) {
			return new ResponseEntity<>(trovaTutti, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	
	@Operation(summary = "Trova un comune con un id", description = "Trova un comune con un id")
	@GetMapping(value = "/trovacomunebyid")
	public ResponseEntity<Comune> trovaById(@PathVariable Long id) throws ComuneException {
		Optional<Comune> comuneTrovato = comuneService.trovaComuneById(id);
		if (comuneTrovato.isPresent()) {
			return new ResponseEntity<>(comuneTrovato.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

		}
	}
}
