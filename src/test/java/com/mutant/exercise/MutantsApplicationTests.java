package com.mutant.exercise;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mutant.exercise.exception.DNAValidationException;
import com.mutant.exercise.model.DNA;
import com.mutant.exercise.model.DNAStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
class MutantsApplicationTests {

	protected MockMvc mvc;

	@Autowired
	protected WebApplicationContext wac;

	@Autowired
	ObjectMapper objectMapper;

	@BeforeEach
	protected void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	void contextLoads() {
	}

    /**
     * @throws Exception if something went wrong
     * @test sequence example MercadoLibreExam
     */
	@Test
	void testMutantMeliExample() throws Exception {
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

        isMutantRequest(request, status().isOk());
    }

	/**
	 * @throws Exception if something went wrong
	 * @test sequence with several sequences horizontal and vertical
	 */
	@Test
	void testMutantHoriontalAndVertical() throws Exception {
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

        isMutantRequest(request, status().isOk());
    }

	/**
	 * @throws Exception if something went wrong
	 * @test sequence with 2 sequences horizontal
	 */
	@Test
	void testMutantTwoHorizontals() throws Exception {
		final String request = "{\n"+
				"\"dna\": " +
				"        [\"ATGCGA\",\n" +
				"         \"CAGTGC\",\n" +
				"         \"TTTTGT\",\n" +
				"         \"AGAAGG\",\n" +
				"         \"AACCCC\",\n" +
				"         \"TCACTG\"" +
				"        ]\n" +
				"}";

        isMutantRequest(request, status().isOk());
    }

	/**
	 * @throws Exception if something went wrong
	 * @test sequence with 2 sequences vertical
	 */
	@Test
	void testMutantTwoVerticals() throws Exception {
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

        isMutantRequest(request, status().isOk());
    }

	/**
	 * @throws Exception if something went wrong
	 * @test sequence with 2 sequences diagonalUp
	 */
	@Test
	void testMutantTwoDiagonalUp() throws Exception {
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

        isMutantRequest(request, status().isOk());
    }

	/**
	 * @throws Exception if something went wrong
	 * @test sequence with 2 sequences diagonalDown
	 */
	@Test
	void testMutantTwoDiagonalDown() throws Exception {
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

        isMutantRequest(request, status().isOk());
    }

	/**
	 * @throws Exception if something went wrong
	 * @test sequence with 1 sequences horizontal and 1 diagonalDown
	 */
	@Test
	void testMutantHorizontalAndDiagonalDown() throws Exception {
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

        isMutantRequest(request, status().isOk());
    }

	/**
	 * @throws Exception if something went wrong
	 * @test sequence with 1 sequences vertical and 1 diagonalUp
	 */
	@Test
	void testMutantVerticalAndDiagonalUp() throws Exception {
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

        isMutantRequest(request, status().isOk());
    }

	/**
	 * @throws Exception if something went wrong
	 * @test 2 sequences of the same adn (2 horizontal Mutant) and check only 1 was inserted !!!
	 */
	@Test
	void testSameMutantNotRepetead() throws Exception {
		final ResultActions firstResult = listRequest(status().isOk());

		final String firstJson = firstResult.andReturn().getResponse().getContentAsString();
		final List<DNA> firstList = objectMapper.readValue(firstJson, List.class);

		final String request = "{\n"+
				"\"dna\": " +
				"        [\"ATGCGA\",\n" +
				"         \"CAGTGC\",\n" +
				"         \"TTTTGT\",\n" +
				"         \"AGAAGG\",\n" +
				"         \"CCCCTA\",\n" +
				"         \"TCTTTT\"" +
				"        ]\n" +
				"}";

		isMutantRequest(request, status().isOk());

        final String request2 = "{\n" +
				"\"dna\": " +
				"        [\"ATgCGA\",\n" +
				"         \"CAGTGC\",\n" +
				"         \"TtTTgT\",\n" +
				"         \"AGAAGG\",\n" +
				"         \"CCcCTA\",\n" +
				"         \"TCTTTT\"" +
				"        ]\n" +
				"}";

        isMutantRequest(request2, status().isOk());

		final ResultActions lastResult = listRequest(status().isOk());

		final String lastJson = lastResult.andReturn().getResponse().getContentAsString();
		final List<DNA> lastList = objectMapper.readValue(lastJson, List.class);

		assertThat(lastList.size()).isEqualTo(firstList.size()+1);
	}

	/**
	 * Check random dna
	 *
	 * @throws Exception
	 *
	 */
	@Test
	void testHuman01() throws Exception {
		final String request = "{\n"+
				"\"dna\": " +
				"        [\"ACCTA\",\n" +
				"         \"CTCAC\",\n" +
				"         \"ACGCT\",\n" +
				"         \"ACCTA\",\n" +
				"         \"CAACA\"" +
				"        ]\n" +
				"}";

        isMutantRequest(request, status().isForbidden());
    }

	@Test
	void testHuman02() throws Exception {
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

        isMutantRequest(request, status().isForbidden());
    }

    @Test
	void testHuman03() throws Exception {
		final String request = "{\n"+
				"\"dna\": " +
				"        [\"ACCTA\",\n" +
				"         \"CCCCC\",\n" +
				"         \"ACGCT\",\n" +
				"         \"AACTA\",\n" +
				"         \"CAAAG\"" +
				"        ]\n" +
				"}";

        isMutantRequest(request, status().isForbidden());
    }


	/**
	 * @throws Exception if something went wrong
	 * @test sequence with only 1 sequence horizontal
	 */
	@Test
	void testHumanHorizontal() throws Exception {
		final String request = "{\n"+
				"\"dna\": " +
				"        [\"AAAAC\",\n" +
				"         \"GATCT\",\n" +
				"         \"TGACT\",\n" +
				"         \"TCTGC\",\n" +
				"         \"ATTGT\"" +
				"        ]\n" +
				"}";

        isMutantRequest(request, status().isForbidden());
    }

	/**
	 * @throws Exception if something went wrong
	 * @test sequence with only 1 sequence vertical
	 */
	@Test
	void testHumanVertical() throws Exception {
		final String request = "{\n"+
				"\"dna\": " +
				"        [\"AACAC\",\n" +
				"         \"GATCT\",\n" +
				"         \"TGACT\",\n" +
				"         \"TCTGT\",\n" +
				"         \"ATTGT\"" +
				"        ]\n" +
				"}";

        isMutantRequest(request, status().isForbidden());
    }

	/**
	 * @throws Exception if something went wrong
	 * @test sequence with only 1 sequence diagonal up
	 */
	@Test
	void testHumanDiagonalUp() throws Exception {
		final String request = "{\n"+
				"\"dna\": " +
				"        [\"AACAC\",\n" +
				"         \"GATCT\",\n" +
				"         \"TGCCT\",\n" +
				"         \"TCTGC\",\n" +
				"         \"ATTGT\"" +
				"        ]\n" +
				"}";

        isMutantRequest(request, status().isForbidden());
    }

	/**
	 * @throws Exception if something went wrong
	 * @test sequence with only 1 diagonal down
	 */
	@Test
	void testHumanDiagonalDown() throws Exception {
		final String request = "{\n"+
				"\"dna\": " +
				"        [\"AACAC\",\n" +
				"         \"GATCT\",\n" +
				"         \"TGACT\",\n" +
				"         \"TCGGC\",\n" +
				"         \"ATTGT\"" +
				"        ]\n" +
				"}";

        isMutantRequest(request, status().isForbidden());
    }

	/**
	 * @throws Exception if something went wrong
	 * @test validation limit
	 */
	@Test
	void testLimitValidation() throws Exception {
		final String request = "{\n"+
				"\"dna\": " +
				"        [\"ACCTA\",\n" +
				"         \"CCCCC\",\n" +
				"         \"CAAAG\"" +
				"        ]\n" +
				"}";

		final ResultActions resultActions = isMutantRequest(request, status().isBadRequest());

		final String result = resultActions.andReturn().getResponse().getContentAsString();
		assertThat(result.contains(DNAValidationException.ERROR_SIZE)).isTrue();
	}

	/**
	 * @throws Exception
	 * @test validation matching
	 */
	@Test
	void testMatchValidation() throws Exception {
		final String request = "{\n"+
				"\"dna\": " +
				"        [\"ADCTA\",\n" +
				"         \"CCCCC\",\n" +
				"         \"ACGCT\",\n" +
				"         \"AACTA\",\n" +
				"         \"CAABB\"" +
				"        ]\n" +
				"}";

		final ResultActions resultActions = isMutantRequest(request, status().isBadRequest());

		final String result = resultActions.andReturn().getResponse().getContentAsString();
		assertThat(result.contains(DNAValidationException.ERROR_MATCH)).isTrue();
	}

	/**
	 * @throws Exception
	 * @test try with lowCase pattern
	 */
	@Test
	void testPatternOk() throws Exception {
		final String request = "{\n"+
				"\"dna\": " +
				"        [\"aTcTA\",\n" +
				"         \"ACcCC\",\n" +
				"         \"ACGCT\",\n" +
				"         \"AACTA\",\n" +
				"         \"CAATt\"" +
				"        ]\n" +
				"}";

        isMutantRequest(request, status().isOk());
    }

	/**
	 * @throws Exception
	 * @test validation size cannot be lesser than DNAProperties.MUTANT_ADN_SEQUENCE
	 */
	@Test
	void testSequenceLesserThanPermitted() throws Exception {
		final String request = "{\n"+
				"\"dna\": " +
				"        [\"ADC\",\n" +
				"         \"AAC\",\n" +
				"         \"CAA\"" +
				"        ]\n" +
				"}";

		final ResultActions resultActions = isMutantRequest(request, status().isBadRequest());

		final String result = resultActions.andReturn().getResponse().getContentAsString();
		assertThat(result.contains(DNAValidationException.ERROR_SIZE)).isTrue();
	}

	/**
	 * @throws Exception if something went wrong
	 * @test add 1 mutant, 2 humans and check stats
	 */
	@Test
	void testStats() throws Exception {
		final ResultActions firstResultActions = getStatsRequestOk();

		final String firstJson = firstResultActions.andReturn().getResponse().getContentAsString();
		final DNAStats firsStats = objectMapper.readValue(firstJson, DNAStats.class);

		testMutantMeliExample();
		testHuman01();
		testHuman02();

		final ResultActions resultActions = getStatsRequestOk();

		final String json = resultActions.andReturn().getResponse().getContentAsString();
		final DNAStats stats = objectMapper.readValue(json, DNAStats.class);

		assertThat(stats.getHumanCount()).isEqualTo(firsStats.getHumanCount()+2);
		assertThat(stats.getMutantCount()).isEqualTo(firsStats.getMutantCount()+1);
		assertThat(stats.getRatio()).isEqualTo((double)stats.getMutantCount()/(double)stats.getHumanCount());
	}

	/**
	 * Search for stats of human and mutant
	 *
	 * @return DNAStats request
	 * @throws Exception
	 */
    private ResultActions getStatsRequestOk() throws Exception {
        return mvc.perform(MockMvcRequestBuilders.get("/stats").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

	/**
	 * @param request DNA sequence
	 * @param status Expected result
	 * @return
	 * @throws Exception
	 */
    private ResultActions isMutantRequest(String request, ResultMatcher status) throws Exception {
        return mvc.perform(MockMvcRequestBuilders.post("/mutant").contentType(MediaType.APPLICATION_JSON).content(request))
                .andExpect(status);
    }

	/**
	 * @param status Expected result
	 * @return	list of DNA
	 * @throws Exception
	 */
    private ResultActions listRequest(ResultMatcher status) throws Exception {
		return mvc.perform(MockMvcRequestBuilders.get("/mutant/list"))
				.andExpect(status);
	}
}