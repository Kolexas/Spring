package org.skypro.skyshop.model.basket;

import java.util.List;

public class UserBasket {
    private final List<BasketItem> basketItemList;
    private final int total;

    public UserBasket(List<BasketItem> basketItemList) {
        this.basketItemList = basketItemList;
        total = basketItemList.stream()
                .mapToInt(BasketItem -> BasketItem.getProduct().getPrice() * BasketItem.getQuantity())
                .sum();
    }

    public List<BasketItem> getBasketItemList() {
        return basketItemList;
    }

    public int getTotal() {
        return total;
    }
}
