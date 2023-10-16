package tn.esprit.rh.achat.services;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.repositories.ReglementRepository;
import tn.esprit.rh.achat.services.ProduitServiceImpl;
import tn.esprit.rh.achat.services.ReglementServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ContextConfiguration(classes = {ReglementServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class ReglementServiceImplTest {

    @MockBean
    private ReglementRepository reglementRepository;
    @MockBean
    private FactureRepository factureRepository;

    @Autowired
    private ReglementServiceImpl reglementService;

    @Test
    void testRetrieveAllReglements() {
        // Mocking
        List<Reglement> reglementList = new ArrayList<>();
        when(reglementRepository.findAll()).thenReturn(reglementList);

        // Test
        List<Reglement> result = reglementService.retrieveAllReglements();

        // Assertions
        assertSame(reglementList, result);
        assertTrue(result.isEmpty());

        // Vérification que la méthode findAll a été appelée
        verify(reglementRepository).findAll();
    }


    @Test
    void testAddReglement() {
        // Mocking
        Reglement reglement = new Reglement();
        when(reglementRepository.save(any(Reglement.class))).thenReturn(reglement);

        // Test
        Reglement result = reglementService.addReglement(reglement);

        // Assertions
        assertEquals(reglement, result);

        // Vérification que la méthode save a été appelée avec le bon argument
        verify(reglementRepository).save(reglement);
    }

    @Test
    void testRetrieveReglement() {
        // Mocking
        Long id = 1L;
        Reglement mockReglement = new Reglement();
        when(reglementRepository.findById(id)).thenReturn(Optional.of(mockReglement));

        // Test
        Reglement result = reglementService.retrieveReglement(id);

        // Assertions
        assertEquals(mockReglement, result);

        // Vérification que la méthode findById a été appelée avec le bon argument
        verify(reglementRepository).findById(id);
    }

    @Test
    void testRetrieveReglementByFacture() {
        // Mocking
        Long idFacture = 1L;
        List<Reglement> mockReglements = new ArrayList<>();
        when(reglementRepository.retrieveReglementByFacture(idFacture)).thenReturn(mockReglements);

        // Test
        List<Reglement> result = reglementService.retrieveReglementByFacture(idFacture);

        // Assertions
        assertEquals(mockReglements, result);

        // Vérification que la méthode retrieveReglementByFacture a été appelée avec le bon argument
        verify(reglementRepository).retrieveReglementByFacture(idFacture);
    }

    @Test
    void testGetChiffreAffaireEntreDeuxDate() {
        // Mocking
        Date startDate = new Date();
        Date endDate = new Date();
        float mockChiffreAffaire = 100.0f;
        when(reglementRepository.getChiffreAffaireEntreDeuxDate(startDate, endDate)).thenReturn(mockChiffreAffaire);

        // Test
        float result = reglementService.getChiffreAffaireEntreDeuxDate(startDate, endDate);

        // Assertions
        assertEquals(mockChiffreAffaire, result, 0.01); // 0.01 est la marge d'erreur acceptée pour les valeurs flottantes

        // Vérification que la méthode getChiffreAffaireEntreDeuxDate a été appelée avec les bonnes dates
        verify(reglementRepository).getChiffreAffaireEntreDeuxDate(startDate, endDate);
    }


}
