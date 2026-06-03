package com.claude.service;

import com.claude.entity.Article;
import com.claude.repository.ArticleRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public List<Article> getPublishedArticles() {
        return articleRepository.findByStatus(Article.ArticleStatus.PUBLISHED);
    }

    public Optional<Article> getArticleById(Long id) {
        return articleRepository.findById(id);
    }

    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    public Article updateArticle(Long id, Article articleDetails) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("文章不存在"));

        article.setTitle(articleDetails.getTitle());
        article.setExcerpt(articleDetails.getExcerpt());
        article.setContent(articleDetails.getContent());
        article.setCategory(articleDetails.getCategory());
        article.setTags(articleDetails.getTags());
        article.setStatus(articleDetails.getStatus());
        article.setReadTime(articleDetails.getReadTime());
        article.setAuthor(articleDetails.getAuthor());
        article.setSortOrder(articleDetails.getSortOrder());

        return articleRepository.save(article);
    }

    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }
}
