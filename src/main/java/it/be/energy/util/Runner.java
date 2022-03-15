package it.be.energy.util;

import java.io.FileReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;

import it.be.energy.model.Cliente;
import it.be.energy.model.Comune;
import it.be.energy.model.Fattura;
import it.be.energy.model.Indirizzo;
import it.be.energy.model.Provincia;
import it.be.energy.model.StatoFattura;
import it.be.energy.model.TipoCliente;
import it.be.energy.repository.ClienteRepository;
import it.be.energy.repository.ComuneRepository;
import it.be.energy.repository.FatturaRepository;
import it.be.energy.repository.IndirizzoRepository;
import it.be.energy.repository.ProvinciaRepository;
import it.be.energy.repository.StatoFatturaRepository;

@Component
public class Runner implements CommandLineRunner {

	@Autowired
	ProvinciaRepository provinciaRepository;

	@Autowired
	ComuneRepository comuneRepository;

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	FatturaRepository fatturaRepository;

	@Autowired
	IndirizzoRepository indirizzoRepository;

	@Autowired
	StatoFatturaRepository statoFatturaRepository;

	@Override
	public void run(String... args) throws Exception {
		initComune();
		initProvincia();
		initIndirizzo();
		initStatoFattura();
		initFattura();
		initClienti();

	}

	private void initIndirizzo() throws Exception {
		Indirizzo i1 = new Indirizzo();
		i1.setCap("26020");
		i1.setCivico("30");
		i1.setLocalita("Campagna");
		i1.setVia("bizantini");
		i1.setComune(comuneRepository.getById(1l));
		indirizzoRepository.save(i1);

		Indirizzo i2 = new Indirizzo();
		i2.setCap("26000");
		i2.setCivico("12");
		i2.setLocalita("Campaglknlkna");
		i2.setVia("bizagsgtini");
		i2.setComune(comuneRepository.getById(2l));
		indirizzoRepository.save(i2);

		Indirizzo i3 = new Indirizzo();
		i2.setCap("26010");
		i2.setCivico("52");
		i2.setLocalita("stocavolo");
		i2.setVia("via de qua");
		i2.setComune(comuneRepository.getById(3l));
		indirizzoRepository.save(i3);

		Indirizzo i4 = new Indirizzo();
		i2.setCap("56124");
		i2.setCivico("69");
		i2.setLocalita("sticavoli");
		i2.setVia("ds");
		i2.setComune(comuneRepository.getById(4l));
		indirizzoRepository.save(i4);

		Indirizzo i5 = new Indirizzo();
		i2.setCap("56124");
		i2.setCivico("69");
		i2.setLocalita("sticavoli");
		i2.setVia("ds");
		i2.setComune(comuneRepository.getById(5l));
		indirizzoRepository.save(i5);

		Indirizzo i6 = new Indirizzo();
		i2.setCap("56124");
		i2.setCivico("69");
		i2.setLocalita("sticavoli");
		i2.setVia("ds");
		i2.setComune(comuneRepository.getById(6l));
		indirizzoRepository.save(i6);

		Indirizzo i7 = new Indirizzo();
		i2.setCap("56124");
		i2.setCivico("69");
		i2.setLocalita("sticavoli");
		i2.setVia("ds");
		i2.setComune(comuneRepository.getById(7l));
		indirizzoRepository.save(i7);

		Indirizzo i8 = new Indirizzo();
		i2.setCap("56124");
		i2.setCivico("69");
		i2.setLocalita("sticavoli");
		i2.setVia("ds");
		i2.setComune(comuneRepository.getById(8l));
		indirizzoRepository.save(i8);
	}

	private void initStatoFattura() throws Exception {

		StatoFattura s1 = new StatoFattura();
		s1.setNome("pagata");
		statoFatturaRepository.save(s1);
	
	}

	private void initFattura() throws Exception {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date data = sdf.parse("2020-02-12");
	Fattura f = new Fattura();
	f.setNumeroFattura(12);
	f.setAnno(2002);
	f.setData(data);
	f.setImporto(new BigDecimal(100));
	//f.setCliente(cliente);
	//f.setStatoFattura();

	fatturaRepository.save(f);
	
	
	
	
	}
	private void initClienti() throws Exception {

		Cliente cliente = new Cliente();
		cliente.setRagioneSociale("Tabolacci Industries");
		cliente.setPIva("ABC123");
		cliente.setEmail("tabind@gmail.com");
		cliente.setDataInserimento(LocalDate.parse("2022-03-14"));
		cliente.setDataUltimoContatto(LocalDate.parse("2022-03-14"));
		cliente.setFatturatoAnnuale(new BigDecimal("1000000"));
		cliente.setPec("tabind@pec.com");
		cliente.setTelefono("3401863101");
		cliente.setEmailContatto("alessio.tabolacci@gmail.com");
		cliente.setNomeContatto("Alessio");
		cliente.setCognomeContatto("Tabolacci");
		cliente.setNumeroContatto("123456");
		cliente.setSedeLegale(indirizzoRepository.getById(1l));
		cliente.setSedeOperativa(indirizzoRepository.getById(2l));
		cliente.setTipoCliente(TipoCliente.SRL);
		//cliente.setFatture(null);
		clienteRepository.save(cliente);

	
		
		
	}


	private void initComune() throws Exception {
		try (CSVReader csvReader = new CSVReader(new FileReader("comuni-italiani_1.csv"));) {
			String[] values = null;
			csvReader.readNext();
			Optional<Provincia> p;
			String[] valore;
			Provincia provincia;
			while ((values = csvReader.readNext()) != null) {
				valore = values[0].split(";");
				p = provinciaRepository.findByCodProvincia(Long.valueOf(valore[0]));
				if (p.isPresent()) {
					comuneRepository.save(new Comune(rimpiazza(valore[2]), p.get()));
				} else {

					provincia = new Provincia();
					provincia.setCodProvincia(Long.valueOf(valore[0]));
					provincia.setNome(rimpiazza(valore[3]));
					provinciaRepository.save(provincia);
					comuneRepository.save(new Comune(valore[2], provincia));
				}
			}
		}
	}

	private void initProvincia() throws Exception {
		try (CSVReader csvReader = new CSVReader(new FileReader("province-italiane_1.csv"));) {
			String[] values = null;
			csvReader.readNext(); //
			Optional<Provincia> pr;
			Provincia provincia;
			String nome;
			String[] valore;
			while ((values = csvReader.readNext()) != null) {
				valore = values[0].split(";");
				nome = valore[1];
				pr = provinciaRepository.findByNomeLike("%" + nome + "%");
				if (pr.isPresent()) {

					provincia = pr.get();
					provincia.setSigla(valore[0]);
					provincia.setRegione(valore[2]);
					provinciaRepository.save(provincia);
				} else {

					provinciaRepository.save(new Provincia(valore[0], valore[1], valore[2]));
				}
			}
		}
	}

	private String rimpiazza(String nome) {
		return nome.replace('-', ' ');

	}

}
