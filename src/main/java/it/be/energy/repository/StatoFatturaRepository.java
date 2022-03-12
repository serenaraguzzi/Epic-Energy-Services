package it.be.energy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.be.energy.model.Fattura;

public interface StatoFatturaRepository extends JpaRepository<Fattura, Long>{

}
