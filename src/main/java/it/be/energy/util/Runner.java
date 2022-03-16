package it.be.energy.util;

import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalDate;
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
		initPopolaDb();
	}

	private void initPopolaDb() throws Exception {
		Indirizzo i1 = new Indirizzo();
		i1.setCap("37062");
		i1.setCivico("12");
		i1.setLocalita("Villafranca di Verona");
		i1.setVia("Via San Luca");
		i1.setComune(comuneRepository.getById(1l));
		indirizzoRepository.save(i1);

		Indirizzo i2 = new Indirizzo();
		i2.setCap("88046");
		i2.setCivico("12");
		i2.setLocalita("Bianco");
		i2.setVia("Via Campagna");
		i2.setComune(comuneRepository.getById(2l));
		indirizzoRepository.save(i2);

		Indirizzo i3 = new Indirizzo();
		i3.setCap("10030");
		i3.setCivico("52");
		i3.setLocalita("Rosso");
		i3.setVia("Via di StoCavolo");
		i3.setComune(comuneRepository.getById(3l));
		indirizzoRepository.save(i3);

		Indirizzo i4 = new Indirizzo();
		i4.setCap("12070");
		i4.setCivico("67");
		i4.setLocalita("Azzurra");
		i4.setVia("Via Vai");
		i4.setComune(comuneRepository.getById(4l));
		indirizzoRepository.save(i4);

		Indirizzo i5 = new Indirizzo();
		i5.setCap("37010");
		i5.setCivico("74");
		i5.setLocalita("Lilla");
		i5.setVia("Via DiQua");
		i5.setComune(comuneRepository.getById(5l));
		indirizzoRepository.save(i5);

		Indirizzo i6 = new Indirizzo();
		i6.setCap("00020");
		i6.setCivico("11");
		i6.setLocalita("Viola");
		i6.setVia("Via CheNeSo");
		i6.setComune(comuneRepository.getById(6l));
		indirizzoRepository.save(i6);

		Indirizzo i7 = new Indirizzo();
		i7.setCap("00030");
		i7.setCivico("13");
		i7.setLocalita("Blu");
		i7.setVia("Via DiQua Che TePorto DeLa");
		i7.setComune(comuneRepository.getById(7l));
		indirizzoRepository.save(i7);

		Indirizzo i8 = new Indirizzo();
		i8.setCap("16150");
		i8.setCivico("108");
		i8.setLocalita("Giallo");
		i8.setVia("Via del Presidentissimo");
		i8.setComune(comuneRepository.getById(8l));
		indirizzoRepository.save(i8);

		
		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		Date data = sdf.parse("2020-02-12");
		
		

	
		Cliente cliente = new Cliente();
		cliente.setRagioneSociale("TabolacciProductions");
		cliente.setPIva("ABC123");
		cliente.setEmail("tabind@gmail.com");
		cliente.setDataInserimento(LocalDate.parse("2021-03-14"));
		cliente.setDataUltimoContatto(LocalDate.parse("2022-03-14"));
		cliente.setFatturatoAnnuale(new BigDecimal("1000000"));
		cliente.setPec("tabind@pec.com");
		cliente.setTelefono("0448956244");
		cliente.setEmailContatto("alessio.tabolacci@gmail.com");
		cliente.setNomeContatto("Alessio");
		cliente.setCognomeContatto("Tabolacci");
		cliente.setNumeroContatto("3408956244");
		cliente.setSedeLegale(indirizzoRepository.getById(2l));
		cliente.setSedeOperativa(indirizzoRepository.getById(1l));
		cliente.setTipoCliente(TipoCliente.SRL);
		
		
		Cliente cliente1 = new Cliente();
		cliente1.setRagioneSociale("GuidiIndustries");
		cliente1.setPIva("DEF456");
		cliente1.setEmail("simo4president@gmail.com");
		cliente1.setDataInserimento(LocalDate.parse("2021-02-10"));
		cliente1.setDataUltimoContatto(LocalDate.parse("2022-02-10"));
		cliente1.setFatturatoAnnuale(new BigDecimal("1500000"));
		cliente1.setPec("simo@pec.com");
		cliente1.setTelefono("0228841365");
		cliente1.setEmailContatto("simone.guidi@gmail.com");
		cliente1.setNomeContatto("Simone");
		cliente1.setCognomeContatto("Guidi");
		cliente1.setNumeroContatto("3478841365");
		cliente1.setSedeLegale(indirizzoRepository.getById(4l));
		cliente1.setSedeOperativa(indirizzoRepository.getById(3l));
		cliente1.setTipoCliente(TipoCliente.PA);
		
		
		Cliente cliente2 = new Cliente();
		cliente2.setRagioneSociale("PachiKiteSurfing");
		cliente2.setPIva("GHI789");
		cliente2.setEmail("pachino@gmail.com");
		cliente2.setDataInserimento(LocalDate.parse("2021-02-22"));
		cliente2.setDataUltimoContatto(LocalDate.parse("2022-01-08"));
		cliente2.setFatturatoAnnuale(new BigDecimal("1800000"));
		cliente2.setPec("pachi@pec.com");
		cliente2.setTelefono("0147812563");
		cliente2.setEmailContatto("paco.smurf@gmail.com");
		cliente2.setNomeContatto("Pachino");
		cliente2.setCognomeContatto("Smurfe");
		cliente2.setNumeroContatto("3495211022");
		cliente2.setSedeLegale(indirizzoRepository.getById(6l));
		cliente2.setSedeOperativa(indirizzoRepository.getById(5l));
		cliente2.setTipoCliente(TipoCliente.SPA);
		
		
		Cliente cliente3 = new Cliente();
		cliente3.setRagioneSociale("ScassaioliSAS");
		cliente3.setPIva("LMN101");
		cliente3.setEmail("giuliano@gmail.com");
		cliente3.setDataInserimento(LocalDate.parse("2021-05-12"));
		cliente3.setDataUltimoContatto(LocalDate.parse("2022-03-08"));
		cliente3.setFatturatoAnnuale(new BigDecimal("1400000"));
		cliente3.setPec("giul@pec.com");
		cliente3.setTelefono("0158896236");
		cliente3.setEmailContatto("giulia.scassaio@gmail.com");
		cliente3.setNomeContatto("Giuliano");
		cliente3.setCognomeContatto("Scassaioli");
		cliente3.setNumeroContatto("3464482316");
		cliente3.setSedeLegale(indirizzoRepository.getById(7l));
		cliente3.setSedeOperativa(indirizzoRepository.getById(8l));
		cliente3.setTipoCliente(TipoCliente.SAS);
		
		
		
		clienteRepository.save(cliente);
		clienteRepository.save(cliente1);
		clienteRepository.save(cliente2);
		clienteRepository.save(cliente3);
		
		
		StatoFattura s1 = new StatoFattura();
		s1.setNome("pagata");
		
		
		StatoFattura s2 = new StatoFattura();
		s2.setNome("transazione in corso");
	
		
		StatoFattura s3 = new StatoFattura();
		s3.setNome("non pagata");
	
		
		StatoFattura s4 = new StatoFattura();
		s4.setNome("inviata al cliente");
		
		
		statoFatturaRepository.save(s1);
		statoFatturaRepository.save(s2);
		statoFatturaRepository.save(s3);
		statoFatturaRepository.save(s4);
		
	
	
		
		Fattura f = new Fattura();
		f.setNumeroFattura(40);
		f.setAnno(2021);
		f.setData(LocalDate.parse("2021-05-12"));
		f.setImporto(new BigDecimal(100));
		f.setStatoFattura(s1);
		f.setCliente(cliente3);
		
		
		Fattura f1 = new Fattura();
		f1.setNumeroFattura(51);
		f1.setAnno(2022);
		f1.setData(LocalDate.parse("2022-01-12"));
		f1.setImporto(new BigDecimal(150));
		f1.setStatoFattura(s1);
		f1.setCliente(cliente2);
		
		
		Fattura f2 = new Fattura();
		f2.setNumeroFattura(78);
		f2.setAnno(2021);
		f2.setData(LocalDate.parse("2021-01-11"));
		f2.setImporto(new BigDecimal(300));
		f2.setStatoFattura(s2);
		f2.setCliente(cliente3);
		
		
		Fattura f3 = new Fattura();
		f3.setNumeroFattura(74);
		f3.setAnno(2022);
		f3.setData(LocalDate.parse("2022-01-10"));
		f3.setImporto(new BigDecimal(130));
		f3.setStatoFattura(s1);
		f3.setCliente(cliente2);
		fatturaRepository.save(f3);
		
		Fattura f4 = new Fattura();
		f4.setNumeroFattura(11);
		f4.setAnno(2022);
		f4.setData(LocalDate.parse("2022-01-09"));
		f4.setImporto(new BigDecimal(190));
		f4.setStatoFattura(s2);
		f4.setCliente(cliente1);
		fatturaRepository.save(f4);
		
		Fattura f5 = new Fattura();
		f5.setNumeroFattura(13);
		f5.setAnno(2022);
		f5.setData(LocalDate.parse("2022-01-07"));
		f5.setImporto(new BigDecimal(250));
		f5.setStatoFattura(s4);
		f5.setCliente(cliente);
		fatturaRepository.save(f5);
		
		Fattura f6 = new Fattura();
		f6.setNumeroFattura(15);
		f6.setAnno(2022);
		f6.setData(LocalDate.parse("2022-01-05"));
		f6.setImporto(new BigDecimal(170));
		f6.setStatoFattura(s1);
		f6.setCliente(cliente1);
		fatturaRepository.save(f6);
		
		Fattura f7 = new Fattura();
		f7.setNumeroFattura(79);
		f7.setAnno(2021);
		f7.setData(LocalDate.parse("2021-01-04"));
		f7.setImporto(new BigDecimal(165));
		f7.setStatoFattura(s3);
		f7.setCliente(cliente);
		fatturaRepository.save(f7);
		
		
		
		fatturaRepository.save(f);
		fatturaRepository.save(f1);
		fatturaRepository.save(f2);
		fatturaRepository.save(f3);
		fatturaRepository.save(f4);
		fatturaRepository.save(f5);
		fatturaRepository.save(f6);
		fatturaRepository.save(f7);
		
		

		
		
		
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
