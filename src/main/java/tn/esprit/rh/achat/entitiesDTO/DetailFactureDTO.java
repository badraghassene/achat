package tn.esprit.rh.achat.entitiesDTO;


import lombok.Data;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.entities.Produit;



@Data
public class DetailFactureDTO {

    private static final long serialVersionUID = 1L;

    private Long idDetailFacture;
    private Integer qteCommandee;
    private float prixTotalDetail;
    private Integer pourcentageRemise;
    private float montantRemise;

    private Produit produit;

    Facture facture;
}
