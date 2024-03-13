package Hotel.GIL.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import Hotel.GIL.Repositories.ChambreRepository;
import Hotel.GIL.Repositories.ReservationRepository;
import Hotel.GIL.models.Chambre;
import Hotel.GIL.models.Reservation;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    Chambre ch;
    @Autowired
    ChambreRepository chambreRepository;

    /* Add une reservation */
    public Reservation SaveReservation(int id_client, LocalDate date_arrivee, LocalDate date_depart,
            LocalTime Heure_reservation, LocalTime Heure_fin_reservation, int nb_pers, float prix, int idChambre) {
        Reservation reservation = new Reservation();
        reservation.setDate_arrivee(date_arrivee);
        reservation.setDate_depart(date_depart);
        reservation.setHeure_fin_reservation(Heure_fin_reservation);
        reservation.setHeure_reservation(Heure_reservation);
        reservation.setId_client(id_client);
        reservation.setNb_pers(nb_pers);
        reservation.setPrix(prix);
        reservation.setStatut("En cours");
        ch = chambreRepository.findById(idChambre).get();
        ch.setStatut("Reserver");
        reservation.setVoicidchambre(ch);

        reservationRepository.save(reservation);

        return reservation;
    }

    @Scheduled(cron = "0/1 * * * * *") // Toutes les 1 secondes
    public void executeEveryFiveMinutes() {

        List<Reservation> reservations = reservationRepository.findAll();

        for (Reservation reservation : reservations) {
            // Récupérer la date et l'heure actuelles
            LocalDateTime currentDateTime = LocalDateTime.now();

            // Combiner la date de départ et l'heure de fin de réservation en un objet
            // LocalDateTime
            LocalDateTime departureDateTime = reservation.getDate_depart()
                    .atTime(reservation.getHeure_fin_reservation());

            // Vérifier si la date et l'heure de fin de réservation sont passées ou égales à
            // la date et l'heure actuelles
            if (currentDateTime.isAfter(departureDateTime) || currentDateTime.isEqual(departureDateTime)) {
                // Si la date et l'heure de fin de réservation sont passées ou égales à la date
                // et l'heure actuelles, la réservation est terminée

                Chambre cha = chambreRepository.findById(reservation.getVoicidchambre().getIdchambre()).get();
                if (cha != null) {
                    // Mettre à jour le statut de la chambre
                    cha.setStatut("✅ Libre");
                    reservation.setStatut("Terminer");
                    reservationRepository.save(reservation);
                    chambreRepository.save(cha); // Sauvegarder le changement dans la base de données
                    // System.out.println("super bon !");
                }
            } 
            
            // else {
            //     // Sinon, la réservation n'est pas terminée
            //     reservation.setStatut("Non terminée");
            //     reservationRepository.save(reservation);
            // }
        }


    }
}
