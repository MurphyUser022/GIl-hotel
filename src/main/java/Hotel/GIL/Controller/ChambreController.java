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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.zxing.WriterException;

import Hotel.GIL.Repositories.ChambreRepository;
import Hotel.GIL.Repositories.ReservationRepository;
import Hotel.GIL.Services.ChambreService;
import Hotel.GIL.Services.QRCodeGenerator;
import Hotel.GIL.models.Chambre;
import Hotel.GIL.models.Reservation;

@Controller
public class ChambreController {

    @Autowired
    private ChambreRepository chambreRepository;

    @Autowired
    private ChambreService chambreService;

    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping("/chambre_admin")
    public String Personel(Model model) {

        List<Chambre> toutesLesChambres = chambreRepository.findAll();

        // Créer une liste pour stocker les chambres libres
        List<Chambre> chambresLibres = new ArrayList<>();

        // Récupérer la liste de toutes les réservations
        List<Reservation> toutesLesReservations = reservationRepository.findAll();

        // Créer une liste pour stocker les chambres Reserver
        List<Reservation> chambresReserver = new ArrayList<>();

        for (Reservation chamRes : toutesLesReservations)

        {
            if (chamRes.getStatut().equalsIgnoreCase("En Cours")) {
                chambresReserver.add(chamRes);
            }
        }

        for (Chambre chambre : toutesLesChambres)

        {
            if (!chambre.getStatut().equalsIgnoreCase("Reserver")) {
                chambresLibres.add(chambre);
            }
        }

        model.addAttribute("chambreLibre", chambresLibres);
        model.addAttribute("chambreReserver", chambresReserver);
        return "/chambreAdmin.html";

    }

    @GetMapping("/chambre")
    public String allPersonel(Model model) {

        List<Chambre> toutesLesChambres = chambreRepository.findAll();


        // Créer une liste pour stocker les chambres libres
        List<Chambre> chambresLibres = new ArrayList<>();



        for (Chambre chambre : toutesLesChambres)

        {
            if (!chambre.getStatut().equalsIgnoreCase("Reserver")) {
                chambresLibres.add(chambre);
            }
        }

        model.addAttribute("chambre", chambresLibres);
        return "/chambre.html"; }



        

    @GetMapping("/delete_chambre/{id}")
    public String deletePers(@PathVariable("id") int id) {
        chambreService.DeleteChambre(id);
        return "redirect:/chambre_admin";
    }

    @PostMapping("/create_chambre")
    public String Creatchambre(
            // @RequestParam("file") MultipartFile file,
            @RequestParam("nom") String nom,
            @RequestParam("categorie") String categorie)
    // @RequestParam("status") String status)
    {
        chambreService.SaveChambre(nom, categorie, "Libre");
        return "redirect:/chambre_admin";
    }

    @PostMapping("/update_chambre")
    public String updatePersonnel(// @PathVariable("id") int id,
            @RequestParam("nom") String nom,
            @RequestParam("categorie") String categorie,
            @RequestParam("idChambre") int id)

    {
        chambreService.UpdateChambre(id, nom, categorie);

        return "redirect:/chambre_admin";
    }




}

