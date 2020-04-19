package com.mutant.exercise;

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

	protected void setup() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	void contextLoads() {
	}

	@Test
	void test() throws Exception {
		setup();
		final String request = "{\n"+
				"\"dna\": " +
				"        [\"ACCTATC\",\n" +
				"         \"CTCACTT\",\n" +
				"         \"ACGCTAT\",\n" +
				"         \"ACCTACC\",\n" +
				"         \"CAATTCC\",\n" +
				"         \"CACCAAT\",\n" +
				"         \"CAACAAT\"" +
				"        ]\n" +
				"}";

		mvc.perform(MockMvcRequestBuilders.post("/mutant").contentType(MediaType.APPLICATION_JSON).content(request))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isForbidden());
	}

}