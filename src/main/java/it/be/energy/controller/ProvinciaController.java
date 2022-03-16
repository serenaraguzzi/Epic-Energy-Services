package it.be.energy.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.be.energy.exception.ProvinciaException;
import it.be.energy.model.Provincia;
import it.be.energy.service.ProvinciaService;


@SecurityRequirement(name = "bearerAuth")

@RestController
@RequestMapping("/provincia")

public class ProvinciaController {
	
	
	@Autowired
	ProvinciaService provinciaService;


	@Operation(summary = "Trova tutte le province", description = "Trova tutte le province")
	@GetMapping(value = "/trovatutte")
	public ResponseEntity<List<Provincia>> trovaTutti() {
		List<Provincia> trovaTutte = provinciaService.findAll();
		if (!trovaTutte.isEmpty()) {
			return new ResponseEntity<>(trovaTutte, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	
	@Operation(summary = "Trova una provincia con un id", description = "Trova una provincia con un id")
	@GetMapping(value = "/trovaprovinciabyid/{id}")
	public ResponseEntity<Provincia> trovaById(@PathVariable Long id) throws ProvinciaException {
		Optional<Provincia> provinciaTrovata = provinciaService.trovaProvinciaById(id);
		if (provinciaTrovata.isPresent()) {
			return new ResponseEntity<>(provinciaTrovata.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

		}
	}

	
}
