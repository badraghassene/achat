package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.repositories.OperateurRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {OperateurServiceImpl.class})
@ExtendWith(SpringExtension.class)
class OperateurServiceImplTest {
    @MockBean
    private OperateurRepository operateurRepository;
    @MockBean
    private FactureRepository factureRepository;

    @Autowired
    private OperateurServiceImpl operateurService;

    @Test
    void testRetrieveAllOperateurs() {
        // Mocking
        List<Operateur> operateurList = new ArrayList<>();
        when(operateurRepository.findAll()).thenReturn(operateurList);

        // Test
        List<Operateur> result = operateurService.retrieveAllOperateurs();

        // Assertions
        assertSame(operateurList, result);
        assertTrue(result.isEmpty());

        // Vérification que la méthode findAll a été appelée
        verify(operateurRepository).findAll();
    }

    @Test
    void testRetrieveOperateur() {
        // Mocking
        Long id = 1L;
        Operateur mockOperateur = new Operateur();
        when(operateurRepository.findById(id)).thenReturn(Optional.of(mockOperateur));

        // Test
        Operateur result = operateurService.retrieveOperateur(id);

        // Assertions
        assertEquals(mockOperateur, result);

        // Vérification que la méthode findById a été appelée avec le bon argument
        verify(operateurRepository).findById(id);
    }

    @Test
    void testDeleteOperateur() {
        doNothing().when(operateurRepository).deleteById((Long) any());

        // Exécutez la méthode delete du service.
        operateurService.deleteOperateur(1L);

        // Assurez-vous que la méthode deleteById a été appelée une fois avec l'ID spécifié.
        verify(operateurRepository).deleteById((Long) any());
    }
}