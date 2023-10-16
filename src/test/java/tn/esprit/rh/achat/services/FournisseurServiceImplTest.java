package tn.esprit.rh.achat.services;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.rh.achat.entities.DetailFournisseur;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.repositories.DetailFournisseurRepository;
import tn.esprit.rh.achat.repositories.FournisseurRepository;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;
import tn.esprit.rh.achat.services.FournisseurServiceImpl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class FournisseurServiceImplTest {

    @InjectMocks
    private FournisseurServiceImpl fournisseurService;

    @Mock
    private FournisseurRepository fournisseurRepository;

    @Mock
    private DetailFournisseurRepository detailFournisseurRepository;

    @Mock
    private SecteurActiviteRepository secteurActiviteRepository;

    @Test
    void testAddFournisseur() {
        Fournisseur fournisseur = new Fournisseur();
        DetailFournisseur detailFournisseur = new DetailFournisseur();
        detailFournisseur.setDateDebutCollaboration(new Date());

        when(fournisseurRepository.save(any(Fournisseur.class))).thenReturn(fournisseur);
        when(detailFournisseurRepository.save(any(DetailFournisseur.class))).thenReturn(detailFournisseur);

        Fournisseur result = fournisseurService.addFournisseur(fournisseur);

        assertNotNull(result);
        assertNotNull(result.getDetailFournisseur());
        verify(fournisseurRepository, times(1)).save(fournisseur);
        verify(detailFournisseurRepository, times(1)).save(detailFournisseur);
    }

    @Test
    void testUpdateFournisseur() {
        Fournisseur fournisseur = new Fournisseur();
        DetailFournisseur detailFournisseur = new DetailFournisseur();
        detailFournisseur.setDateDebutCollaboration(new Date());
        fournisseur.setDetailFournisseur(detailFournisseur);

        when(detailFournisseurRepository.save(any(DetailFournisseur.class))).thenReturn(detailFournisseur);
        when(fournisseurRepository.save(any(Fournisseur.class))).thenReturn(fournisseur);

        Fournisseur result = fournisseurService.updateFournisseur(fournisseur);

        assertNotNull(result);
        assertNotNull(result.getDetailFournisseur());
        verify(detailFournisseurRepository, times(1)).save(detailFournisseur);
        verify(fournisseurRepository, times(1)).save(fournisseur);
    }

    @Test
    void testAssignSecteurActiviteToFournisseur() {
        Long idSecteurActivite = 1L;
        Long idFournisseur = 2L;

        Fournisseur fournisseur = new Fournisseur();
        SecteurActivite secteurActivite = new SecteurActivite();
        when(fournisseurRepository.findById(idFournisseur)).thenReturn(java.util.Optional.of(fournisseur));
        when(secteurActiviteRepository.findById(idSecteurActivite)).thenReturn(java.util.Optional.of(secteurActivite));

        fournisseurService.assignSecteurActiviteToFournisseur(idSecteurActivite, idFournisseur);

        Set<SecteurActivite> secteurActivites = fournisseur.getSecteurActivites();
        assertNotNull(secteurActivites);
        assertTrue(secteurActivites.contains(secteurActivite));
        verify(fournisseurRepository, times(1)).save(fournisseur);
    }
}