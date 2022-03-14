package it.be.energy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.be.energy.exception.ComuneException;
import it.be.energy.model.Comune;
import it.be.energy.repository.ComuneRepository;


@Service
public class ComuneService {

	@Autowired
	ComuneRepository comuneRepository;
	
	
	public Page<Comune> findAll(Pageable pageable) {
		return comuneRepository.findAll(pageable);
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
