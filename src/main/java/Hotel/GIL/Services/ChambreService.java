package Hotel.GIL.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Hotel.GIL.Repositories.ChambreRepository;
import Hotel.GIL.models.Chambre;

@Service
public class ChambreService {

    @Autowired
    ChambreRepository chambreRepository;

    /* Fonction pour Save une Chambre */
    public void SaveChambre(String nom, String Categorie, String Statut) {

    Chambre ch = new Chambre();
        ch.setCategorie(Categorie);
        ch.setNom(nom);
        ch.setStatut(Statut);
        chambreRepository.save(ch);
    }

    /* Supprimer une chambre */
    public void DeleteChambre(int idChambre) {
        chambreRepository.deleteById(idChambre);
    }


    public Chambre UpdateChambre(int id, String nom, String categorie)// String status)//int nb_place)
    {
        Chambre ch = new Chambre();
        ch = chambreRepository.findById(id).get();
        ch.setCategorie(categorie);
        ch.setNom(nom);
        chambreRepository.save(ch);
        return ch;

    }    
}
