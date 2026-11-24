package tn.iset.drones.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.iset.drones.entities.Drone;

public interface DroneRepository extends JpaRepository<Drone, Long> {

    List<Drone> findByNomDroneContainingIgnoreCase(String nom);

    List<Drone> findByType_IdType(Long idType);

    boolean existsByType_IdType(Long idType);
}
