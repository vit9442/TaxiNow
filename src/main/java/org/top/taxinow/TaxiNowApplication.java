package org.top.taxinow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TaxiNowApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaxiNowApplication.class, args);
    }

}
