package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.repositories.FournisseurRepository;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ContextConfiguration(classes = {SecteurActiviteServiceImpl.class})
@ExtendWith(SpringExtension.class)
class SecteurActiviteServiceImplTest {

    @MockBean
    private SecteurActiviteRepository secteurActiviteRepository;
    @MockBean
    private FournisseurRepository fournisseurRepository;

    @Autowired
    private SecteurActiviteServiceImpl secteurActiviteService;

    @Test
    void testRetrieveAllCategorieProduit() {
        // Mocking
        List<SecteurActivite> secteurActiviteList = new ArrayList<>();
        when(secteurActiviteRepository.findAll()).thenReturn(secteurActiviteList);

        // Test
        List<SecteurActivite> result = secteurActiviteService.retrieveAllSecteurActivite();

        // Assertions
        assertSame(secteurActiviteList, result);
        assertTrue(result.isEmpty());

        // Vérification que la méthode findAll a été appelée
        verify(secteurActiviteRepository).findAll();
    }

    @Test
    void testRetrieveCategorieProduit() {
        // Mocking
        Long id = 1L;
        SecteurActivite mockSecteurActivite = new SecteurActivite();
        when(secteurActiviteRepository.findById(id)).thenReturn(Optional.of(mockSecteurActivite));

        // Test
        SecteurActivite result = secteurActiviteService.retrieveSecteurActivite(id);

        // Assertions
        assertEquals(mockSecteurActivite, result);

        // Vérification que la méthode findById a été appelée avec le bon argument
        verify(secteurActiviteRepository).findById(id);
    }

    @Test
    void testDeleteCategorieProduit() {
        doNothing().when(secteurActiviteRepository).deleteById((Long) any());

        // Exécutez la méthode delete du service.
        secteurActiviteService.deleteSecteurActivite(1L);

        // Assurez-vous que la méthode deleteById a été appelée une fois avec l'ID spécifié.
        verify(secteurActiviteRepository).deleteById((Long) any());
    }


}