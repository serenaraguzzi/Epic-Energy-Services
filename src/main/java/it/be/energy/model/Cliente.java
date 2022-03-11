package it.be.energy.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String ragioneSociale;
	private String pIva;
	private String email;
	private LocalDate dataInserimento;
	private LocalDate dataUtimoContatto;
	private BigDecimal fatturatoAnnuale;
	private String pec;
	private String telefono;
	private String emailContatto;
	private String nomeContatto;
	private String cognomeContatto;
	private String numeroContatto;
	private Indirizzo sedeLegale;
	private Indirizzo sedeOperativa;
	private TipoCliente tipoCliente;
	@OneToMany(mappedBy = "cliente")
	List<Fattura> fatture;
	
	
	
	
	
	
	
	
	
	
	
}
