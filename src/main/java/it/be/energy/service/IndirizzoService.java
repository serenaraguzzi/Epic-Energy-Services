package it.be.energy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.be.energy.exception.IndirizzoException;
import it.be.energy.model.Indirizzo;
import it.be.energy.repository.IndirizzoRepository;

@Service
public class IndirizzoService {

	@Autowired
	IndirizzoRepository indirizzoRepository;
	
	
	public Page<Indirizzo> findAll(Pageable pageable) {
		return indirizzoRepository.findAll(pageable);
	}
	
	public Optional<Indirizzo> trovaIndirizzoById(Long id) throws IndirizzoException {
		Optional<Indirizzo> indirizzoTrovato = indirizzoRepository.findById(id);
		if (indirizzoTrovato.isPresent()) {
			return indirizzoTrovato;
	} else {
		throw new IndirizzoException("Indirizzo non trovato!");
	}

	}
	
	public Indirizzo inserisciIndirizzo(Indirizzo indirizzo) {
		return indirizzoRepository.save(indirizzo);

	}
	
	
	public void cancellaIndirizzoById(Long id) {
		indirizzoRepository.deleteById(id);
	}
	
	public Indirizzo modificaIndirizzo(Indirizzo indirizzo, Long id) throws IndirizzoException {
		Optional<Indirizzo> indirizzoDaAggiornare = indirizzoRepository.findById(id);
		if (indirizzoDaAggiornare.isPresent()) {
			Indirizzo modifica = indirizzoDaAggiornare.get();
			modifica.setVia(indirizzo.getVia());
			modifica.setCivico(indirizzo.getCivico());
			modifica.setCap(indirizzo.getCap());
			modifica.setLocalita(indirizzo.getLocalita());
			modifica.setComune(indirizzo.getComune());
			
			return indirizzoRepository.save(modifica);
		} else {
			throw new IndirizzoException("Indirizzo non aggiornato!");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
