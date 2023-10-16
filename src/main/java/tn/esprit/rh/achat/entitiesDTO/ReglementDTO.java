package tn.esprit.rh.achat.entitiesDTO;


import tn.esprit.rh.achat.entities.Facture;


import java.util.Date;
import lombok.Data;

@Data
public class ReglementDTO {
    private Long idReglement;
    private float montantPaye;
    private float montantRestant;
    private Boolean payee;

    private Date dateReglement;

    private Facture facture;
}
