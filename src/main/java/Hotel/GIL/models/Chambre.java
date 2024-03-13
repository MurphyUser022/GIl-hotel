package Hotel.GIL.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;


@Data
@Entity
@DynamicUpdate
public class Chambre {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idchambre;


    private String nom;
    private String categorie;
    private String statut;

}
