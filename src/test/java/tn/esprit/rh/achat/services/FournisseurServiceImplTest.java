package tn.esprit.rh.achat.services;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.rh.achat.entities.CategorieFournisseur;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.repositories.DetailFournisseurRepository;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.repositories.FournisseurRepository;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {FournisseurServiceImpl.class})
@ExtendWith(SpringExtension.class)
class FournisseurServiceImplTest {

    @MockBean
    private FournisseurRepository fournisseurRepository;
    @MockBean
    private FactureRepository factureRepository;
    @MockBean
    private SecteurActiviteRepository secteurActiviteRepository;
    @MockBean
    private DetailFournisseurRepository detailFournisseurRepository;
   // @MockBean
   // private CategorieFournisseur categorieFournisseur;


    @Autowired
    private FournisseurServiceImpl fournisseurService;

    @Test
    void testRetrieveAllFournisseur() {
        // Mocking
        List<Fournisseur> fournisseurList = new ArrayList<>();
        when(fournisseurRepository.findAll()).thenReturn(fournisseurList);

        // Test
        List<Fournisseur> result = fournisseurService.retrieveAllFournisseurs();

        // Assertions
        assertSame(fournisseurList, result);
        assertTrue(result.isEmpty());

        // Vérification que la méthode findAll a été appelée
        verify(fournisseurRepository).findAll();
    }
/*
    @Test
    void testRetrieveFournisseur() {
        // Mocking
        Long id = 1L;
        Fournisseur mockFournisseur = new Fournisseur();
        when(fournisseurRepository.findById(id)).thenReturn(Optional.of(mockFournisseur));

        // Test
        Fournisseur result = fournisseurService.retrieveFournisseur(id);

        // Assertions
        assertEquals(mockFournisseur, result);

        // Vérification que la méthode findById a été appelée avec le bon argument
        verify(fournisseurRepository).findById(id);
    }

    @Test
    void testDeleteFournisseur() {
        doNothing().when(fournisseurRepository).deleteById((Long) any());

        // Exécutez la méthode delete du service.
        fournisseurService.deleteFournisseur(1L);

        // Assurez-vous que la méthode deleteById a été appelée une fois avec l'ID spécifié.
        verify(fournisseurRepository).deleteById((Long) any());
    }
*/
}