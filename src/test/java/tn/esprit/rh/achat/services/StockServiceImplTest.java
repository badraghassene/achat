package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {StockServiceImpl.class})
@ExtendWith(SpringExtension.class)
class StockServiceImplTest {
    @MockBean
    private StockRepository stockRepository;
    @Autowired
    private StockServiceImpl stockService;

    @Test
    void testRetrieveAllStocks() {
        // Mocking
        List<Stock> stockList = new ArrayList<>();
        when(stockRepository.findAll()).thenReturn(stockList);

        // Test
        List<Stock> result = stockService.retrieveAllStocks();

        // Assertions
        assertSame(stockList, result);
        assertTrue(result.isEmpty());

        // Vérification que la méthode findAll a été appelée
        verify(stockRepository).findAll();
    }

    @Test
    void testRetrieveStock() {
        // Mocking
        Long id = 1L;
        Stock mockStock = new Stock();
        when(stockRepository.findById(id)).thenReturn(Optional.of(mockStock));

        // Test
        Stock result = stockService.retrieveStock(id);

        // Assertions
        assertEquals(mockStock, result);

        // Vérification que la méthode findById a été appelée avec le bon argument
        verify(stockRepository).findById(id);
    }

    @Test
    void testDeleteStock() {
        doNothing().when(stockRepository).deleteById((Long) any());

        // Exécutez la méthode delete du service.
        stockService.deleteStock(1L);

        // Assurez-vous que la méthode deleteById a été appelée une fois avec l'ID spécifié.
        verify(stockRepository).deleteById((Long) any());
    }

}