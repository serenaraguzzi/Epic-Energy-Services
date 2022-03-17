package it.be.energy.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import it.be.energy.model.Indirizzo;

public interface IndirizzoRepository extends JpaRepository<Indirizzo, Long> {

	public List<Indirizzo> findAll();

}