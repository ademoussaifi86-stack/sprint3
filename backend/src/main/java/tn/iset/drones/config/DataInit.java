package tn.iset.drones.config;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tn.iset.drones.entities.Drone;
import tn.iset.drones.entities.TypeDrone;
import tn.iset.drones.repositories.DroneRepository;
import tn.iset.drones.repositories.TypeDroneRepository;

// Insère des données de départ si les tables sont vides
@Configuration
public class DataInit {

    private static Date date(String iso) {
        return Date.from(LocalDate.parse(iso).atStartOfDay(ZoneOffset.UTC).toInstant());
    }

    @Bean
    CommandLineRunner initData(TypeDroneRepository typeRepository, DroneRepository droneRepository) {
        return args -> {

            if (typeRepository.count() == 0) {
                typeRepository.saveAll(List.of(
                        new TypeDrone("Quadricoptère"),
                        new TypeDrone("Hexacoptère"),
                        new TypeDrone("Octocoptère"),
                        new TypeDrone("Aile fixe"),
                        new TypeDrone("FPV Racing"),
                        new TypeDrone("Agricole"),
                        new TypeDrone("Photographie"),
                        new TypeDrone("Industriel")));
            }

            if (droneRepository.count() == 0) {
                TypeDrone photo = typeRepository.findByNomTypeIgnoreCase("Photographie").orElseThrow();
                TypeDrone quad = typeRepository.findByNomTypeIgnoreCase("Quadricoptère").orElseThrow();
                TypeDrone agri = typeRepository.findByNomTypeIgnoreCase("Agricole").orElseThrow();

                droneRepository.saveAll(List.of(
                        new Drone("DJI Mavic 3", "DJI", 9000.0, date("2020-01-14"), photo),
                        new Drone("Parrot Anafi", "Parrot", 3500.0, date("2021-02-20"), quad),
                        new Drone("DJI Agras T40", "DJI", 45000.0, date("2022-03-10"), agri)));
            }
        };
    }
}
