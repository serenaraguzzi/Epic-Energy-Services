package it.be.energy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.be.energy.model.Comune;


public interface ComuneRepository extends JpaRepository<Comune, Long>{

	public List<Comune> findAll();

}
