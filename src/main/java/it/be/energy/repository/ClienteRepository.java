package it.be.energy.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.be.energy.model.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	
//	public Page<Cliente> findAll(Pageable pageable);
//	
//	public Page<Cliente> findAllByOrderByFatturatoAnnuale(Pageable pageable);
//
//	public Page<Cliente> findAllByOrderByDataInserimento(Pageable pageable);
//
//	public Page<Cliente> findAllByOrderByDataUltimoContatto(Pageable pageable);
//
//	public Page<Cliente> findAllByOrderByRagioneSociale(Pageable pageable);
//
//	public Page<Cliente> findAllByOrderBySedeLegaleComuneProvinciaNome(Pageable pageable);
	
	
	public List<Cliente> findAll();
	
	public List<Cliente> findAllByOrderByFatturatoAnnuale();

	public List<Cliente> findAllByOrderByDataInserimento();

	public List<Cliente> findAllByOrderByDataUltimoContatto();

	public List<Cliente> findAllByOrderByRagioneSociale();

	public List<Cliente> findAllByOrderBySedeLegaleComuneProvinciaNome();
	
	

	public List<Cliente> findByRagioneSocialeContaining(String fisso);

	
	//@Query("SELECT c FROM Cliente c WHERE c.dataInserimento=:dataInserimento")
	public List<Cliente> findByDataInserimento(Date data);

	@Query("SELECT c FROM Cliente c WHERE c.dataUltimoContatto=:dataUltimoContatto")
	public List<Cliente> findByDataUltimoContatto(Date data);

	@Query("SELECT c FROM Cliente c WHERE c.fatturatoAnnuale BETWEEN :fatturatoMin AND :fatturatoMax")
	public List<Cliente> findByFatturatoAnnuale(BigDecimal fatturatoMin, BigDecimal fatturatoMax);



	

	
	

}
