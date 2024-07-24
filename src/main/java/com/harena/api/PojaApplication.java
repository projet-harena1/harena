package com.harena.api;

import com.harena.api.dto.json.ArgentDataFromJsonFile;
import com.harena.api.repository.ArgentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class PojaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PojaApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ArgentRepository argentRepository) {
        return args -> {
            List<ArgentDataFromJsonFile> initialData = argentRepository.loadAllData();
            System.out.println("Initial data: " + initialData.size());
        };
    }
}
