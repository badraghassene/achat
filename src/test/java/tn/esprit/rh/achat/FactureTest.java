package tn.esprit.rh.achat;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.repositories.*;
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

    @MockBean
    private OperateurRepository operateurRepository;




   

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


