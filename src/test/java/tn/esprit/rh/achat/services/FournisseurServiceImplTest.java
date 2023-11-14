package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.rh.achat.entities.*;
import tn.esprit.rh.achat.repositories.DetailFournisseurRepository;
import tn.esprit.rh.achat.repositories.FournisseurRepository;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ContextConfiguration(classes = {FournisseurServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class FournisseurServiceImplTest {

    @Autowired
    private FournisseurServiceImpl fournisseurService;

    @MockBean
    private FournisseurRepository fournisseurRepository;

    @MockBean
    private DetailFournisseurRepository detailFournisseurRepository;

    @MockBean
    private ProduitRepository produitRepository;

    @MockBean
    private SecteurActiviteRepository secteurActiviteRepository;

    @Test
    void testRetrieveAllFournisseurs() {
        // Mocking
        List<Fournisseur> fournisseurs = new ArrayList<>();
        when(fournisseurRepository.findAll()).thenReturn(fournisseurs);

        // Test
        List<Fournisseur> result = fournisseurService.retrieveAllFournisseurs();

        // Assertions
        assertSame(fournisseurs, result);
        assertTrue(result.isEmpty());

        // Vérification que la méthode findAll a été appelée
        verify(fournisseurRepository).findAll();
    }

    @Test
    void testAddFournisseur() {
        // Mocking
        Fournisseur fournisseur = new Fournisseur();
        when(fournisseurRepository.save(any(Fournisseur.class))).thenReturn(fournisseur);

        // Test
        Fournisseur result = fournisseurService.addFournisseur(fournisseur);

        // Assertions
        assertEquals(fournisseur, result);

        // Vérification que la méthode save a été appelée avec le bon argument
        verify(fournisseurRepository).save(fournisseur);
    }

    @Test
    void testUpdateFournisseur() {
        // Mocking
        Fournisseur fournisseur = new Fournisseur();
        when(fournisseurRepository.save(any(Fournisseur.class))).thenReturn(fournisseur);

        // Test
        Fournisseur result = fournisseurService.updateFournisseur(fournisseur);

        // Assertions
        assertEquals(fournisseur, result);

        // Vérification que la méthode save a été appelée avec le bon argument
        verify(fournisseurRepository).save(fournisseur);
    }

    @Test
    void testDeleteFournisseur() {
        // Test
        fournisseurService.deleteFournisseur(1L);

        // Vérification que la méthode deleteById a été appelée avec le bon argument
        verify(fournisseurRepository).deleteById(1L);
    }

    @Test
    void testRetrieveFournisseur() {
        // Mocking
        Long fournisseurId = 1L;
        Fournisseur mockFournisseur = new Fournisseur();
        when(fournisseurRepository.findById(fournisseurId)).thenReturn(Optional.of(mockFournisseur));

        // Test
        Fournisseur result = fournisseurService.retrieveFournisseur(fournisseurId);

        // Assertions
        assertEquals(mockFournisseur, result);

        // Vérification que la méthode findById a été appelée avec le bon argument
        verify(fournisseurRepository).findById(fournisseurId);
    }
    @Test
    void testRetrieveFournisseurwithJUNIT() {
        // Créer un fournisseur fictif dans la base de données
        Fournisseur mockFournisseur = new Fournisseur();
        mockFournisseur.setCode("123");
        mockFournisseur.setLibelle("FournisseurTest");
        mockFournisseur.setCategorieFournisseur(CategorieFournisseur.ORDINAIRE);

        // Ajouter des secteurs d'activité fictifs
        SecteurActivite secteur1 = new SecteurActivite();
        secteur1.setCodeSecteurActivite("Secteur1");
        secteur1.setLibelleSecteurActivite("Libelle1");

        SecteurActivite secteur2 = new SecteurActivite();
        secteur2.setCodeSecteurActivite("Secteur2");
        secteur2.setLibelleSecteurActivite("Libelle2");

        Set<SecteurActivite> secteurActivites = new HashSet<>();
        secteurActivites.add(secteur1);
        secteurActivites.add(secteur2);

        mockFournisseur.setSecteurActivites(secteurActivites);

        // Ajouter un détail de fournisseur fictif
        DetailFournisseur detailFournisseur = new DetailFournisseur();
        detailFournisseur.setEmail("test@example.com");
        detailFournisseur.setDateDebutCollaboration(new Date());
        detailFournisseur.setAdresse("AdresseTest");
        detailFournisseur.setMatricule("MatriculeTest");

        mockFournisseur.setDetailFournisseur(detailFournisseur);

        // Ajouter une facture fictive
        Facture facture = new Facture();
        facture.setMontantRemise(50.0f);
        facture.setMontantFacture(500.0f);
        facture.setDateCreationFacture(new Date());
        facture.setArchivee(false);

        Set<Facture> factures = new HashSet<>();
        factures.add(facture);

        mockFournisseur.setFactures(factures);

        fournisseurRepository.save(mockFournisseur);

        // Appeler la méthode du service pour récupérer le fournisseur par son ID
        Fournisseur result = fournisseurService.retrieveFournisseur(mockFournisseur.getIdFournisseur());

        // Vérifier que le fournisseur a été récupéré avec succès
        assertNotNull(result);
        assertEquals(mockFournisseur.getIdFournisseur(), result.getIdFournisseur());
        assertEquals("123", result.getCode());
        assertEquals("FournisseurTest", result.getLibelle());
        assertEquals(CategorieFournisseur.ORDINAIRE, result.getCategorieFournisseur());

        // Vérifier les secteurs d'activité
        assertEquals(2, result.getSecteurActivites().size());

        // Vérifier le détail du fournisseur
        assertNotNull(result.getDetailFournisseur());
        assertEquals("test@example.com", result.getDetailFournisseur().getEmail());
        assertEquals("AdresseTest", result.getDetailFournisseur().getAdresse());
        assertEquals("MatriculeTest", result.getDetailFournisseur().getMatricule());

        // Vérifier les factures
        assertEquals(1, result.getFactures().size());
        Facture retrievedFacture = result.getFactures().iterator().next();
        assertEquals(50.0f, retrievedFacture.getMontantRemise());
        assertEquals(500.0f, retrievedFacture.getMontantFacture());
    }




/*
    @Test
    void testAssignSecteurActiviteToFournisseur() {
        // Mocking
        Long idSecteurActivite = 1L;
        Long idFournisseur = 2L;

        // Créer un mock de Fournisseur
        Fournisseur mockFournisseur = new Fournisseur();
        when(fournisseurRepository.findById(idFournisseur)).thenReturn(Optional.of(mockFournisseur));

        // Créer un mock de SecteurActivite
        SecteurActivite mockSecteurActivite = new SecteurActivite();
        when(secteurActiviteRepository.findById(idSecteurActivite)).thenReturn(Optional.of(mockSecteurActivite));

        // Test
        fournisseurService.assignSecteurActiviteToFournisseur(idSecteurActivite, idFournisseur);

        // Vérification que la méthode findById a été appelée avec les bons arguments
        verify(fournisseurRepository).findById(idFournisseur);
        verify(secteurActiviteRepository).findById(idSecteurActivite);

        // Vérification que le secteur d'activité a été assigné au fournisseur
        assertTrue(mockFournisseur.getSecteurActivites().contains(mockSecteurActivite));

        // Vérification que la méthode save a été appelée sur le repository de fournisseur
        verify(fournisseurRepository).save(mockFournisseur);
    }




*/



}
