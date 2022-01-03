package ai.exscientia.compounds;

import ai.exscientia.compounds.domain.Compound;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.util.StringUtils.countOccurrencesOf;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CompoundsApplicationIntegrationTests {

    public static final String BASE_URL = "http://localhost";

    public static final int NUMBER_OF_COMPOUNDS_IN_TEST_DATA = 100;

    public static final String ENDPOINT_FOR_COMPOUNDS = "/api/v1/compounds/";
    private static final String EXISTING_COMPOUND_ID = "2251994";
    private static final String EXISTING_COMPOUND_ID2 = "27648";
    private static final String COMPOUND_ID_WITH_MANY_ASSAY_RESULTS = "1117973";
    private static final String NON_EXISTENT_COMPONENT_ID = "999999";

    public static final int NUMBER_OF_ASSAY_RESULTS_IN_TEST_DATA = 163;

    public static final String ENDPOINT_FOR_ASSAY_RESULTS = "/api/v1/assayresults/";
    private static final String EXISTING_ASSAY_RESULT_ID = "12161625";
    private static final String EXISTING_ASSAY_RESULT_ID2 = "6364741";
    private static final String NON_EXISTENT_ASSAY_RESULT_ID = "999999";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * Integration tests of the REST API for compounds and assay results.
     * <p>
     * Note: At present, some assert statements rather crudely search the String response to establish content and
     * cardinality of responses. Given more time, we should modify the code to create and  interrogate the Java objects.
     */
    //compounds
    @Test
    public void requestForOneCompoundShouldReturnCompound() throws Exception {
        assertThat(this.restTemplate.getForObject(BASE_URL + ":" + port + ENDPOINT_FOR_COMPOUNDS + EXISTING_COMPOUND_ID,
                String.class)).contains("CCOC1=CC(=O)N(C)C=C1c2cc(NC(=O)CC3C[C@@H]4CC[C@H]3C4)ccc2Oc5ccc(F)cc5F");
    }

    @Test
    public void requestForOneCompoundWithManyAssayResultsShouldContain11AssayItems() throws Exception {
        String response = this.restTemplate.getForObject(BASE_URL + ":" + port + ENDPOINT_FOR_COMPOUNDS + COMPOUND_ID_WITH_MANY_ASSAY_RESULTS,
                String.class);

        assertThat(countOccurrencesOf(response, "target")).isEqualTo(11);
    }

    @Test
    public void requestForAllCompoundsShouldReturnExpectedItems() throws Exception {
        String response = this.restTemplate.getForObject(BASE_URL + ":" + port + ENDPOINT_FOR_COMPOUNDS,
                String.class);

        assertThat(countOccurrencesOf(response, "smiles")).isEqualTo(NUMBER_OF_COMPOUNDS_IN_TEST_DATA);
    }

    @Test
    public void requestForNonExistentCompoundShouldReturnNotFound() throws Exception {
        assertThat(this.restTemplate.getForObject(BASE_URL + ":" + port + ENDPOINT_FOR_COMPOUNDS + NON_EXISTENT_COMPONENT_ID,
                String.class)).contains("Could not find compound " + NON_EXISTENT_COMPONENT_ID);
    }

    @Test
    public void deletionOfCompoundSucceeds() throws Exception {
        this.restTemplate.delete(BASE_URL + ":" + port + ENDPOINT_FOR_COMPOUNDS + EXISTING_COMPOUND_ID2);

        assertThat(this.restTemplate.getForObject(BASE_URL + ":" + port + ENDPOINT_FOR_COMPOUNDS + EXISTING_COMPOUND_ID2,
                String.class)).contains("Could not find compound " + EXISTING_COMPOUND_ID2);

        //verify cascaded delete of associated assay result(s)
        String associatedAssayResult = "8055999";
        assertThat(this.restTemplate.getForObject(BASE_URL + ":" + port + ENDPOINT_FOR_ASSAY_RESULTS + associatedAssayResult,
                String.class)).contains("Could not find assay result " + associatedAssayResult);
    }

    @Test
    public void creationOfnewCompoundSucceeds() throws Exception {
        String url = BASE_URL + ":" + port + ENDPOINT_FOR_COMPOUNDS;
        Compound newCompound = new Compound();

        assertThat(this.restTemplate.postForObject(url, newCompound, String.class)).contains("2251995");
    }

    //assay results

    @Test
    public void requestForOneAssayResultReturnAssayResult() throws Exception {
        assertThat(this.restTemplate.getForObject(BASE_URL + ":" + port + ENDPOINT_FOR_ASSAY_RESULTS + EXISTING_ASSAY_RESULT_ID,
                String.class)).contains(EXISTING_ASSAY_RESULT_ID);
    }

    @Test
    public void requestForAllAssayResultsShouldReturn163Items() throws Exception {
        String response = this.restTemplate.getForObject(BASE_URL + ":" + port + ENDPOINT_FOR_ASSAY_RESULTS,
                String.class);

        assertThat(countOccurrencesOf(response, "target")).isEqualTo(NUMBER_OF_ASSAY_RESULTS_IN_TEST_DATA);
    }

    @Test
    public void requestForNonExistentAssayResultShouldReturnNotFound() throws Exception {
        assertThat(this.restTemplate.getForObject(BASE_URL + ":" + port + ENDPOINT_FOR_ASSAY_RESULTS + NON_EXISTENT_ASSAY_RESULT_ID,
                String.class)).contains("Could not find assay result " + NON_EXISTENT_ASSAY_RESULT_ID);
    }

    @Test
    public void deletionOfAssayResultSucceeds() throws Exception {
        this.restTemplate.delete(BASE_URL + ":" + port + ENDPOINT_FOR_ASSAY_RESULTS + EXISTING_ASSAY_RESULT_ID2);

        assertThat(this.restTemplate.getForObject(BASE_URL + ":" + port + ENDPOINT_FOR_ASSAY_RESULTS + EXISTING_ASSAY_RESULT_ID2,
                String.class)).contains("Could not find assay result " + EXISTING_ASSAY_RESULT_ID2);
    }

}