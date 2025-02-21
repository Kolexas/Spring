package org.skypro.skyshop.model.search;

import java.util.Objects;
import java.util.UUID;

public class SearchResult {
    private final UUID id;
    private final String name;
    private final String contentType;

    public SearchResult(UUID id, String name, String contentType) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContentType() {
        return contentType;
    }

    public static SearchResult fromSearchable(Searchable searchable) {
        return new SearchResult(searchable.getId(), searchable.getSearchTerm(), searchable.getTypeOfContent());
    }

    @Override
    public String toString() {
        return UUID.randomUUID() + " " + " " + name + " " + contentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchResult that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(contentType, that.contentType);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
