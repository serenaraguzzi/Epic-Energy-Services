package it.be.energy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import it.be.energy.exception.ComuneException;

import it.be.energy.model.Comune;
import it.be.energy.repository.ComuneRepository;


@Service
public class ComuneService {

	@Autowired
	ComuneRepository comuneRepository;
	
	
	public List<Comune> findAll() {
		return comuneRepository.findAll();	
		}

	
	
	
	public Optional<Comune> trovaComuneById(Long id) throws ComuneException {
		Optional<Comune> comuneTrovato = comuneRepository.findById(id);
		if (comuneTrovato.isPresent()) {
			return comuneTrovato;
	} else {
		throw new ComuneException("Comune non trovato!");
	}

	}
}
