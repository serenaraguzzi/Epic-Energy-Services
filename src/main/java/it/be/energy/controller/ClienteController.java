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
import it.be.energy.exception.ClienteException;
import it.be.energy.model.Cliente;
import it.be.energy.service.ClienteService;
import lombok.extern.slf4j.Slf4j;

@SecurityRequirement(name = "bearerAuth")
@Slf4j
@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova tutti i clienti", description = "Trova tutti i clienti")
	@GetMapping(value = "/trovatutti")
	public ResponseEntity<List<Cliente>> findAll() {
		log.info("*** INIZIO RICERCA CLIENTI ***");
		List<Cliente> trovaTutti = clienteService.findAll();
		if (!trovaTutti.isEmpty()) {
			log.info("*** FINE RICERCA CLIENTI ***");
			return new ResponseEntity<>(trovaTutti, HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova un cliente con un ID", description = "Inserire un ID di un cliente da trovare")
	@GetMapping(value = "/trovaclientebyid/{id}")
	public ResponseEntity<Cliente> findClienteById(@PathVariable Long id) throws ClienteException {
		log.info("*** INIZIO RICERCA CLIENTE ***");
		Optional<Cliente> clienteTrovato = clienteService.findClienteById(id);
		if (clienteTrovato.isPresent()) {
			log.info("*** FINE RICERCA CLIENTE ***");
			return new ResponseEntity<>(clienteTrovato.get(), HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Inserisci un nuovo cliente", description = "Inserire i parametri richiesti")
	@PostMapping(value = "/inseriscicliente", produces = MediaType.APPLICATION_JSON_VALUE)
	public String addCliente(@RequestBody Cliente cliente) {
		log.info("*** INIZIO INSERIMENTO CLIENTE ***");
		clienteService.addCliente(cliente);
		log.info("*** FINE INSERIMENTO CLIENTE ***");		
		return "Cliente salvato!";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Aggiorna un cliente", description = "Inserire i parametri da modificare")
	@PutMapping(value = "/aggiornacliente/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String updateCliente(@RequestBody Cliente cliente, @PathVariable Long id) throws ClienteException {
		log.info("*** INIZIO AGGIORNAMENTO CLIENTE ***");
		clienteService.updateCliente(cliente, id);
		log.info("*** FINE AGGIORNAMENTO CLIENTE ***");
		return "Aggiornamento effettuato!";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Cancella un cliente", description = "Inserire un ID di un cliente da cancellare")
	@DeleteMapping("/cancellacliente/{id}")
	public String deleteClienteById(@PathVariable Long id) {
		log.info("*** INIZIO CANCELLAZIONE CLIENTE ***");
		clienteService.deleteClienteById(id);
		log.info("*** FINE CANCELLAZIONE CLIENTE ***");
		return "Cliente cancellato!";
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Ordina i clienti per fatturato annuale", description = "Ordina i clienti per fatturato annuale")
	@GetMapping(value = "/ordinaclientibyfatturatoannuale")
	public ResponseEntity<List<Cliente>> ordinaByFatturatoAnnuale() {
		log.info("*** ORDINAMENTO IN CORSO ***");
		List<Cliente> trovaTutti = clienteService.findAllByOrderByFatturatoAnnuale();
		if (!trovaTutti.isEmpty()) {
			log.info("*** FINE ORDINAMENTO ***");
			return new ResponseEntity<>(trovaTutti, HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Ordina i clienti per data inserimento", description = "Ordina i clienti per data inserimento")
	@GetMapping(value = "/ordinaclientibydatainserimento")
	public ResponseEntity<List<Cliente>> ordinaByDataInserimento() {
		log.info("*** ORDINAMENTO IN CORSO ***");
		List<Cliente> trovaTutti = clienteService.findAllByOrderByDataInserimento();
		if (!trovaTutti.isEmpty()) {
			log.info("*** FINE ORDINAMENTO ***");
			return new ResponseEntity<>(trovaTutti, HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Ordina i clienti per data ultimo contatto", description = "Ordina i clienti per data ultimo contatto")
	@GetMapping(value = "/ordinaclientibydataultimocontatto")
	public ResponseEntity<List<Cliente>> ordinaByDataUltimoContatto() {
		log.info("*** ORDINAMENTO IN CORSO ***");
		List<Cliente> trovaTutti = clienteService.findAllByOrderByDataUltimoContatto();
		if (!trovaTutti.isEmpty()) {
			log.info("*** FINE ORDINAMENTO ***");
			return new ResponseEntity<>(trovaTutti, HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Ordina i clienti per ragione sociale", description = "Ordina i clienti per ragione sociale")
	@GetMapping(value = "/ordinaclientibyragionesociale")
	public ResponseEntity<List<Cliente>> ordinaByRagioneSociale() {
		log.info("*** ORDINAMENTO IN CORSO ***");
		List<Cliente> trovaTutti = clienteService.findAllByOrderByRagioneSociale();
		if (!trovaTutti.isEmpty()) {
			log.info("*** FINE ORDINAMENTO ***");
			return new ResponseEntity<>(trovaTutti, HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Ordina i clienti per sede legale provincia", description = "Ordina i clienti per sede legale provincia")
	@GetMapping(value = "/ordinaclientibysedelegalecomuneprovincia")
	public ResponseEntity<List<Cliente>> ordinaBySedeLegaleComuneProvinciaNome() {
		log.info("*** ORDINAMENTO IN CORSO ***");
		List<Cliente> trovaTutti = clienteService.findAllByOrderBySedeLegaleComuneProvinciaNome();
		if (!trovaTutti.isEmpty()) {
			log.info("*** FINE ORDINAMENTO ***");
			return new ResponseEntity<>(trovaTutti, HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova i clienti per data inserimento", description = "Inserire una data di inserimento del cliente")
	@GetMapping("/trovaclientiperdatainserimento/{data}")
	public ResponseEntity<List<Cliente>> findByDataInserimento(
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data) {
		log.info("*** INIZIO RICERCA CLIENTI ***");
		List<Cliente> trovati = clienteService.findByDataInserimento(data);
		if (!trovati.isEmpty()) {
			log.info("*** FINE RICERCA CLIENTI ***");
			return new ResponseEntity<>(trovati, HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>(trovati, HttpStatus.NO_CONTENT);
		}
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova i clienti per data ultimo contatto", description = "Inserire una data di ultimo contatto col cliente")
	@GetMapping("/trovaclientiperdataultimocontatto/{data}")
	public ResponseEntity<List<Cliente>> findByDataUltimoContatto(
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data) {
		log.info("*** INIZIO RICERCA CLIENTI ***");
		List<Cliente> trovati = clienteService.findByDataUltimoContatto(data);
		if (!trovati.isEmpty()) {
			log.info("*** FINE RICERCA CLIENTI ***");
			return new ResponseEntity<>(trovati, HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>(trovati, HttpStatus.NO_CONTENT);
		}
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova i clienti per fatturato annuale", description = "Inserire un range di fatturato annuale del cliente")
	@GetMapping("/trovaclientiperfatturatoannuale/{fatturatoMin}/{fatturatoMax}")
	public ResponseEntity<List<Cliente>> findByFatturatoAnnuale(@PathVariable BigDecimal fatturatoMin,
			@PathVariable BigDecimal fatturatoMax) {
		log.info("*** INIZIO RICERCA CLIENTI ***");
		List<Cliente> trovati = clienteService.findByFatturatoAnnuale(fatturatoMin, fatturatoMax);
		if (!trovati.isEmpty()) {
			log.info("*** FINE RICERCA CLIENTI ***");
			return new ResponseEntity<>(trovati, HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>(trovati, HttpStatus.NO_CONTENT);
		}
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova i clienti inserendo una parte del nome contenuta nella ragione sociale", description = "Inserire una parte del nome contenuta nella ragione sociale del cliente")
	@GetMapping("/trovaclientiperragionesociale/{fisso}")
	public ResponseEntity<List<Cliente>> findByRagioneSocialeContaining(@PathVariable String fisso) {
		log.info("*** INIZIO RICERCA CLIENTI ***");
		List<Cliente> trovati = clienteService.findByRagioneSocialeContaining(fisso);
		if (!trovati.isEmpty()) {
			log.info("*** FINE RICERCA CLIENTI ***");
			return new ResponseEntity<>(trovati, HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>(trovati, HttpStatus.NO_CONTENT);
		}
	}
	
}