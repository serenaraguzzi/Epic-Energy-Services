package it.be.energy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import it.be.energy.exception.ClienteException;
import it.be.energy.model.Cliente;
import it.be.energy.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Inserisci un cliente", description = "Inserire un cliente")
	@PostMapping(value = "/inseriscicliente", produces = MediaType.APPLICATION_JSON_VALUE)
	public String addCliente(@RequestBody Cliente cliente) {
		clienteService.inserisciCliente(cliente);
		return "Cliente salvato!";
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
}
