package it.be.energy.controller;

import java.math.BigDecimal;
import java.util.Date;
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


@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/fattura")
public class FatturaController {
	
	
	@Autowired
	FatturaService fatturaService;
	
	
	@Operation(summary = "Trova tutte le fatture", description = "Trova tutte le fatture")
	@GetMapping(value = "/trovatutte")
	public ResponseEntity<Page<Fattura>> trovaTutte(Pageable pageable) {
		Page<Fattura> trovaTutte = fatturaService.findAll(pageable);
		if (trovaTutte.hasContent()) {
			return new ResponseEntity<>(trovaTutte, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}


	
	@Operation(summary = "Trova una fattura con un id", description = "Trova una fattura con un id")
	@GetMapping(value = "/trovafatturabyid")
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
	@PutMapping(value = "/aggiornacliente", produces = MediaType.APPLICATION_JSON_VALUE)
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
    public ResponseEntity<Page<Fattura>> findByClienteRagioneSocialeLike(Pageable pageable, String nome) {
        Page<Fattura> trovate = fatturaService.findByClienteRagioneSocialeLike(pageable, nome); 
        if(trovate.hasContent()) {
            return new ResponseEntity<>(trovate , HttpStatus.OK);
        }else {
            return new ResponseEntity<>(trovate , HttpStatus.NO_CONTENT);
        }
    }

   
    @GetMapping("/trovafattureperstatofattura")
    @Operation(summary = "Trova una fattura per stato fattura", description = "Trova una fattura per stato fattura")
    public ResponseEntity<Page<Fattura>> findByStatoFattura(Pageable pageable, StatoFattura stato) {
        Page<Fattura> trovate = fatturaService.findByStatoFattura(pageable, stato); 
        if(trovate.hasContent()) {
            return new ResponseEntity<>(trovate , HttpStatus.OK);
        }else {
            return new ResponseEntity<>(trovate , HttpStatus.NO_CONTENT);
        }

    }
    
    @GetMapping("/trovafattureperdatafattura")
    @Operation(summary = "Trova una fattura per data fattura", description = "Trova una fattura per data fattura")
    public ResponseEntity<Page<Fattura>> findByDataFattura(Pageable pageable, Date data) {
        Page<Fattura> trovate = fatturaService.findByDataFattura(pageable, data); 
        if(trovate.hasContent()) {
            return new ResponseEntity<>(trovate , HttpStatus.OK);
        }else {
            return new ResponseEntity<>(trovate , HttpStatus.NO_CONTENT);
        }
    }
	
      
        @GetMapping("/trovafattureperannofattura")
        @Operation(summary = "Trova una fattura per anno fattura", description = "Trova una fattura per anno fattura")
        public ResponseEntity<Page<Fattura>> findByAnnoFattura(Pageable pageable, Integer anno) {
            Page<Fattura> trovate = fatturaService.findByAnnoFattura(pageable, anno); 
            if(trovate.hasContent()) {
                return new ResponseEntity<>(trovate , HttpStatus.OK);
            }else {
                return new ResponseEntity<>(trovate , HttpStatus.NO_CONTENT);
            }

        }

      
        @GetMapping("/trovafattureperrangeimporto")
        @Operation(summary = "Trova unafattura per range importo", description = "Trova unafattura per range importo")
        public ResponseEntity<Page<Fattura>> findByImportoBetween(Pageable pageable, BigDecimal importoMin, BigDecimal importoMax) {
            Page<Fattura> trovate = fatturaService.findByImportoBetween(pageable, importoMin, importoMax); 
            if(trovate.hasContent()) {
                return new ResponseEntity<>(trovate , HttpStatus.OK);
            }else {
                return new ResponseEntity<>(trovate , HttpStatus.NO_CONTENT);
            }
        }
	
	
	
	
}