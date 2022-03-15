package it.be.energy.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.be.energy.model.Provincia;

public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {

	Optional<Provincia> findByNomeLike(String string);

    Optional<Provincia> findByCodProvincia(Long codice);

	public List<Provincia> findAll();
	
	
	
}
