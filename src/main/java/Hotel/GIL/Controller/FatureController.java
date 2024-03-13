package Hotel.GIL.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.zxing.WriterException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.Authentication;

import Hotel.GIL.Repositories.ReservationRepository;
import Hotel.GIL.Repositories.UserEntityRepository;
import Hotel.GIL.Services.QRCodeGenerator;
import Hotel.GIL.Services.ReservationService;
import Hotel.GIL.models.Reservation;
import Hotel.GIL.models.Role;
import Hotel.GIL.models.RoleName;
import Hotel.GIL.models.UserEntity;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class FatureController {

    @Autowired
    ReservationService reservationService;
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @GetMapping("/facture/{id}")
    public String facrureById(Model model,
            @PathVariable("id") int id,
            Authentication authentication,
            HttpServletRequest request, RedirectAttributes attributes) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        // Récupérer l'objet UserEntity correspondant à l'utilisateur connecté
        UserEntity user = userEntityRepository.findByUserName(username);
        Reservation reservation = reservationRepository.findById(id).get();
        // List<Reservation> sans = reservationRepository.findAll();

        model.addAttribute("facture", reservation);

        int id_User_connecteur = user.getId();
        UserEntity currentUser = userEntityRepository.findById(id_User_connecteur).get();

        String medium = "Info: " + reservation + "user:info" + id_User_connecteur;

        System.err.println(reservation);

        byte[] image = new byte[0];
        try {

            // Generate and Return Qr Code in Byte Array
            image = QRCodeGenerator.getQRCodeImage(medium, 250, 250);

            // Generate and Save Qr Code Image in static/image folder
            // QRCodeGenerator.generateQRCodeImage(github,250,250,QR_CODE_IMAGE_PATH);

        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
        // Convert Byte Array into Base64 Encode String
        String qrcode = Base64.getEncoder().encodeToString(image);

        model.addAttribute("medium", medium);
        model.addAttribute("qrcode", qrcode);
        model.addAttribute("currentUser", currentUser);

        return "/facture.html";
    }

    @GetMapping("/facture")
    public String listFacture(Model model, Authentication authentication,
            HttpServletRequest request, RedirectAttributes attributes) {

        List<Reservation> sans = reservationRepository.findAll();
        List<Reservation> ReservationUser = new ArrayList<>();

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        // Récupérer l'objet UserEntity correspondant à l'utilisateur connecté
        UserEntity user = userEntityRepository.findByUserName(username);

        // Obtenir la liste des rôles de l'utilisateur
        List<Role> roles = user.getRoles();

        // Parcourir la liste des rôles de l'utilisateur
        for (Role role : roles) {
            // Accéder au nom du rôle
            RoleName roleName = role.getRoleName();
            if (roleName.equals(RoleName.ROLE_USER)) {
                for (Reservation res : sans) {
                    if (res.getId_client() == user.getId()) {
                        ReservationUser.add(res);
                    }
                }
            } else if (roleName.equals(RoleName.ROLE_ADMIN)) {
                // Pour les administrateurs, ajouter toutes les réservations
                ReservationUser.addAll(sans);
                // Pas besoin de continuer à parcourir les rôles, car l'administrateur
                // a tous les privilèges nécessaires pour afficher toutes les réservations
                break;
            }
        }

        model.addAttribute("reservations", ReservationUser);
        return "/ListFacture.html";
    }

}