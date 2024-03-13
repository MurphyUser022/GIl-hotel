package Hotel.GIL.models;


import java.time.LocalDate;
import java.time.LocalTime;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@DynamicUpdate
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReservation;
    
    private Integer id_client; // id du client ayant faire la reservation
    private LocalDate date_arrivee;
    private LocalDate date_depart;
    private LocalTime Heure_reservation;
    private LocalTime Heure_fin_reservation;
    private int nb_pers;
    private float prix;
    private String statut;
    @ManyToOne @JoinColumn(name="idChambre", nullable=false)
    private Chambre voicidchambre;  





}
