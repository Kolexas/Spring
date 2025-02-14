package org.skypro.skyshop.model.product;

import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    private final String name;
    private final UUID id;

    public Product(String name, UUID id) {
        this.name = name;
        this.id = id;
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Такого товара не существует");
        }
    }

    public abstract boolean isSpecial();

    public String getName() {
        return name;
    }

    public abstract int getPrice();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String getSearchTerm() {
        return getName();
    }

    @Override
    public String getTypeOfContent() {
        return "PRODUCT";
    }

    public UUID getId() {
        return id;
    }
}