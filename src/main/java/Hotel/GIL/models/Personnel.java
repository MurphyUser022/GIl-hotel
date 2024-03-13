package Hotel.GIL.models;

import jakarta.persistence.*;
import lombok.Data;

// import java.util.Date;

import org.hibernate.annotations.DynamicUpdate;

@Data
@Entity
@DynamicUpdate
public class Personnel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    private String nom;
    private String prenom;
    private String poste;
    private int mois;
    private String email;
    private String ncni;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String photo;

    // private Date date_naissance;
    // private String sexe;
    // private String ncni;
    // private String speudo;
    // private String email;
    // private String password;



}
