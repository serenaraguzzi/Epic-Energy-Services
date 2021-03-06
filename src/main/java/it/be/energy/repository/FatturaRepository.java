package it.be.energy.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import it.be.energy.model.Fattura;

public interface FatturaRepository extends JpaRepository<Fattura, Long> {

	public List<Fattura> findAll();

	public List<Fattura> findByClienteRagioneSocialeLike(String ragioneSociale);

	public List<Fattura> findByStatoFatturaId(Long id);

	public List<Fattura> findByData(LocalDate data);

	public List<Fattura> findByAnno(Integer anno);

	public List<Fattura> findByImportoBetween(BigDecimal importoMin, BigDecimal importoMax);

}