package it.be.energy;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import it.be.energy.repository.ClienteRepository;
import it.be.energy.service.ClienteService;

@SpringBootTest
@AutoConfigureMockMvc

public class TestController {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	ClienteRepository clienteRepo;

	@Mock
	ClienteService clienteService;

	@Test
	@WithMockUser
	public void loginNoBody() throws Exception {
		this.mockMvc.perform(post("http://localhost:8080/auth/login")).andExpect(status().isBadRequest());
	}

	@Test
	@WithMockUser
	final void testTrovaTuttiClienti() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/cliente/trovatutti")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	@WithAnonymousUser
	public void testTrovaTuttiClientiWhenUtenteIsAnonymous() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/cliente/trovatutti")).andDo(print())
				.andExpect(status().isUnauthorized());
	}

	@Test
	@WithAnonymousUser
	public void testTrovaTuttiComuniWhenUtenteIsAnonymous() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/comune/trovatutti")).andDo(print())
				.andExpect(status().isUnauthorized());
	}

	@Test
	@WithAnonymousUser
	public void testTrovaTutteFattureWhenUtenteIsAnonymous() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/fattura/trovatutte")).andDo(print())
				.andExpect(status().isUnauthorized());
	}

	@Test
	@WithAnonymousUser
	public void testTrovaTuttiIndirizziWhenUtenteIsAnonymous() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/indirizzo/trovatutti")).andDo(print())
				.andExpect(status().isUnauthorized());
	}

	@Test
	@WithAnonymousUser
	public void testTrovaTutteProvinceWhenUtenteIsAnonymous() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/provincia/trovatutte")).andDo(print())
				.andExpect(status().isUnauthorized());
	}

	@Test
	@WithAnonymousUser
	public void testTrovaTuttiStatiFatturaWhenUtenteIsAnonymous() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/statofattura/trovatutti")).andDo(print())
				.andExpect(status().isUnauthorized());
	}

	@Test
	@WithMockUser
	final void testTrovaTuttiComuni() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/comune/trovatutti")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	@WithMockUser
	final void testTrovaTutteFatture() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/fattura/trovatutte")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	@WithMockUser
	final void testTrovaTuttiIndirizzi() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/indirizzo/trovatutti")).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser
	final void testTrovaTutteProvince() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/provincia/trovatutte")).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser
	final void testTrovaTuttiStatiFattura() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/statofattura/trovatutti")).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser
	final void testTrovaClienteById() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/cliente/trovaclientebyid/2")).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser
	final void testTrovaComuneById() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/comune/trovacomunebyid/3")).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser
	final void testTrovaFatturaById() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/fattura/trovafatturabyid/2")).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser
	final void testTrovaIndirizzoById() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/indirizzo/trovaindirizzobyid/2")).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser
	final void testTrovaProvinciaById() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/provincia/trovaprovinciabyid/1")).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser
	final void testTrovaStatoFatturaById() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/statofattura/trovastatofatturabyid/1")).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	final void testPost() throws Exception {
		String body = "{\r\n" + "  \"tipoCliente\": \"SAS\",\r\n" + "  \"ragioneSociale\": \"PagnozziCompany\",\r\n"
				+ "  \"pIva\": \"159753a\",\r\n" + "  \"email\": \"anto@gmail.com\",\r\n"
				+ "  \"dataInserimento\": \"2020-02-12\",\r\n" + "  \"dataUltimoContatto\": \"2022-02-20\",\r\n"
				+ "  \"fatturatoAnnuale\": 1100000,\r\n" + "  \"pec\": \"amnendar@pec.it\",\r\n"
				+ "  \"telefono\": \"12345\",\r\n" + "  \"nomeContatto\": \"Antonello\",\r\n"
				+ "  \"cognomeContatto\": \"Pagnozzi\",\r\n" + "  \"numeroContatto\": \"3462100963\",\r\n"
				+ "  \"emailContatto\": \"Amnendar@gmail.com\",\r\n" + "  \"sedeLegale\": {\r\n" + "    \"id\": 1\r\n"
				+ "    },\r\n" + "  \"sedeOperativa\": {\r\n" + "    \"id\": 2\r\n" + "  },\r\n"
				+ "  \"fatture\": [\r\n" + "    {\r\n" + "      \"anno\": 2021,\r\n"
				+ "      \"data\": \"2021-12-12\",\r\n" + "      \"importo\": 188,\r\n" + "      \"numero\": 74,\r\n"
				+ "      \"stato\": \"pagata\"\r\n" + "    }\r\n" + "  ]\r\n" + "}";

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/cliente/inseriscicliente")
				.contentType(MediaType.APPLICATION_JSON).content(body)).andExpect(status().isOk()).andReturn();
	}

	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	final void testPut() throws Exception {
		String body = "{\r\n" + "  \"tipoCliente\": \"PA\",\r\n" + "  \"ragioneSociale\": \"Anaclerio&Co\",\r\n"
				+ "  \"pIva\": \"string\",\r\n" + "  \"email\": \"rick@gmail.com\",\r\n"
				+ "  \"dataInserimento\": \"2022-03-15\",\r\n" + "  \"dataUltimoContatto\": \"2022-03-15\",\r\n"
				+ "  \"pec\": \"rick@pec.com\",\r\n" + "  \"telefono\": \"3412597536\",\r\n"
				+ "  \"nomeContatto\": \"SanRick\",\r\n" + "  \"cognomeContatto\": \"DaCremona\",\r\n"
				+ "  \"telefonoContatto\": \"3472210598\",\r\n" + "  \"emailContatto\": \"SanRick@gmail.com\",\r\n"
				+ "  \"fatturatoAnnuale\": 1100000,\r\n" + "  \"sedeLegale\": {\r\n" + "    \"id\": 2\r\n"
				+ "    },\r\n" + "  \"sedeOperativa\": {\r\n" + "    \"id\": 3\r\n" + "  },\r\n"
				+ "  \"fatture\": [\r\n" + "    {\r\n" + "      \"anno\": 2021,\r\n"
				+ "      \"data\": \"2021-01-28\",\r\n" + "      \"importo\": 155,\r\n" + "      \"numero\": 159,\r\n"
				+ "      \"stato\": \"pagata\"\r\n" + "    }\r\n" + "  ]\r\n" + "}";

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/cliente/aggiornacliente/2")
				.contentType(MediaType.APPLICATION_JSON).content(body)).andExpect(status().isOk()).andReturn();
	}

	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	final void testDeleteCliente() throws Exception {
		this.mockMvc.perform(delete("http://localhost:8080/cliente/cancellacliente/1")).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	final void testDeleteFattura() throws Exception {
		this.mockMvc.perform(delete("http://localhost:8080/fattura/cancellafattura/1")).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	final void testDeleteIndirizzo() throws Exception {
		this.mockMvc.perform(delete("http://localhost:8080/indirizzo/cancellaindirizzo/4")).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	final void testDeleteStatoFattura() throws Exception {
		this.mockMvc.perform(delete("http://localhost:8080/statofattura/cancellastatofattura/3")).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "user", password = "user", roles = "USER")
	public void testDeleteClienteWhenUserIsAuthenticated() throws Exception {
		this.mockMvc.perform(delete("http://localhost:8080/cliente/cancellacliente/2")).andDo(print())
				.andExpect(status().isForbidden());
	}

	@Test
	@WithMockUser(username = "user", password = "user", roles = "USER")
	public void testDeleteFatturaWhenUserIsAuthenticated() throws Exception {
		this.mockMvc.perform(delete("http://localhost:8080/fattura/cancellafattura/2")).andDo(print())
				.andExpect(status().isForbidden());
	}

	@Test
	@WithMockUser(username = "user", password = "user", roles = "USER")
	public void testDeleteIndirizzoWhenUserIsAuthenticated() throws Exception {
		this.mockMvc.perform(delete("http://localhost:8080/indirizzo/cancellaindirizzo/2")).andDo(print())
				.andExpect(status().isForbidden());
	}

	@Test
	@WithMockUser(username = "user", password = "user", roles = "USER")
	public void testDeleteStatoFatturaWhenUserIsAuthenticated() throws Exception {
		this.mockMvc.perform(delete("http://localhost:8080/statofattura/cancellastatofattura/2")).andDo(print())
				.andExpect(status().isForbidden());
	}

	@Test
	@WithMockUser
	final void testFindAllByOrderByRagioneSociale() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/cliente/ordinaclientibyragionesociale")).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser
	final void testFindAllByOrderByDataUltimoContatto() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/cliente/ordinaclientibydataultimocontatto")).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser
	final void testFindAllByOrderByDataInserimento() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/cliente/ordinaclientibydatainserimento")).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser
	final void testFindAllByOrderByFatturatoAnnuale() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/cliente/ordinaclientibyfatturatoannuale")).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser
	final void testFindAllByOrderBySedeLegaleComuneProvinciaNome() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/cliente/ordinaclientibysedelegalecomuneprovincia"))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	@WithMockUser
	final void testFindByDataInserimento() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/cliente/trovaclientiperdatainserimento/2021-05-12"))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	@WithMockUser
	final void testFindByDataUltimoContatto() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/cliente/trovaclientiperdataultimocontatto/2022-01-08"))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	@WithMockUser
	final void testFindByFatturatoAnnuale() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/cliente/trovaclientiperfatturatoannuale/10000/10000000"))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	@WithMockUser
	final void findByRagioneSocialeContaining() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/cliente/trovaclientiperragionesociale/Kite")).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser
	final void testFindByClienteRagioneSocialeLike() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/fattura/trovafattureperragionesociale/ScassaioliSAS"))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	@WithMockUser
	final void testFindByStatoFattura() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/fattura/trovafattureperstatofattura/1")).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser
	final void testFindByDataFattura() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/fattura/trovafattureperdatafattura/2022-01-10")).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser
	final void testFindByAnnoFattura() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/fattura/trovafattureperannofattura/2022")).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser
	final void testFindByImportoBetween() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/fattura/trovafattureperrangeimporto/150/200")).andDo(print())
				.andExpect(status().isOk());
	}

}