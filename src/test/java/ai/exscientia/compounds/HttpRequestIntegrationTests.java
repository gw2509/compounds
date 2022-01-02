package ai.exscientia.compounds;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestIntegrationTests {

    public static final String ENDPOINT_FOR_COMPOUNDS = "/api/v1/compounds/";
    private static final String EXISTING_COMPONENT_ID = "2251994";
    private static final String NON_EXISTENT_COMPONENT_ID = "999999";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void requestForOneCompoundShouldReturnCompound() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + ENDPOINT_FOR_COMPOUNDS + EXISTING_COMPONENT_ID,
                String.class)).contains("CCOC1=CC(=O)N(C)C=C1c2cc(NC(=O)CC3C[C@@H]4CC[C@H]3C4)ccc2Oc5ccc(F)cc5F");
    }
    @Test
    public void requestForNonExistentCompoundShouldReturn404() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + ENDPOINT_FOR_COMPOUNDS + NON_EXISTENT_COMPONENT_ID,
                String.class)).contains("Could not find compound " + NON_EXISTENT_COMPONENT_ID);
    }
}