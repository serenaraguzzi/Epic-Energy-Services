package it.be.energy.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.be.energy.exception.FatturaException;
import it.be.energy.model.Fattura;
import it.be.energy.model.StatoFattura;
import it.be.energy.repository.FatturaRepository;

@Service
public class FatturaService {

	
	@Autowired
	FatturaRepository fatturaRepository;
	
	
	public Page<Fattura> findAll(Pageable pageable) {
		return fatturaRepository.findAll(pageable);
	}
	
	public Optional<Fattura> trovaFatturaById(Long id) throws FatturaException {
		Optional<Fattura> fatturaTrovata = fatturaRepository.findById(id);
		if (fatturaTrovata.isPresent()) {
			return fatturaTrovata;
	} else {
		throw new FatturaException("Fattura non trovata!");
	}

	}
	public Fattura inserisciFattura(Fattura fattura) {
		return fatturaRepository.save(fattura);

	}
	
	public void cancellaFatturaById(Long id) {
		fatturaRepository.deleteById(id);
	}
	
	public Fattura modificaFattura(Fattura fattura, Long id) throws FatturaException {
		Optional<Fattura> fatturaDaAggiornare = fatturaRepository.findById(id);
		if (fatturaDaAggiornare.isPresent()) {
			Fattura modifica = fatturaDaAggiornare.get();
			modifica.setNumeroFattura(fattura.getNumeroFattura());
			modifica.setAnno(fattura.getAnno());
			modifica.setData(fattura.getData());
			modifica.setImporto(fattura.getImporto());
			modifica.setStatoFattura(fattura.getStatoFattura());
			modifica.setCliente(fattura.getCliente());
			
			return fatturaRepository.save(modifica);
		} else {
			throw new FatturaException("Fattura non aggiornata!");
		}
	}

	public Page<Fattura> findByClienteRagioneSocialeLike(Pageable pageable, String nome){
        return fatturaRepository.findByClienteRagioneSocialeLike(pageable, nome);
    }


	
    public Page<Fattura> findByStatoFattura(Pageable pageable, StatoFattura stato){
        return fatturaRepository.findByStatoFattura(pageable, stato);
    }

    public Page<Fattura> findByDataFattura(Pageable pageable, Date data){
        return fatturaRepository.findByData(pageable, data);
    }

    public Page<Fattura> findByAnnoFattura(Pageable pageable, Integer anno){
        return fatturaRepository.findByAnno(pageable, anno);
    }

    public Page<Fattura> findByImportoBetween(Pageable pageable, BigDecimal importoMin, BigDecimal importoMax){
        return fatturaRepository.findByImportoBetween(pageable, importoMin, importoMax);
    }

    
    
   
}
