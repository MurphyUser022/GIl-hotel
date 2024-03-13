package Hotel.GIL.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import Hotel.GIL.models.Personnel;
import Hotel.GIL.Repositories.PersonnelRepository;
import Hotel.GIL.Services.PersonnelService;

@Controller
public class PersonelController {

    @Autowired
    private PersonnelRepository personnelRepository;

    @Autowired
    private PersonnelService personnelService;

    @GetMapping("/pers")
    public String Personel(Model model) {
        List<Personnel> sans = personnelRepository.findAll();
        model.addAttribute("ens", sans);
        return "/personnel.html";
    }

    @GetMapping("/personel")
    public String allPersonel(Model model) {
        List<Personnel> sans = personnelRepository.findAll();
        model.addAttribute("ens", sans);
        return "/personnel_All.html";
    }

    @GetMapping("/delete_pers/{id}")
    public String deletePers(@PathVariable("id") int id) {
        personnelService.deletePersonnel(id);
        return "redirect:/personel";
    }

    @PostMapping("/create_pers")
    public String CreatPersonnel(
            @RequestParam("file") MultipartFile file,
            @RequestParam("nom") String nom,
            @RequestParam("email") String email,
            @RequestParam("prenom") String prenom,
            @RequestParam("poste") String poste,
            @RequestParam("mois") int mois,
            @RequestParam("ncni") String ncni) {
        System.out.println("c'est le dem");
        personnelService.savePersonnel(file, nom, prenom, poste, mois, email, ncni);
        System.out.println("c'est le dem");
        return "redirect:/personel";
    }

    @GetMapping("/edit/{id}")
    public String EditPersonnel(@PathVariable("id") int id, Model model) {

        model.addAttribute("id", id);
        List<Personnel> sans = personnelRepository.findAll();
        model.addAttribute("ens", sans);

        return "persCAL.html";
    }

    @PostMapping("/update_pers")
    public String updatePersonnel(
            @RequestParam("idChambre") int id,
            @RequestParam("file") MultipartFile file,
            @RequestParam("nom") String nom,
            @RequestParam("email") String email,
            @RequestParam("prenom") String prenom,
            @RequestParam("poste") String poste,
            @RequestParam("mois") int mois,
            @RequestParam("ncni") String ncni) {
        personnelService.updateinformation(id, file, nom, prenom, poste, mois, email, ncni);

        return "redirect:/pers";
    }

}