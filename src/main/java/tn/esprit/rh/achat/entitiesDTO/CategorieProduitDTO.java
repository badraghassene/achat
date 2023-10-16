package tn.esprit.rh.achat.entitiesDTO;
import lombok.Data;
import tn.esprit.rh.achat.entities.Produit;
import java.util.Set;


@Data
public class CategorieProduitDTO {

    private Long idCategorieProduit;
    private String codeCategorie;
    private String libelleCategorie;
    private Set<Produit> produits;
}
