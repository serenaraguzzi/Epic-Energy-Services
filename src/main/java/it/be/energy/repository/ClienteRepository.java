package it.be.energy.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.be.energy.model.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	public Page<Cliente> findAll(Pageable pageable);
	
	

	public Page<Cliente> findAllByOrderByFatturatoAnnuale(Pageable pageable);

	public Page<Cliente> findAllByOrderByDataInserimento(Pageable pageable);

	public Page<Cliente> findAllByOrderByDataUltimoContatto(Pageable pageable);

	public Page<Cliente> findAllByOrderByRagioneSociale(Pageable pageable);

	//public Page<Cliente> findAllByOrderBySedeLegaleProvincia(Pageable pageable);
	
	
	
	

	public List<Cliente> findByRagioneSocialeContaining(String fisso);

	
	@Query("SELECT c FROM Cliente c WHERE c.dataInserimento=:dataInserimento")
	public List<Cliente> findByDataInserimento(LocalDate dataInserimento);

	@Query("SELECT c FROM Cliente c WHERE c.dataUltimoContatto=:dataUltimoContatto")
	public List<Cliente> findByDataUltimoContatto(LocalDate dataUltimoContatto);

	@Query("SELECT c FROM Cliente c WHERE c.fatturatoAnnuale BETWEEN :fatturatoMin AND :fatturatoMax")
	public List<Cliente> findByFatturatoAnnuale(BigDecimal fatturatoMin, BigDecimal fatturatoMax);

	
	

}
