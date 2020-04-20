package com.mutant.exercise;

import com.mutant.exercise.exception.DNAValidationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;


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
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * @throws Exception if something went wrong
	 * @test sequence with several sequences horizontal and vertical
	 */
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
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * @throws Exception if something went wrong
	 * @test sequence with 2 sequences horizontal
	 */
	@Test
	void testMutant03() throws Exception {
		setup();
		final String request = "{\n"+
				"\"dna\": " +
				"        [\"ATGCGA\",\n" +
				"         \"CAGTGC\",\n" +
				"         \"TTTTGT\",\n" +
				"         \"AGAAGG\",\n" +
				"         \"CCCCTA\",\n" +
				"         \"TCACTG\"" +
				"        ]\n" +
				"}";

		mvc.perform(MockMvcRequestBuilders.post("/mutant").contentType(MediaType.APPLICATION_JSON).content(request))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * @throws Exception if something went wrong
	 * @test sequence with 2 sequences vertical
	 */
	@Test
	void testMutant04() throws Exception {
		setup();
		final String request = "{\n"+
				"\"dna\": " +
				"        [\"ATGTGA\",\n" +
				"         \"AAGTGC\",\n" +
				"         \"ATTTGT\",\n" +
				"         \"AGATGG\",\n" +
				"         \"CTACTA\",\n" +
				"         \"TCACTG\"" +
				"        ]\n" +
				"}";

		mvc.perform(MockMvcRequestBuilders.post("/mutant").contentType(MediaType.APPLICATION_JSON).content(request))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * @throws Exception if something went wrong
	 * @test sequence with 2 sequences diagonalUp
	 */
	@Test
	void testMutant05() throws Exception {
		setup();
		final String request = "{\n"+
				"\"dna\": " +
				"        [\"ATGAGA\",\n" +
				"         \"CAATGC\",\n" +
				"         \"TAATGA\",\n" +
				"         \"AGAAAG\",\n" +
				"         \"CTCATA\",\n" +
				"         \"TCACTG\"" +
				"        ]\n" +
				"}";

		mvc.perform(MockMvcRequestBuilders.post("/mutant").contentType(MediaType.APPLICATION_JSON).content(request))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * @throws Exception if something went wrong
	 * @test sequence with 2 sequences diagonalDown
	 */
	@Test
	void testMutant06() throws Exception {
		setup();
		final String request = "{\n"+
				"\"dna\": " +
				"        [\"ATGAGA\",\n" +
				"         \"CAATGC\",\n" +
				"         \"CTATGT\",\n" +
				"         \"ACAAAG\",\n" +
				"         \"CTCATA\",\n" +
				"         \"TCACTG\"" +
				"        ]\n" +
				"}";

		mvc.perform(MockMvcRequestBuilders.post("/mutant").contentType(MediaType.APPLICATION_JSON).content(request))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * @throws Exception if something went wrong
	 * @test sequence with 1 sequences horizontal and 1 diagonalDown
	 */
	@Test
	void testMutant07() throws Exception {
		setup();
		final String request = "{\n"+
				"\"dna\": " +
				"        [\"ATGAGA\",\n" +
				"         \"CAATGC\",\n" +
				"         \"CTATGT\",\n" +
				"         \"ACAAAG\",\n" +
				"         \"CTTATA\",\n" +
				"         \"TTTTTG\"" +
				"        ]\n" +
				"}";

		mvc.perform(MockMvcRequestBuilders.post("/mutant").contentType(MediaType.APPLICATION_JSON).content(request))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * @throws Exception if something went wrong
	 * @test sequence with 1 sequences vertical and 1 diagonalUp
	 */
	@Test
	void testMutant08() throws Exception {
		setup();
		final String request = "{\n"+
				"\"dna\": " +
				"        [\"ATGAGA\",\n" +
				"         \"AAATGC\",\n" +
				"         \"AAATGT\",\n" +
				"         \"ACATAG\",\n" +
				"         \"CTTATA\",\n" +
				"         \"TTTATG\"" +
				"        ]\n" +
				"}";

		mvc.perform(MockMvcRequestBuilders.post("/mutant").contentType(MediaType.APPLICATION_JSON).content(request))
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
				.andExpect(MockMvcResultMatchers.status().isForbidden());
	}

	@Test
	void testHuman03() throws Exception {
		setup();
		final String request = "{\n"+
				"\"dna\": " +
				"        [\"ACCTA\",\n" +
				"         \"CCCCC\",\n" +
				"         \"ACGCT\",\n" +
				"         \"AACTA\",\n" +
				"         \"CAAAG\"" +
				"        ]\n" +
				"}";

		mvc.perform(MockMvcRequestBuilders.post("/mutant").contentType(MediaType.APPLICATION_JSON).content(request))
				.andExpect(MockMvcResultMatchers.status().isForbidden());
	}


	/**
	 * @throws Exception if something went wrong
	 * @test sequence with only 1 sequence horizontal
	 */
	@Test
	void testHuman04() throws Exception {
		setup();
		final String request = "{\n"+
				"\"dna\": " +
				"        [\"AAAAC\",\n" +
				"         \"GATCT\",\n" +
				"         \"TGACT\",\n" +
				"         \"TCTGC\",\n" +
				"         \"ATTGT\"" +
				"        ]\n" +
				"}";

		mvc.perform(MockMvcRequestBuilders.post("/mutant").contentType(MediaType.APPLICATION_JSON).content(request))
				.andExpect(MockMvcResultMatchers.status().isForbidden());
	}

	/**
	 * @throws Exception if something went wrong
	 * @test sequence with only 1 sequence vertical
	 */
	@Test
	void testHuman05() throws Exception {
		setup();
		final String request = "{\n"+
				"\"dna\": " +
				"        [\"AACAC\",\n" +
				"         \"GATCT\",\n" +
				"         \"TGACT\",\n" +
				"         \"TCTGT\",\n" +
				"         \"ATTGT\"" +
				"        ]\n" +
				"}";

		mvc.perform(MockMvcRequestBuilders.post("/mutant").contentType(MediaType.APPLICATION_JSON).content(request))
				.andExpect(MockMvcResultMatchers.status().isForbidden());
	}

	/**
	 * @throws Exception if something went wrong
	 * @test sequence with only 1 sequence diagonal up
	 */
	@Test
	void testHuman06() throws Exception {
		setup();
		final String request = "{\n"+
				"\"dna\": " +
				"        [\"AACAC\",\n" +
				"         \"GATCT\",\n" +
				"         \"TGCCT\",\n" +
				"         \"TCTGC\",\n" +
				"         \"ATTGT\"" +
				"        ]\n" +
				"}";

		mvc.perform(MockMvcRequestBuilders.post("/mutant").contentType(MediaType.APPLICATION_JSON).content(request))
				.andExpect(MockMvcResultMatchers.status().isForbidden());
	}

	/**
	 * @throws Exception if something went wrong
	 * @test sequence with only 1 diagonal down
	 */
	@Test
	void testHuman07() throws Exception {
		setup();
		final String request = "{\n"+
				"\"dna\": " +
				"        [\"AACAC\",\n" +
				"         \"GATCT\",\n" +
				"         \"TGACT\",\n" +
				"         \"TCGGC\",\n" +
				"         \"ATTGT\"" +
				"        ]\n" +
				"}";

		mvc.perform(MockMvcRequestBuilders.post("/mutant").contentType(MediaType.APPLICATION_JSON).content(request))
				.andExpect(MockMvcResultMatchers.status().isForbidden());
	}

	/**
	 * @throws Exception if something went wrong
	 * @test validation matching
	 */
	@Test
	void testLimitValidation() throws Exception {
		setup();
		final String request = "{\n"+
				"\"dna\": " +
				"        [\"ACCTA\",\n" +
				"         \"CCCCC\",\n" +
				"         \"CAAAG\"" +
				"        ]\n" +
				"}";

		final ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.post("/mutant").contentType(MediaType.APPLICATION_JSON).content(request))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());

		final String result = resultActions.andReturn().getResponse().getContentAsString();
		assertThat(result.contains(DNAValidationException.errorSize)).isTrue();
	}


	@Test
	void testMatchValidation() throws Exception {
		setup();
		final String request = "{\n"+
				"\"dna\": " +
				"        [\"ADCTA\",\n" +
				"         \"CCCCC\",\n" +
				"         \"ACGCT\",\n" +
				"         \"AACTA\",\n" +
				"         \"CAABB\"" +
				"        ]\n" +
				"}";

		final ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.post("/mutant").contentType(MediaType.APPLICATION_JSON).content(request))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());

		final String result = resultActions.andReturn().getResponse().getContentAsString();
		assertThat(result.contains(DNAValidationException.errorMatch)).isTrue();
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	void testPatternOk() throws Exception {
		setup();
		final String request = "{\n"+
				"\"dna\": " +
				"        [\"aTcTA\",\n" +
				"         \"ACcCC\",\n" +
				"         \"ACGCT\",\n" +
				"         \"AACTA\",\n" +
				"         \"CAATt\"" +
				"        ]\n" +
				"}";

		mvc.perform(MockMvcRequestBuilders.post("/mutant").contentType(MediaType.APPLICATION_JSON).content(request))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

}