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
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.be.energy.exception.ClienteException;
import it.be.energy.model.Cliente;
import it.be.energy.service.ClienteService;
import lombok.extern.slf4j.Slf4j;


@SecurityRequirement(name = "bearerAuth")
@RestController
@Slf4j
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	
	@Operation(summary = "Trova tutti i clienti", description = "Trova tutti i clienti")
	@GetMapping(value = "/trovatutti")
	public ResponseEntity<Page<Cliente>> trovaTutti(Pageable pageable) {
		Page<Cliente> trovaTutti = clienteService.findAll(pageable);
		if (trovaTutti.hasContent()) {
			return new ResponseEntity<>(trovaTutti, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova tutti i clienti con un id", description = "Trova tutti i clienti con un id")
	@GetMapping(value = "/trovaclientebyid")
	public ResponseEntity<Cliente> trovaById(@PathVariable Long id) throws ClienteException {
		Optional<Cliente> clienteTrovato = clienteService.trovaClienteById(id);
		if (clienteTrovato.isPresent()) {
			return new ResponseEntity<>(clienteTrovato.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Inserisci un cliente", description = "Inserire un cliente")
	@PostMapping(value = "/inseriscicliente", produces = MediaType.APPLICATION_JSON_VALUE)
	public String addCliente(@RequestBody Cliente cliente) {
	//	@log.info("*** INSERIMENTO CLIENTE IN CORSO ***");
		clienteService.inserisciCliente(cliente);
		return "Cliente salvato!";
	//	 @log.info("*** INSERIMENTO CLIENTE COMPLETATO ***");

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Aggiorna un cliente", description = "Aggiorna un cliente")
	@PutMapping(value = "/aggiornacliente", produces = MediaType.APPLICATION_JSON_VALUE)
	public String updateCliente(@RequestBody Cliente cliente, @PathVariable Long id) throws ClienteException {
		clienteService.modificaCliente(cliente, id);
		return "Aggiornamento effettuato!";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Cancella un cliente", description = "Cancella un cliente")
	@DeleteMapping("/cancellacliente")
	public String deleteCliente(@RequestParam Long id) {
		clienteService.cancellaClienteById(id);
		return "Cliente cancellato!";

	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova tutti i clienti", description = "Trova tutti i clienti")
	@GetMapping(value = "/trovatuttknki")
	public ResponseEntity<Page<Cliente>> ordinaByFatturatoAnnuale(Pageable pageable) {
		Page<Cliente> trovaTutti = clienteService.findAllByOrderByFatturatoAnnuale(pageable);
		if (trovaTutti.hasContent()) {
			return new ResponseEntity<>(trovaTutti, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova tutti i clienti", description = "Trova tutti i clienti")
	@GetMapping(value = "/trovatui")
	public ResponseEntity<Page<Cliente>> ordinaByDataInserimento(Pageable pageable) {
		Page<Cliente> trovaTutti = clienteService.findAllByOrderByDataInserimento(pageable);
		if (trovaTutti.hasContent()) {
			return new ResponseEntity<>(trovaTutti, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova tutti i clienti", description = "Trova tutti i clienti")
	@GetMapping(value = "/trovati")
	public ResponseEntity<Page<Cliente>> ordinaByDataUltimoContatto(Pageable pageable) {
		Page<Cliente> trovaTutti = clienteService.findAllByOrderByDataUltimoContatto(pageable);
		if (trovaTutti.hasContent()) {
			return new ResponseEntity<>(trovaTutti, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova tutti i clienti", description = "Trova tutti i clienti")
	@GetMapping(value = "/troatutti")
	public ResponseEntity<Page<Cliente>> ordinaByRagioneSociale(Pageable pageable) {
		Page<Cliente> trovaTutti = clienteService.findAllByOrderByRagioneSociale(pageable);
		if (trovaTutti.hasContent()) {
			return new ResponseEntity<>(trovaTutti, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova tutti i clienti", description = "Trova tutti i clienti")
	@GetMapping(value = "/ti")
	public ResponseEntity<Page<Cliente>> ordinaBySedeLegaleComuneProvincia(Pageable pageable) {
		Page<Cliente> trovaTutti = clienteService.findAllByOrderBySedeLegaleComuneProvincia(pageable);
		if (trovaTutti.hasContent()) {
			return new ResponseEntity<>(trovaTutti, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	
	
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova tutti i clienti", description = "Trova tutti i clienti")
	@GetMapping(value = "/trovatutt")
	public ResponseEntity<Page<Cliente>> ordinaByRagioneSocialeContaining(String fisso) {
		Page<Cliente> trovaTutti = (Page<Cliente>) clienteService.findByRagioneSocialeContaining(fisso);
		if (trovaTutti.hasContent()) {
			return new ResponseEntity<>(trovaTutti, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
