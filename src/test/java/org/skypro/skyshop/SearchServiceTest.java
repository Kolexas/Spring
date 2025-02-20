package org.skypro.skyshop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;

import java.util.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;


    @Test
    void search_EmptyStorageService_ReturnsEmptyList() {
        when(storageService.getSearchables()).thenReturn(Collections.emptyMap());
        List<SearchResult> results = searchService.search("someSearchTerm");
        Assertions.assertTrue(results.isEmpty());
    }

    @Test
    void search_NoMatchingProductExist_ReturnsEmptyList() {
        Product banana = new SimpleProduct("Банан", 140, UUID.randomUUID());
        Product strawberry = new SimpleProduct("Клубника", 250, UUID.randomUUID());
        Map<UUID, Searchable> searchables = new HashMap<>();
        searchables.put(banana.getId(), banana);
        searchables.put(strawberry.getId(), strawberry);
        when(storageService.getSearchables()).thenReturn(searchables);
        List<SearchResult> results = searchService.search("E");
        Assertions.assertTrue(results.isEmpty());
    }

    @Test
    void search_MatchingProductExist_ReturnsSearchResults() {
        Product meat = new FixPriceProduct("Колбаса", UUID.randomUUID());
        Product raspberry = new DiscountedProduct("Малина", 10, 300, UUID.randomUUID());
        Map<UUID, Searchable> searchables = new HashMap<>();
        searchables.put(meat.getId(), meat);
        searchables.put(raspberry.getId(), raspberry);
        when(storageService.getSearchables()).thenReturn(searchables);
        List<SearchResult> results = searchService.search("Малина");
        SearchResult expectedResult = SearchResult.fromSearchable(raspberry);
        Assertions.assertEquals(expectedResult, results.get(0));
    }
}
