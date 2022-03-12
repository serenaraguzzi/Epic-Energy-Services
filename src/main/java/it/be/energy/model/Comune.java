package it.be.energy.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comune {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codProgComune;
	private String nome;
	@ManyToOne
	private Provincia provincia;
	@OneToMany(mappedBy = "comune")
	private List<Indirizzo> indirizzi;
	
}
