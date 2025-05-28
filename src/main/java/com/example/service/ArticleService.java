package com.example.service;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 記事を管理するサービス.
 *
 * @author koki.kurihara
 */
@Service
@Transactional
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentRepository commentRepository;

    /**
     * 記事を取得する.
     *
     * @return 検索された記事一覧
     */
    public List<Article> searchAllPre() {
        List<Article> articleList = articleRepository.findAll();
        for (Article article : articleList) {
            List<Comment> commentList = commentRepository.findByArticleId(article.getId());
            article.setCommentList(commentList);
        }
        return articleList;
    }

    /**
     * 記事を取得する.
     * テーブルを結合して記事を取得している。
     *
     * @return 検索された記事一覧
     */
    public List<Article> searchAll() {;
        return articleRepository.findAllWithComments();
    }


    /**
     * 記事を投稿する.
     *
     * @param name    記事名
     * @param content 投稿内容
     */
    public void insertArticle(String name, String content) {
        articleRepository.insertArticle(name, content);
    }

    /**
     * 記事を削除する.
     *
     * @param id ID
     */
    public void deleteArticleById(Integer id) {
        articleRepository.deleteArticleById(id);
    }
}
