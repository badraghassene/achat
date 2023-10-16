package tn.esprit.rh.achat;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.repositories.DetailFactureRepository;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.repositories.FournisseurRepository;
import tn.esprit.rh.achat.repositories.ReglementRepository;
import tn.esprit.rh.achat.services.FactureServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ContextConfiguration(classes = {FactureServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class FactureTest {

    @MockBean
    private FactureRepository factureRepository;

    @MockBean
    private DetailFactureRepository detailFactureRepository;

    @MockBean
    private ReglementRepository reglementRepository;

    @MockBean
    private FournisseurRepository fournisseurRepository;

    @Autowired
    private FactureServiceImpl factureService;


    @Test
    void testRetrieveAllFactures() {
        // Mocking
        List<Facture> factureList = new ArrayList<>();
        when(factureRepository.findAll()).thenReturn(factureList);
        // Test
        List<Facture> result = factureService.retrieveAllFactures();
        // Assertions
        assertSame(factureList, result);
        assertTrue(result.isEmpty());
        // Vérification que la méthode findAll a été appelée
        verify(factureRepository).findAll();
    }

    @Test
    void testRetrieveFacture() {
        // Mocking
        Long id = 1L;
        Facture mockFacture = new Facture();
        when(factureRepository.findById(id)).thenReturn(Optional.of(mockFacture));
        // Test
        Facture result = factureService.retrieveFacture(id);
        // Assertions
        assertEquals(mockFacture, result);
        // Vérification que la méthode findById a été appelée avec le bon argument
        verify(factureRepository).findById(id);
    }

    @Test
    void testAddFacture() {
        // Mocking
        Facture facture = new Facture();
        when(factureRepository.save(any(Facture.class))).thenReturn(facture);

        // Test
       Facture result = factureService.addFacture(facture);

        // Assertions
        assertEquals(facture, result);

        // Vérification que la méthode save a été appelée avec le bon argument
        verify(factureRepository).save(facture);
    }

}


