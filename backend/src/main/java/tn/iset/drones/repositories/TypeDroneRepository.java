package tn.iset.drones.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.iset.drones.entities.TypeDrone;

public interface TypeDroneRepository extends JpaRepository<TypeDrone, Long> {

    Optional<TypeDrone> findByNomTypeIgnoreCase(String nomType);
}
