package it.be.energy.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.be.energy.exception.FatturaException;
import it.be.energy.model.Fattura;
import it.be.energy.model.StatoFattura;
import it.be.energy.service.FatturaService;
import lombok.extern.slf4j.Slf4j;


@SecurityRequirement(name = "bearerAuth")
@Slf4j
@RestController
@RequestMapping("/fattura")
public class FatturaController {
	
	
	@Autowired
	FatturaService fatturaService;
	
	
	@Operation(summary = "Trova tutte le fatture", description = "Trova tutte le fatture")
	@GetMapping(value = "/trovatutte")
	public ResponseEntity<List<Fattura>> trovaTutte() {
		List<Fattura> trovaTutte = fatturaService.findAll();
		if (!trovaTutte.isEmpty()) {
			return new ResponseEntity<>(trovaTutte, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}


	
	@Operation(summary = "Trova una fattura con un id", description = "Trova una fattura con un id")
	@GetMapping(value = "/trovafatturabyid/{id}")
	public ResponseEntity<Fattura> trovaById(@PathVariable Long id) throws FatturaException {
		Optional<Fattura> fatturaTrovata = fatturaService.trovaFatturaById(id);
		if (fatturaTrovata.isPresent()) {
			return new ResponseEntity<>(fatturaTrovata.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Inserisci una fattura", description = "Inserire una fattura")
	@PostMapping(value = "/inseriscifattura", produces = MediaType.APPLICATION_JSON_VALUE)
	public String addFattura(@RequestBody Fattura fattura) {
		fatturaService.inserisciFattura(fattura);
		return "Fattura salvata!";
	

	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Aggiorna una fattura", description = "Aggiorna una fattura")
	@PutMapping(value = "/aggiornacliente/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String updateFattura(@RequestBody Fattura fattura, @PathVariable Long id) throws FatturaException {
		fatturaService.modificaFattura(fattura, id);
		return "Aggiornamento effettuato!";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Cancella una fattura", description = "Cancella una fattura")
	@DeleteMapping("/cancellacliente")
	public String deleteFattura(@RequestParam Long id) {
		fatturaService.cancellaFatturaById(id);
		return "Fattura cancellata!";

	}
	
	
	
	
	
	
	
	
    @GetMapping("/trovafattureperragionesociale")
    @Operation(summary = "Trova una fattura per ragione sociale", description = "Trova una fattura per ragione sociale")
    public ResponseEntity<List<Fattura>> findByClienteRagioneSocialeLike(String nome) {
        List<Fattura> trovate = fatturaService.findByClienteRagioneSocialeLike(nome); 
        if(!trovate.isEmpty()) {
            return new ResponseEntity<>(trovate , HttpStatus.OK);
        }else {
            return new ResponseEntity<>(trovate , HttpStatus.NO_CONTENT);
        }
    }

   
    @GetMapping("/trovafattureperstatofattura")
    @Operation(summary = "Trova una fattura per stato fattura", description = "Trova una fattura per stato fattura")
    public ResponseEntity<List<Fattura>> findByStatoFattura(StatoFattura statoFattura) {
        List<Fattura> trovate = fatturaService.findByStatoFattura(statoFattura); 
        if(!trovate.isEmpty()) {
            return new ResponseEntity<>(trovate , HttpStatus.OK);
        }else {
            return new ResponseEntity<>(trovate , HttpStatus.NO_CONTENT);
        }

    }
    
    @GetMapping("/trovafattureperdatafattura")
    @Operation(summary = "Trova una fattura per data fattura", description = "Trova una fattura per data fattura")
    public ResponseEntity<List<Fattura>> findByDataFattura(Date data) {
        List<Fattura> trovate = fatturaService.findByDataFattura(data); 
        if(!trovate.isEmpty()) {
            return new ResponseEntity<>(trovate , HttpStatus.OK);
        }else {
            return new ResponseEntity<>(trovate , HttpStatus.NO_CONTENT);
        }
    }
	
      
        @GetMapping("/trovafattureperannofattura")
        @Operation(summary = "Trova una fattura per anno fattura", description = "Trova una fattura per anno fattura")
        public ResponseEntity<List<Fattura>> findByAnnoFattura(Integer anno) {
            List<Fattura> trovate = fatturaService.findByAnnoFattura(anno); 
            if(!trovate.isEmpty()) {
                return new ResponseEntity<>(trovate , HttpStatus.OK);
            }else {
                return new ResponseEntity<>(trovate , HttpStatus.NO_CONTENT);
            }

        }

      
        @GetMapping("/trovafattureperrangeimporto")
        @Operation(summary = "Trova una fattura per range importo", description = "Trova unafattura per range importo")
        public ResponseEntity<List<Fattura>> findByImportoBetween(BigDecimal importoMin, BigDecimal importoMax) {
            List<Fattura> trovate = fatturaService.findByImportoBetween(importoMin, importoMax); 
            if(!trovate.isEmpty()) {
                return new ResponseEntity<>(trovate , HttpStatus.OK);
            }else {
                return new ResponseEntity<>(trovate , HttpStatus.NO_CONTENT);
            }
        }
	
	
	
	
}