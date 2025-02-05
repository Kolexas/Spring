package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> storageOfProducts;
    private final Map<UUID, Article> storageOfArticle;


    public StorageService(Map<UUID, Product> storageOfProducts, Map<UUID, Article> storageOfArticle) {
        this.storageOfProducts = storageOfProducts;
        this.storageOfArticle = storageOfArticle;
        addProductsAndArticles();
    }


    public Collection<Product> getStorageOfProducts() {
        return storageOfProducts.values();
    }

    public Collection<Article> getStorageOfArticle() {
        return storageOfArticle.values();
    }

    private void addProductsAndArticles() {
        storageOfProducts.put(UUID.randomUUID(), new SimpleProduct("Банан", 150, UUID.randomUUID()));
        storageOfProducts.put(UUID.randomUUID(), new SimpleProduct("Клубника", 300, UUID.randomUUID()));
        storageOfProducts.put(UUID.randomUUID(), new FixPriceProduct("Малина", UUID.randomUUID()));
        storageOfProducts.put(UUID.randomUUID(), new DiscountedProduct("Колбаса", 10, 600, UUID.randomUUID()));
        storageOfArticle.put(UUID.randomUUID(), new Article("Вино", "из Грузии", UUID.randomUUID()));
    }

    public Map<UUID, Searchable> getSearchables() {
        Map<UUID, Searchable> collectionOfSearchables = new HashMap<>();
        collectionOfSearchables.putAll(storageOfProducts);
        collectionOfSearchables.putAll(storageOfArticle);
        return collectionOfSearchables;
    }
    }
