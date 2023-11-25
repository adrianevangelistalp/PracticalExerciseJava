package com.backend.test.account;

import com.backend.test.account.model.Account;
import com.backend.test.account.repository.AccountRepository;
import com.backend.test.account.repository.MovementRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccountIntegrationTest {
	@ServiceConnection
	private static final MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.0");

	static {
		mySQLContainer.withUrlParam("serverTimezone", "UTC")
				.withReuse(true)
				.start();
	}
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private MovementRepository movementRepository;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void givenAccount_whenRegisterPositiveMovement_thenUpdateBalanceAndMovements() throws Exception {
		// Given
		 Account account = new Account();
		 account.setCustomerId(1L);
		 account.setType("SAVINGS");
		 account.setBalance(1000.0);
		 account.setState(true);
		 Account newAccount = accountRepository.save(account);

		// When
		 mockMvc.perform(MockMvcRequestBuilders.post("/accounts/"+newAccount.getId()+"/movement")
		 		.contentType(MediaType.APPLICATION_JSON)
		 		.content("{\n" +
		 				"    \"type\": \"DEPOSIT\",\n" +
		 				"    \"amount\": 100.0\n" +
		 				"}"))
		 		.andExpect(status().isOk())
		 		.andExpect(jsonPath("$.balance", is(1100.0)));

		// Then
		mockMvc.perform(MockMvcRequestBuilders.get("/accounts/"+newAccount.getId())
						.contentType(MediaType.APPLICATION_JSON).content(""))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.balance", is(1100.0)));

		mockMvc.perform(MockMvcRequestBuilders.get("/movements")
						.contentType(MediaType.APPLICATION_JSON).content(""))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$[-1].balance", is(1100.0)));

	}

	@Test
	void givenAccount_whenRegisterNegativeMovement_thenUpdateBalanceAndMovements() throws Exception {
		// Given
		Account account = new Account();
		account.setCustomerId(1L);
		account.setType("SAVINGS");
		account.setBalance(1000.0);
		account.setState(true);
		Account newAccount = accountRepository.save(account);

		// When
		mockMvc.perform(MockMvcRequestBuilders.post("/accounts/"+newAccount.getId()+"/movement")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\n" +
								"    \"type\": \"DEPOSIT\",\n" +
								"    \"amount\": -100.0\n" +
								"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.balance", is(900.0)));

		// Then
		mockMvc.perform(MockMvcRequestBuilders.get("/accounts/"+newAccount.getId())
						.contentType(MediaType.APPLICATION_JSON).content(""))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.balance", is(900.0)));

		mockMvc.perform(MockMvcRequestBuilders.get("/movements")
						.contentType(MediaType.APPLICATION_JSON).content(""))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$[-1].balance", is(900.0)));

	}

	@Test
	void givenAccount_whenRegisterNegativeMovementWithoutEnoughBalance_thenReturnBadRequest() throws Exception {
		// Given
		Account account = new Account();
		account.setCustomerId(1L);
		account.setType("SAVINGS");
		account.setBalance(100.0);
		account.setState(true);
		Account newAccount = accountRepository.save(account);

		// When
		mockMvc.perform(MockMvcRequestBuilders.post("/accounts/"+newAccount.getId()+"/movement")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\n" +
								"    \"type\": \"DEPOSIT\",\n" +
								"    \"amount\": -100.5\n" +
								"}"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message", is("Insufficient funds")));

		// Then
		mockMvc.perform(MockMvcRequestBuilders.get("/accounts/"+newAccount.getId())
						.contentType(MediaType.APPLICATION_JSON).content(""))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.balance", is(100.0)));

	}

	@Test
	void givenAccount_whenRegisterMultiplesMovements_thenUpdateBalanceAndMovements() throws Exception {
		// Given
		Account account = new Account();
		account.setCustomerId(1L);
		account.setType("SAVINGS");
		account.setBalance(1000.0);
		account.setState(true);
		Account newAccount = accountRepository.save(account);

		// When
		mockMvc.perform(MockMvcRequestBuilders.post("/accounts/"+newAccount.getId()+"/movement")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\n" +
								"    \"type\": \"DEPOSIT\",\n" +
								"    \"amount\": -100.0\n" +
								"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.balance", is(900.0)));

		mockMvc.perform(MockMvcRequestBuilders.post("/accounts/"+newAccount.getId()+"/movement")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\n" +
								"    \"type\": \"DEPOSIT\",\n" +
								"    \"amount\": 100.0\n" +
								"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.balance", is(1000.0)));

		mockMvc.perform(MockMvcRequestBuilders.post("/accounts/"+newAccount.getId()+"/movement")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\n" +
								"    \"type\": \"DEPOSIT\",\n" +
								"    \"amount\": -1000.0\n" +
								"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.balance", is(0.0)));
		// Then
		mockMvc.perform(MockMvcRequestBuilders.get("/accounts/"+newAccount.getId())
						.contentType(MediaType.APPLICATION_JSON).content(""))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.balance", is(0.0)));


		mockMvc.perform(MockMvcRequestBuilders.get("/movements")
						.contentType(MediaType.APPLICATION_JSON).content(""))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$[-3].balance", is(900.0)))
				.andExpect(jsonPath("$[-2].balance", is(1000.0)))
				.andExpect(jsonPath("$[-1].balance", is(0.0)));
	}

}
