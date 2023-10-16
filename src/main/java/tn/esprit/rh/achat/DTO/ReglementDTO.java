package tn.esprit.rh.achat.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import tn.esprit.rh.achat.entities.Facture;

import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
