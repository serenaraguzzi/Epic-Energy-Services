package it.be.energy.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.be.energy.exception.IndirizzoException;
import it.be.energy.model.Cliente;
import it.be.energy.model.Indirizzo;
import it.be.energy.repository.ClienteRepository;
import it.be.energy.repository.IndirizzoRepository;

@Service
public class IndirizzoService {

	@Autowired
	IndirizzoRepository indirizzoRepository;

	@Autowired
	ClienteRepository clienteRepository;

	public List<Indirizzo> findAll() {
		return indirizzoRepository.findAll();
	}

	public Optional<Indirizzo> findIndirizzoById(Long id) throws IndirizzoException {
		Optional<Indirizzo> indirizzoTrovato = indirizzoRepository.findById(id);
		if (indirizzoTrovato.isPresent()) {
			return indirizzoTrovato;
		} 
		else {
			throw new IndirizzoException("Indirizzo non trovato!");
		}
	}

	public Indirizzo addIndirizzo(Indirizzo indirizzo) {
		return indirizzoRepository.save(indirizzo);
	}

	public void deleteIndirizzoById(Long id) {
		List<Cliente> tutti = clienteRepository.findAll();
		for (Cliente cliente : tutti) {
			if (cliente.getSedeLegale().getId().equals(id)) {
				cliente.setSedeLegale(null);
			}
			if (cliente.getSedeOperativa().getId().equals(id)) {
				cliente.setSedeOperativa(null);
			}
		}
		indirizzoRepository.deleteById(id);
	}

	public Indirizzo updateIndirizzo(Indirizzo indirizzo, Long id) throws IndirizzoException {
		Optional<Indirizzo> indirizzoDaAggiornare = indirizzoRepository.findById(id);
		if (indirizzoDaAggiornare.isPresent()) {
			Indirizzo modifica = indirizzoDaAggiornare.get();
			modifica.setVia(indirizzo.getVia());
			modifica.setCivico(indirizzo.getCivico());
			modifica.setCap(indirizzo.getCap());
			modifica.setLocalita(indirizzo.getLocalita());
			modifica.setComune(indirizzo.getComune());
			return indirizzoRepository.save(modifica);
		} 
		else {
			throw new IndirizzoException("Indirizzo non aggiornato!");
		}
	}

}