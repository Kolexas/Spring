package org.skypro.skyshop.model.search;

import org.skypro.skyshop.service.StorageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public List<SearchResult> search(String name) {
        return storageService.getSearchables().values().stream()
                .filter(searchable -> searchable.getSearchTerm().contains(name))
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toList());
    }
}
