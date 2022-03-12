package it.be.energy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.be.energy.controller.ClienteException;
import it.be.energy.model.Cliente;
import it.be.energy.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	public Cliente inserisciCliente(Cliente cliente) {
		return clienteRepository.save(cliente);

	}

	public void cancellaClienteById(Long id) {
		clienteRepository.deleteById(id);
	}

	public Cliente modificaCliente(Cliente cliente, Long id) throws ClienteException {
		Optional<Cliente> clienteDaAggiornare = clienteRepository.findById(id);
		if (clienteDaAggiornare.isPresent()) {
			Cliente modifica = clienteDaAggiornare.get();
			modifica.setNomeContatto(cliente.getNomeContatto());
			modifica.setCognomeContatto(cliente.getCognomeContatto());
			modifica.setRagioneSociale(cliente.getRagioneSociale());
			modifica.setPIva(cliente.getPIva());
			modifica.setPec(cliente.getPec());
			modifica.setEmail(cliente.getEmail());
			modifica.setTelefono(cliente.getTelefono());
			modifica.setTipoCliente(cliente.getTipoCliente());
			modifica.setDataInserimento(cliente.getDataInserimento());
			modifica.setDataUltimoContatto(cliente.getDataUltimoContatto());
			modifica.setEmailContatto(cliente.getEmailContatto());
			modifica.setSedeLegale(cliente.getSedeLegale());
			modifica.setSedeOperativa(cliente.getSedeOperativa());
			modifica.setFatturatoAnnuale(cliente.getFatturatoAnnuale());
			modifica.setNumeroContatto(cliente.getNumeroContatto());
			modifica.setFatture(cliente.getFatture());
			return clienteRepository.save(modifica);
		} else {
			throw new ClienteException("Cliente non aggiornato");
		}
	}

	public Page<Cliente> findAll(Pageable pageable) {
		return clienteRepository.findAll(pageable);
	}

	
	public Cliente trovaClienteById(Long id) throws ClienteException {
		Optional<Cliente> clienteTrovato = clienteRepository.findById(id);
		if (clienteTrovato.isPresent()) {
			return clienteTrovato.get();
	} else {
		throw new ClienteException("Cliente non trovato");
	}

	}
	

public Page<Cliente> findAllByOrderByRagioneSocialeAsc(Pageable pageable){
	return clienteRepository.findAllByOrderByRagioneSocialeAsc(pageable);
}

public Page<Cliente> findAllByOrderByFatturatoAnnuale(Pageable pageable){
	return clienteRepository.findAllByOrderByFatturatoAnnuale(pageable);
}

public Page<Cliente> findAllByOrderByDataInserimento(Pageable pageable){
	return clienteRepository.findAllByOrderByDataInserimento(pageable);
}

public Page<Cliente> findAllByOrderByDataUltimoContatto(Pageable pageable){
	return clienteRepository.findAllByOrderByDataUltimoContatto(pageable);
}















}
