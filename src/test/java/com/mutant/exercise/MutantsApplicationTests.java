package com.mutant.exercise;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@SpringBootTest
class MutantsApplicationTests {

	protected MockMvc mvc;

	@Autowired
	protected WebApplicationContext wac;

	protected void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	void contextLoads() {
	}

	@Test
	void testMutant01() throws Exception {
		setup();
		final String request = "{\n"+
				"\"dna\": " +
				"        [\"ATGCGA\",\n" +
				"         \"CAGTGC\",\n" +
				"         \"TTATGT\",\n" +
				"         \"AGAAGG\",\n" +
				"         \"CCCCTA\",\n" +
				"         \"TCACTG\"" +
				"        ]\n" +
				"}";

		mvc.perform(MockMvcRequestBuilders.post("/mutant").contentType(MediaType.APPLICATION_JSON).content(request))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testMutant02() throws Exception {
		setup();
		final String request = "{\n"+
				"\"dna\": " +
				"        [\"ATGCGAATGCGA\",\n" +
				"         \"CAGTGCCAGTGC\",\n" +
				"         \"CAGTGCCAGTGC\",\n" +
				"         \"CAGTGCCAGTGC\",\n" +
				"         \"TTATGTTTATGT\",\n" +
				"         \"TTATGTTTATGT\",\n" +
				"         \"TTATGTTTATGT\",\n" +
				"         \"TTATGTTTATGT\",\n" +
				"         \"TTATGTTTATGT\",\n" +
				"         \"AGAAGGAGAAGG\",\n" +
				"         \"CCCCTACCCCTA\",\n" +
				"         \"TCACTGTCACTG\"" +
				"        ]\n" +
				"}";

		mvc.perform(MockMvcRequestBuilders.post("/mutant").contentType(MediaType.APPLICATION_JSON).content(request))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testHuman01() throws Exception {
		setup();
		final String request = "{\n"+
				"\"dna\": " +
				"        [\"ACCTA\",\n" +
				"         \"CTCAC\",\n" +
				"         \"ACGCT\",\n" +
				"         \"ACCTA\",\n" +
				"         \"CAACA\"" +
				"        ]\n" +
				"}";

		mvc.perform(MockMvcRequestBuilders.post("/mutant").contentType(MediaType.APPLICATION_JSON).content(request))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isForbidden());
	}

	@Test
	void testHuman02() throws Exception {
		setup();
		final String request = "{\n"+
				"\"dna\": " +
				"        [\"ACCTAAT\",\n" +
				"         \"CTCACTA\",\n" +
				"         \"CGGCTAT\",\n" +
				"         \"ACCAATA\",\n" +
				"         \"ATCTCTA\",\n" +
				"         \"ACCTATA\",\n" +
				"         \"CAACAAT\"" +
				"        ]\n" +
				"}";

		mvc.perform(MockMvcRequestBuilders.post("/mutant").contentType(MediaType.APPLICATION_JSON).content(request))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isForbidden());
	}



}