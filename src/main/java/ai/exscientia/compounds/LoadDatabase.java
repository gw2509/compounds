package ai.exscientia.compounds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CompoundRepository repository) {

        return args -> {
//            log.info("Preloading " + repository.save(new Compound("smiles1", 444.44)));
//            log.info("Preloading " + repository.save(new Compound("smiles2", 555.55)));
        };
    }
}