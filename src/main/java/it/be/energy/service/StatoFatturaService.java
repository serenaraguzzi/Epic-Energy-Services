package it.be.energy.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.be.energy.exception.StatoFatturaException;
import it.be.energy.model.Fattura;
import it.be.energy.model.StatoFattura;
import it.be.energy.repository.FatturaRepository;
import it.be.energy.repository.StatoFatturaRepository;

@Service
public class StatoFatturaService {

	@Autowired
	StatoFatturaRepository statoFatturaRepository;

	@Autowired
	FatturaRepository fatturaRepository;

	public List<StatoFattura> findAll() {
		return statoFatturaRepository.findAll();
	}

	public Optional<StatoFattura> findStatoFatturaById(Long id) throws StatoFatturaException {
		Optional<StatoFattura> statoFatturaTrovato = statoFatturaRepository.findById(id);
		if (statoFatturaTrovato.isPresent()) {
			return statoFatturaTrovato;
		}
		else {
			throw new StatoFatturaException("Stato fattura non trovato!");
		}
	}

	public StatoFattura inserisciStatoFattura(StatoFattura statoFattura) {
		return statoFatturaRepository.save(statoFattura);
	}

	public void deleteStatoFatturaById(Long id) {
		List<Fattura> tutte = fatturaRepository.findAll();
		for (Fattura fattura : tutte) {
			if (fattura.getStatoFattura().getId().equals(id)) {
				fattura.setStatoFattura(null);
			}
		}
		statoFatturaRepository.deleteById(id);
	}

	public StatoFattura modificaStatoFattura(StatoFattura statoFattura, Long id) throws StatoFatturaException {
		Optional<StatoFattura> statoFatturaDaAggiornare = statoFatturaRepository.findById(id);
		if (statoFatturaDaAggiornare.isPresent()) {
			StatoFattura modifica = statoFatturaDaAggiornare.get();
			modifica.setNome(statoFattura.getNome());

			return statoFatturaRepository.save(modifica);
		} 
		else {
			throw new StatoFatturaException("Stato fattura non aggiornato!");
		}
	}
	
}