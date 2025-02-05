package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable {
    private final String articleName;
    private final String articleContent;
    private final UUID id;

    public Article(String articleName, String articleContent, UUID id) {
        this.articleContent = articleContent;
        this.articleName = articleName;
        this.id = id;
    }

    public String getArticleName() {
        return articleName;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return getArticleName() + " " + getArticleContent();
    }

    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return getArticleName() + " " + getArticleContent();
    }

    @JsonIgnore
    @Override
    public String getTypeOfContent() {
        return "ARTICLE";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false;
        return Objects.equals(articleName, article.articleName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(articleName);
    }

}
