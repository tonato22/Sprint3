package com.exemplo.patio;

import com.exemplo.patio.model.Moto;
import com.exemplo.patio.model.Registro;
import com.exemplo.patio.repository.MotoRepository;
import com.exemplo.patio.repository.RegistroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class PatioApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatioApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(MotoRepository motoRepository, RegistroRepository registroRepository) {
        return args -> {
            // Inserindo Motos
            Moto m1 = new Moto(null, "ABC1234", "Honda CG 160");
            Moto m2 = new Moto(null, "DEF5678", "Yamaha Fazer 250");
            Moto m3 = new Moto(null, "GHI9101", "Suzuki Yes 125");
            Moto m4 = new Moto(null, "JKL1121", "Kawasaki Ninja 400");
            Moto m5 = new Moto(null, "MNO3141", "BMW G310R");

            motoRepository.save(m1);
            motoRepository.save(m2);
            motoRepository.save(m3);
            motoRepository.save(m4);
            motoRepository.save(m5);

            // Inserindo Registros
            registroRepository.save(new Registro(null, m1, LocalDateTime.now().minusDays(1), null));
            registroRepository.save(new Registro(null, m2, LocalDateTime.now().minusHours(10), null));
            registroRepository.save(new Registro(null, m3, LocalDateTime.now().minusHours(5), null));
            registroRepository.save(new Registro(null, m4, LocalDateTime.now().minusHours(2), null));
            registroRepository.save(new Registro(null, m5, LocalDateTime.now().minusMinutes(30), null));
        };
    }
}