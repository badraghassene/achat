package tn.esprit.rh.achat;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.rh.achat.entities.*;
import tn.esprit.rh.achat.repositories.*;
import tn.esprit.rh.achat.services.FactureServiceImpl;
import tn.esprit.rh.achat.services.ReglementServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {FactureServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class FactureServiceImplTest {

    @MockBean
    private FactureRepository factureRepository;
    @MockBean
    private OperateurRepository operateurRepository;
    @MockBean
    private DetailFactureRepository detailFactureRepository;
    @MockBean
    private FournisseurRepository fournisseurRepository;
    @MockBean
    private ProduitRepository produitRepository;
    @MockBean
    private ReglementServiceImpl reglementService;

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
        assertEquals(2, result.size());

        // Vérification que la méthode findAll a été appelée
        verify(factureRepository).findAll();
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


