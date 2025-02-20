package org.skypro.skyshop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;

import java.util.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {

    @Mock
    ProductBasket productBasket;

    @Mock
    StorageService storageService;

    @InjectMocks
    BasketService basketService;

    @Test
    void add_NoExistProduct_ThrowException() {
        UUID noProduct = UUID.randomUUID();
        when(storageService.getProductById(noProduct)).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchProductException.class, () -> basketService.addProduct(noProduct));
    }

    @Test
    void add_ExistProduct_addProductCall() {
        UUID ProductId = UUID.randomUUID();
        Product product = Mockito.mock(Product.class);
        when(storageService.getProductById(ProductId)).thenReturn(Optional.of(product));
        basketService.addProduct(ProductId);
        Mockito.verify(productBasket).addProduct(ProductId);
    }

    @Test
    void return_EmptyUserBasket_ifProductBasketIsEmpty() {
        when(productBasket.productList()).thenReturn(Collections.emptyMap());
        UserBasket result = basketService.getUserBasket();
        Assertions.assertTrue(result.getBasketItemList().isEmpty());
    }

    @Test
    void return_UserBasket_ifProductBasketHaveProducts() {
        Product banana = new SimpleProduct("Банана", 150, UUID.randomUUID());
        int quantity = 1;
        Map<UUID, Integer> productMap = new HashMap<>();
        productMap.put(banana.getId(), quantity);
        when(productBasket.productList()).thenReturn(productMap);
        when(storageService.getProductById(banana.getId())).thenReturn(Optional.of(banana));
        UserBasket result = basketService.getUserBasket();
        Assertions.assertEquals(1, result.getBasketItemList().size());
        BasketItem item = result.getBasketItemList().get(0);
        Assertions.assertEquals(banana, item.getProduct());
        Assertions.assertEquals(quantity, item.getQuantity());
    }
}

