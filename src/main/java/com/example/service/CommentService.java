package com.example.service;

import com.example.domain.Comment;
import com.example.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * コメントを管理するサービス.
 *
 * @author koki.kurihara
 */
@Service
@Transactional
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    /**
     * 投稿IDが一致したコメントを取得する.
     *
     * @param articleId 投稿ID
     * @return 検索されたコメント
     */
    public List<Comment> searchCommentByArticleId(Integer articleId) {
        return commentRepository.findByArticleId(articleId);
    }

    /**
     * コメントを挿入する.
     *
     * @param name      コメント者名
     * @param content   コメント内容
     * @param articleId 投稿ID
     */
    public void insertComment(String name, String content, Integer articleId) {
        commentRepository.insertComment(name, content, articleId);
    }

    /**
     * コメントを削除する.
     *
     * @param articleId 投稿ID
     */
    public void deleteCommentByArticleId(Integer articleId) {
        commentRepository.deleteCommentByArticleId(articleId);
    }
}
