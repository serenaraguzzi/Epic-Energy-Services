package it.be.energy.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.be.energy.model.Fattura;
import it.be.energy.model.StatoFattura;


public interface FatturaRepository extends JpaRepository<Fattura, Long>{

	
	public List<Fattura> findAll ();
	public List<Fattura> findByClienteRagioneSocialeLike (String ragioneSociale);
	public List<Fattura> findByStatoFattura (StatoFattura statoFattura);
	public List<Fattura> findByData (Date data);
	public List<Fattura> findByAnno (Integer anno);
	public List<Fattura> findByImportoBetween (BigDecimal importoMin, BigDecimal importoMax);
	
	
}
