package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.repositories.ReglementRepository;
import tn.esprit.rh.achat.services.ReglementServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ReglementServiceImplTest {

    @Mock
    private FactureRepository factureRepository;

    @Mock
    private ReglementRepository reglementRepository;

    @InjectMocks
    private ReglementServiceImpl reglementService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllReglements() {
        // Mocking
        List<Reglement> mockReglements = new ArrayList<>();
        when(reglementRepository.findAll()).thenReturn(mockReglements);

        // Test
        List<Reglement> result = reglementService.retrieveAllReglements();

        // Assertions
        assertEquals(mockReglements, result);

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
