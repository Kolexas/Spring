package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {
    private final int price;

    public SimpleProduct(String name, int price, UUID id) {
        super(name, id);
        this.price = price;
        if (price <= 0) {
            throw new IllegalArgumentException("Отрицательная цена");
        }
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return getName() + ": " + price;
    }

    public boolean isSpecial() {
        return false;
    }
}