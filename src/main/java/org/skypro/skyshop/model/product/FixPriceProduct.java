package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product {
    private static final int FIX_PRICE = 1551;

    public FixPriceProduct(String name, UUID id) {
        super(name, id);
    }

    public int getPrice() {
        return FIX_PRICE;
    }

    public String toString() {
        return getName() + " : Фиксированная цена " + FIX_PRICE;
    }

    public boolean isSpecial() {
        return true;
    }

}
