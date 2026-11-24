package tn.iset.drones.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.iset.drones.entities.Drone;
import tn.iset.drones.repositories.DroneRepository;

@RestController
@RequestMapping("/api/drones")
public class DroneRestController {

    private final DroneRepository droneRepository;

    public DroneRestController(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    // GET /api/drones : liste de tous les drones
    @GetMapping
    public List<Drone> getAllDrones() {
        return droneRepository.findAll();
    }

    // GET /api/drones/{id} : un drone par son id
    @GetMapping("/{id}")
    public ResponseEntity<Drone> getDroneById(@PathVariable Long id) {
        return droneRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/drones : ajouter un drone
    @PostMapping
    public Drone createDrone(@RequestBody Drone drone) {
        drone.setIdDrone(null); // l'id est généré par la base
        return droneRepository.save(drone);
    }

    // PUT /api/drones : modifier un drone
    @PutMapping
    public ResponseEntity<Drone> updateDrone(@RequestBody Drone drone) {
        if (drone.getIdDrone() == null || !droneRepository.existsById(drone.getIdDrone())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(droneRepository.save(drone));
    }

    // DELETE /api/drones/{id} : supprimer un drone
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDrone(@PathVariable Long id) {
        if (!droneRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        droneRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // GET /api/drones/type/{idType} : drones d'un type
    @GetMapping("/type/{idType}")
    public List<Drone> getDronesByType(@PathVariable Long idType) {
        return droneRepository.findByType_IdType(idType);
    }

    // GET /api/drones/nom/{nom} : recherche par nom (contient)
    @GetMapping("/nom/{nom}")
    public List<Drone> getDronesByNom(@PathVariable String nom) {
        return droneRepository.findByNomDroneContainingIgnoreCase(nom);
    }
}
