package com.sdia.hospital;

import com.sdia.hospital.entities.Patient;
import com.sdia.hospital.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.service.annotation.PatchExchange;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class HospitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }

    // bean pour stocker des dans dooner dans DB lors de demarrage du app
    @Bean
    CommandLineRunner init(PatientRepository patientRepository) {
        return args -> {
            Patient p1 = Patient.builder()
                    .nom("Boussaid")
                    .prenom("amine")
                    .score(100)
                    .dateNaissance(new Date())
                    .malade(false)
                    .build();
            Patient p2 = Patient.builder()
                    .nom("Pelegrini")
                    .prenom("john")
                    .score(150)
                    .dateNaissance(new Date())
                    .malade(true)
                    .build();
            Patient p3 =Patient.builder()
                    .nom("Sarah")
                    .prenom("boss")
                    .score(120)
                    .dateNaissance(new Date())
                    .malade(false)
                    .build();

            patientRepository.save(p1);
            patientRepository.save(p2);
            patientRepository.save(p3);


            List<Patient> patients = patientRepository.findAll();
            patients.forEach(p -> System.out.println(p.toString()));
        };
    }
}
