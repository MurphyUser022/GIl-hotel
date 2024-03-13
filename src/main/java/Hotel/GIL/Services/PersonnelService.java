package Hotel.GIL.Services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import Hotel.GIL.models.Personnel;
import Hotel.GIL.Repositories.PersonnelRepository;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;

import org.springframework.util.StringUtils;

import java.util.Base64;
import java.util.Optional;
import lombok.Data;
@Data
@Service
public class PersonnelService {

        @Autowired
    private PersonnelRepository personnelRepository;


    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String photo;
    
        public Personnel savePersonnel(MultipartFile photo, String nom,String prenom,String poste,int mois, String email,String ncni)
    {
        Personnel pers = new Personnel();
        String fileName = StringUtils.cleanPath(photo.getOriginalFilename());
        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
        try {
            pers.setPhoto(Base64.getEncoder().encodeToString(photo.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
      pers.setEmail(email);
      pers.setPrenom(prenom);
      pers.setPoste(poste);
      pers.setMois(mois);
      pers.setNom(nom);
      pers.setNcni(ncni);

        personnelRepository.save(pers);
        return pers;
        }



            public void deletePersonnel (int id){
        try {
            personnelRepository.deleteById(id);
        } catch (Exception e){

        }
    }

       public void updateinformation(int id,MultipartFile photo, String nom,String prenom,String poste,int mois, String email,String ncni)
    {
        Personnel pers=new Personnel();
        pers=personnelRepository.findById(id).get();
        pers.setEmail(email);
        pers.setPrenom(prenom);
        pers.setPoste(poste);
        pers.setMois(mois);
        pers.setNom(nom);
        pers.setNcni(ncni);
    
        String fileName = StringUtils.cleanPath(photo.getOriginalFilename());
        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
        try {
            pers.setPhoto(Base64.getEncoder().encodeToString(photo.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        personnelRepository.save(pers);
    }


public Personnel SearchPersonnel(int id)
    {
        Personnel pers = new Personnel();
        pers = personnelRepository.findById(id).get();
        return pers;
    }


}
