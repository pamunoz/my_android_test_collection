package com.pfariasmunoz.indensales.data.models;

/**
 * Created by Pablo Farias on 09-05-17.
 */

public class SaleData {

    private String mArticleId;
    private Article mArticle;
    private ArticleSale mArticleSale;

    public SaleData() {
    }

    public SaleData(String articleId, Article article, ArticleSale articleSale) {
        mArticleId = articleId;
        mArticle = article;
        mArticleSale = articleSale;
    }

    public String getArticleId() {
        return mArticleId;
    }

    public void setArticleId(String articleId) {
        mArticleId = articleId;
    }

    public Article getArticle() {
        return mArticle;
    }

    public void setArticle(Article article) {
        mArticle = article;
    }

    public ArticleSale getArticleSale() {
        return mArticleSale;
    }

    public void setArticleSale(ArticleSale articleSale) {
        mArticleSale = articleSale;
    }
}
