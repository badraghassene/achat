package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.repositories.ProduitRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {CategorieProduitServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CategorieProduitServiceImplTest {
    @MockBean
    private CategorieProduitRepository categorieProduitRepository;
    @MockBean
    private ProduitRepository produitRepository;

    @Autowired
    private CategorieProduitServiceImpl categorieProduitService;

    @Test
    void testRetrieveAllCategorieProduit() {
        // Mocking
        List<CategorieProduit> categorieProduitsList = new ArrayList<>();
        when(categorieProduitRepository.findAll()).thenReturn(categorieProduitsList);

        // Test
        List<CategorieProduit> result = categorieProduitService.retrieveAllCategorieProduits();

        // Assertions
        assertSame(categorieProduitsList, result);
        assertTrue(result.isEmpty());

        // Vérification que la méthode findAll a été appelée
        verify(categorieProduitRepository).findAll();
    }

    @Test
    void testRetrieveCategorieProduit() {
        // Mocking
        Long id = 1L;
        CategorieProduit mockCategorieProduit = new CategorieProduit();
        when(categorieProduitRepository.findById(id)).thenReturn(Optional.of(mockCategorieProduit));

        // Test
        CategorieProduit result = categorieProduitService.retrieveCategorieProduit(id);

        // Assertions
        assertEquals(mockCategorieProduit, result);

        // Vérification que la méthode findById a été appelée avec le bon argument
        verify(categorieProduitRepository).findById(id);
    }

    @Test
    void testDeleteCategorieProduit() {
        doNothing().when(categorieProduitRepository).deleteById((Long) any());

        // Exécutez la méthode delete du service.
        categorieProduitService.deleteCategorieProduit(1L);

        // Assurez-vous que la méthode deleteById a été appelée une fois avec l'ID spécifié.
        verify(categorieProduitRepository).deleteById((Long) any());
    }


}