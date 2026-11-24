package tn.iset.drones.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDrone;

    private String nomDrone;

    private String marqueDrone;

    private Double prixDrone;

    @Temporal(TemporalType.DATE)
    private Date dateCreation;

    @ManyToOne
    @JoinColumn(name = "id_type")
    private TypeDrone type;

    public Drone() {
    }

    public Drone(String nomDrone, String marqueDrone, Double prixDrone, Date dateCreation, TypeDrone type) {
        this.nomDrone = nomDrone;
        this.marqueDrone = marqueDrone;
        this.prixDrone = prixDrone;
        this.dateCreation = dateCreation;
        this.type = type;
    }

    public Long getIdDrone() {
        return idDrone;
    }

    public void setIdDrone(Long idDrone) {
        this.idDrone = idDrone;
    }

    public String getNomDrone() {
        return nomDrone;
    }

    public void setNomDrone(String nomDrone) {
        this.nomDrone = nomDrone;
    }

    public String getMarqueDrone() {
        return marqueDrone;
    }

    public void setMarqueDrone(String marqueDrone) {
        this.marqueDrone = marqueDrone;
    }

    public Double getPrixDrone() {
        return prixDrone;
    }

    public void setPrixDrone(Double prixDrone) {
        this.prixDrone = prixDrone;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public TypeDrone getType() {
        return type;
    }

    public void setType(TypeDrone type) {
        this.type = type;
    }
}
