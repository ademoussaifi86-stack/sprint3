package tn.iset.drones.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.iset.drones.entities.TypeDrone;
import tn.iset.drones.repositories.DroneRepository;
import tn.iset.drones.repositories.TypeDroneRepository;

@RestController
@RequestMapping("/api/types")
public class TypeDroneRestController {

    private final TypeDroneRepository typeRepository;
    private final DroneRepository droneRepository;

    public TypeDroneRestController(TypeDroneRepository typeRepository, DroneRepository droneRepository) {
        this.typeRepository = typeRepository;
        this.droneRepository = droneRepository;
    }

    // GET /api/types : liste des types
    @GetMapping
    public List<TypeDrone> getAllTypes() {
        return typeRepository.findAll();
    }

    // GET /api/types/{id} : un type par son id
    @GetMapping("/{id}")
    public ResponseEntity<TypeDrone> getTypeById(@PathVariable Long id) {
        return typeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/types : ajouter un type (nom non vide et unique)
    @PostMapping
    public ResponseEntity<TypeDrone> createType(@RequestBody TypeDrone type) {
        String nom = type.getNomType() == null ? "" : type.getNomType().trim();

        if (nom.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        if (typeRepository.findByNomTypeIgnoreCase(nom).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        type.setIdType(null); // l'id est généré par la base
        type.setNomType(nom);
        return ResponseEntity.ok(typeRepository.save(type));
    }

    // PUT /api/types/{id} : modifier le nom d'un type
    @PutMapping("/{id}")
    public ResponseEntity<TypeDrone> updateType(@PathVariable Long id, @RequestBody TypeDrone type) {
        String nom = type.getNomType() == null ? "" : type.getNomType().trim();

        if (nom.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Optional<TypeDrone> existant = typeRepository.findById(id);
        if (existant.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Optional<TypeDrone> memeNom = typeRepository.findByNomTypeIgnoreCase(nom);
        if (memeNom.isPresent() && !memeNom.get().getIdType().equals(id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        TypeDrone aModifier = existant.get();
        aModifier.setNomType(nom);
        return ResponseEntity.ok(typeRepository.save(aModifier));
    }

    // DELETE /api/types/{id} : supprimer un type (refusé s'il est utilisé)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteType(@PathVariable Long id) {
        if (!typeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        if (droneRepository.existsByType_IdType(id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        typeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
