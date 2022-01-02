package ai.exscientia.compounds;

import ai.exscientia.compounds.controller.AssayResultController;
import ai.exscientia.compounds.controller.CompoundController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CompoundsApplicationSmokeTests {

    @Autowired
    private CompoundController compoundController;

    @Autowired
    private AssayResultController assayResultController;

    @Test
    void contextLoads() {
        assertThat(compoundController).isNotNull();
        assertThat(assayResultController).isNotNull();
    }

}
