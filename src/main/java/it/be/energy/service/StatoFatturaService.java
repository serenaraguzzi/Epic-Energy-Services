package it.be.energy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.be.energy.repository.StatoFatturaRepository;

@Service
public class StatoFatturaService {
	
	@Autowired
	StatoFatturaRepository statoFatturaRepository;
	

}
