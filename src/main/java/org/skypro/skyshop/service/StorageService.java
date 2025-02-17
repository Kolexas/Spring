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
        this.storageOfProducts = new HashMap<>();
        this.storageOfArticle = new HashMap<>();
        this.addProductsAndArticles();
    }


    public Collection<Product> getStorageOfProducts() {
        return storageOfProducts.values();
    }

    public Collection<Article> getStorageOfArticle() {
        return storageOfArticle.values();
    }

    private void addProductsAndArticles() {
        Product banana = new SimpleProduct("Банан", 150, UUID.randomUUID());
        Product strawberry = new SimpleProduct("Клубника", 300, UUID.randomUUID());
        Product raspberry = new FixPriceProduct("Малина", UUID.randomUUID());
        Product meat = new DiscountedProduct("Колбаса", 10, 600, UUID.randomUUID());
        Article vine = new Article("Вино", "из Грузии", UUID.randomUUID());
        storageOfProducts.put(banana.getId(), banana);
        storageOfProducts.put(strawberry.getId(), strawberry);
        storageOfProducts.put(raspberry.getId(), raspberry);
        storageOfProducts.put(meat.getId(), meat);
        storageOfArticle.put(vine.getId(), vine);
    }

    public Map<UUID, Searchable> getSearchables() {
        Map<UUID, Searchable> collectionOfSearchables = new HashMap<>();
        collectionOfSearchables.putAll(storageOfProducts);
        collectionOfSearchables.putAll(storageOfArticle);
        return collectionOfSearchables;
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(storageOfProducts.get(id));
    }
}
