package Hotel.GIL.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.print.DocFlavor.STRING;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Hotel.GIL.Repositories.ChambreRepository;
import Hotel.GIL.Repositories.ReservationRepository;
import Hotel.GIL.Repositories.UserEntityRepository;
import Hotel.GIL.Services.ChambreService;
import Hotel.GIL.Services.ReservationService;
import Hotel.GIL.models.Chambre;
import Hotel.GIL.models.Reservation;
import Hotel.GIL.models.UserEntity;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ReservationController {

    @Autowired
    ReservationService reservationService;
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private ChambreRepository chambreRepository;

    @Autowired
    private ChambreService chambreService;

    @PostMapping("/create_reservation")
    public String CreateReservation(int id_client, LocalDate date_arrivee, LocalDate date_depart,
            LocalTime Heure_reservation, LocalTime Heure_fin_reservation, int nb_pers, float prix, int idChambre) {
        reservationService.SaveReservation(id_client, date_arrivee, date_depart, Heure_reservation,
                Heure_fin_reservation, nb_pers, prix, idChambre);

        return "page.html";
    }

    @GetMapping("/reservation/{id}")
    public String ReservationById(Model model,
            @PathVariable("id") int id) {
        List<Reservation> sans = reservationRepository.findAll();
        Chambre ch = chambreRepository.findById(id).get();

        model.addAttribute("chambre", ch);
        return "/reservationById.html";
    }

    @GetMapping("/reservation")
    public String Reservation(Model model) {
        List<Reservation> sans = reservationRepository.findAll();
        model.addAttribute("ens", sans);
        return "/reservation.html";
    }

    @PostMapping("/reser")
    public String CreatReservation(
            @RequestParam("check-in-date") LocalDate dateA,
            @RequestParam("check-in-time") LocalTime HeureA,
            @RequestParam("check-out-date") LocalDate dateD,
            @RequestParam("check-out-time") LocalTime HeureD,
            @RequestParam("room-type") String typeChambre,
            @RequestParam("num-people") int nbre_pers,
            @RequestParam("price") float prix,
            Authentication authentication,
            HttpServletRequest request, RedirectAttributes attributes) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        // Récupérer l'objet UserEntity correspondant à l'utilisateur connecté
        UserEntity user = userEntityRepository.findByUserName(username);
        int id_User_connecteur = user.getId();
        reservationService.SaveReservation(id_User_connecteur, dateA, dateD, HeureA, HeureD, nbre_pers, prix, 0);

        // Obtenez l'URL de référence (la page précédente)
        String referer = request.getHeader("referer");

        // Ajoutez un attribut pour conserver l'URL de référence lors de la redirection
        attributes.addFlashAttribute("referer", referer);

        // Redirigez l'utilisateur vers la page précédente
        return "redirect:" + referer;

        // return "redirect:/personel";
    }

    @PostMapping("/reserve/{id}")
    public String CreatReservationbyid(
            @RequestParam("check-in-date") LocalDate dateA,
            @RequestParam("check-in-time") LocalTime HeureA,
            @RequestParam("check-out-date") LocalDate dateD,
            @RequestParam("check-out-time") LocalTime HeureD,
            @RequestParam("room-type") String typeChambre,
            @RequestParam("num-people") int nbre_pers,
            @RequestParam("price") float prix,
            @PathVariable("id") int id,
            Authentication authentication,
            HttpServletRequest request, RedirectAttributes attributes) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        // Récupérer l'objet UserEntity correspondant à l'utilisateur connecté
        UserEntity user = userEntityRepository.findByUserName(username);
        int id_User_connecteur = user.getId();
        // reservationService.SaveReservation(dateA, dateD, HeureA, HeureD, typeChambre,
        // nbre_pers, prix,id_User_connecteur,id);

        reservationService.SaveReservation(id_User_connecteur, dateA, dateD, HeureA, HeureD, nbre_pers, prix, id);
        // Obtenez l'URL de référence (la page précédente)
        String referer = request.getHeader("referer");

        // Ajoutez un attribut pour conserver l'URL de référence lors de la redirection
        attributes.addFlashAttribute("referer", referer);

        // Redirigez l'utilisateur vers la page précédente
        return "redirect:" + referer;
        // return "redirect:/personel";
    }

}
