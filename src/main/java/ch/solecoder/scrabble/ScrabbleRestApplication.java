package ch.solecoder.scrabble;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackageClasses = {ScrabbleRestApplication.class})
@SpringBootApplication(scanBasePackages = {"ch.solecoder.scrabble"})
@EnableJpaRepositories(basePackages = "ch.solecoder.scrabble")
public class ScrabbleRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScrabbleRestApplication.class, args);
    }

}
