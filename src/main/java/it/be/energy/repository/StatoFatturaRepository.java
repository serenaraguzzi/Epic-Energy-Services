package it.be.energy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.be.energy.model.StatoFattura;

public interface StatoFatturaRepository extends JpaRepository<StatoFattura, Long> {

public List<StatoFattura> findAll();



}
