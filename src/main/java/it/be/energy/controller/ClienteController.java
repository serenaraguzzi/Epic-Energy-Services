package it.be.energy.controller;




import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
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
@Slf4j
@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	
	@Operation(summary = "Trova tutti i clienti", description = "Trova tutti i clienti")
	@GetMapping(value = "/trovatutti")
	public ResponseEntity<List<Cliente>> trovaTutti() {
		log.info("*** INIZIO RICERCA CLIENTI ***");
		List<Cliente> trovaTutti = clienteService.findAll();
		if (!trovaTutti.isEmpty()) {
			log.info("*** FINE RICERCA CLIENTI ***");
			return new ResponseEntity<>(trovaTutti, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	
	@Operation(summary = "Trova un cliente con un id", description = "Trova un cliente con un id")
	@GetMapping(value = "/trovaclientebyid/{id}")
	public ResponseEntity<Cliente> trovaById(@PathVariable Long id) throws ClienteException {
		log.info("*** INIZIO RICERCA CLIENTE ***");
		Optional<Cliente> clienteTrovato = clienteService.trovaClienteById(id);
		if (clienteTrovato.isPresent()) {
			log.info("*** FINE RICERCA CLIENTE ***");
			return new ResponseEntity<>(clienteTrovato.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Inserisci un cliente", description = "Inserisci un cliente")
	@PostMapping(value = "/inseriscicliente", produces = MediaType.APPLICATION_JSON_VALUE)
	public String addCliente(@RequestBody Cliente cliente) {
		log.info("*** INIZIO INSERIMENTO CLIENTE ***");
		clienteService.inserisciCliente(cliente);
		log.info("*** FINE INSERIMENTO CLIENTE ***");
		return "Cliente salvato!";


	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Aggiorna un cliente", description = "Aggiorna un cliente")
	@PutMapping(value = "/aggiornacliente/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String updateCliente(@RequestBody Cliente cliente, @PathVariable Long id) throws ClienteException {
		log.info("*** INIZIO AGGIORNAMENTO CLIENTE ***");
		clienteService.modificaCliente(cliente, id);
		log.info("*** FINE AGGIORNAMENTO CLIENTE ***");
		return "Aggiornamento effettuato!";
	}

	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Cancella un cliente", description = "Cancella un cliente")
	@DeleteMapping("/cancellacliente")
	public String deleteCliente(@RequestParam Long id) {
		clienteService.cancellaClienteById(id);
		return "Cliente cancellato!";

	}
	
	
	
	
	
	
	
	
	@Operation(summary = "Ordina i clienti per fatturato annuale", description = "Ordina i clienti per fatturato annuale")
	@GetMapping(value = "/ordinaclientibyfatturatoannuale")
	public ResponseEntity<List<Cliente>> ordinaByFatturatoAnnuale() {
		log.info("*** ORDINAMENTO IN CORSO ***");
		List<Cliente> trovaTutti = clienteService.findAllByOrderByFatturatoAnnuale();
		if (!trovaTutti.isEmpty()) {
			log.info("*** FINE ORDINAMENTO ***");
			return new ResponseEntity<>(trovaTutti, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@Operation(summary = "Ordina i clienti per data inserimento", description = "Ordina i clienti per data inserimento")
	@GetMapping(value = "/ordinaclientibydatainserimento")
	public ResponseEntity<List<Cliente>> ordinaByDataInserimento() {
		log.info("*** ORDINAMENTO IN CORSO ***");
		List<Cliente> trovaTutti = clienteService.findAllByOrderByDataInserimento();
		if (!trovaTutti.isEmpty()) {
			log.info("*** FINE ORDINAMENTO ***");
			return new ResponseEntity<>(trovaTutti, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@Operation(summary = "Ordina i clienti per data ultimo contatto", description = "Ordina i clienti per data ultimo contatto")
	@GetMapping(value = "/ordinaclientibydataultimocontatto")
	public ResponseEntity<List<Cliente>> ordinaByDataUltimoContatto() {
		log.info("*** ORDINAMENTO IN CORSO ***");
		List<Cliente> trovaTutti = clienteService.findAllByOrderByDataUltimoContatto();
		if (!trovaTutti.isEmpty()) {
			log.info("*** FINE ORDINAMENTO ***");
			return new ResponseEntity<>(trovaTutti, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@Operation(summary = "Ordina i clienti per ragione sociale", description = "Ordina i clienti per ragione sociale")
	@GetMapping(value = "/ordinaclientibyragionesociale")
	public ResponseEntity<List<Cliente>> ordinaByRagioneSociale() {
		log.info("*** ORDINAMENTO IN CORSO ***");
		List<Cliente> trovaTutti = clienteService.findAllByOrderByRagioneSociale();
		if (!trovaTutti.isEmpty()) {
			log.info("*** FINE ORDINAMENTO ***");
			return new ResponseEntity<>(trovaTutti, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@Operation(summary = "Ordina i clienti per sede legale provincia", description = "Ordina i clienti per sede legale provincia")
	@GetMapping(value = "/ordinaclientibysedelegalecomuneprovincia")
	public ResponseEntity<List<Cliente>> ordinaBySedeLegaleComuneProvinciaNome() {
		List<Cliente> trovaTutti = clienteService.findAllByOrderBySedeLegaleComuneProvinciaNome();
		if (!trovaTutti.isEmpty()) {
			return new ResponseEntity<>(trovaTutti, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	

	
	@GetMapping("/trovaclientiperdatainserimento")
    @Operation(summary = "Trova clienti per data inserimento", description = "Trova clienti per data inserimento")
    public ResponseEntity<List<Cliente>> findByDataInserimento(Date data) {
        List<Cliente> trovati = clienteService.findByDataInserimento(data); 
        if(!trovati.isEmpty()) {
            return new ResponseEntity<>(trovati , HttpStatus.OK);
        }else {
            return new ResponseEntity<>(trovati , HttpStatus.NO_CONTENT);
        }
    }
	
	
	@GetMapping("/trovaclientiperdataultimocontatto")
    @Operation(summary = "Trova clienti per data ultimo contatto", description = "Trova clienti per data ultimo contatto")
    public ResponseEntity<List<Cliente>> findByDataUltimoContatto(Date data) {
        List<Cliente> trovati = clienteService.findByDataUltimoContatto(data); 
        if(!trovati.isEmpty()) {
            return new ResponseEntity<>(trovati , HttpStatus.OK);
        }else {
            return new ResponseEntity<>(trovati , HttpStatus.NO_CONTENT);
        }
    }
	
	
	
	
	
	@GetMapping("/trovaclientiperfatturatoannuale")
    @Operation(summary = "Trova clienti per fatturato annuale", description = "Trova clienti per fatturato annuale")
    public ResponseEntity<List<Cliente>> findByFatturatoAnnuale(BigDecimal fatturatoMin, BigDecimal fatturatoMax) {
        List<Cliente> trovati = clienteService.findByFatturatoAnnuale(fatturatoMin, fatturatoMax); 
        if(!trovati.isEmpty()) {
            return new ResponseEntity<>(trovati , HttpStatus.OK);
        }else {
            return new ResponseEntity<>(trovati , HttpStatus.NO_CONTENT);
        }
    }
	
	
	@GetMapping("/trovaclientiperragionesociale")
    @Operation(summary = "Trova clienti per ragione sociale", description = "Trova clienti per ragione sociale")
    public ResponseEntity<List<Cliente>> findByRagioneSocialeContaining(String fisso) {
        List<Cliente> trovati = clienteService.findByRagioneSocialeContaining(fisso); 
        if(!trovati.isEmpty()) {
            return new ResponseEntity<>(trovati , HttpStatus.OK);
        }else {
            return new ResponseEntity<>(trovati , HttpStatus.NO_CONTENT);
        }
    }
	
	
	
	
	
}
