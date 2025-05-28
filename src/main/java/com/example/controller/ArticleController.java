package com.example.controller;

import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.service.ArticleService;
import com.example.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 記事関連機能を管理するコントローラ.
 *
 * @author koki.kurihara
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;

/// Formは必須。formタグのth:objectを使うとき,
///既にそのリクエストパラメータが存在していないとエラーになる。
    /**
     * 記事画面を表示する.
     *
     * @return 記事画面
     */
    @GetMapping("")
    public String index(Model model, ArticleForm articleForm, CommentForm commentForm) {
        model.addAttribute("articleList", articleService.searchAll());
        return "bbs/bbs";
    }

    /**
     * 記事を投稿する.
     *
     * @param articleForm フォーム
     * @param model       リクエストパラメータ
     * @return indexメソッドにリダイレクト
     */
    @PostMapping("/post-article")
    public String postArticle(ArticleForm articleForm, Model model) {
        articleService.insertArticle(articleForm.getName(), articleForm.getContent());
        return "redirect:/article";
    }

    /**
     * コメント投稿をする.
     *
     * @param commentForm フォーム
     * @param model       リクエストスコープ
     * @return indexメソッドにリダイレクト
     */
    @PostMapping("/post-comment")
    public String postComment(CommentForm commentForm, Model model) {
        commentService.insertComment(commentForm.getName(), commentForm.getContent(), commentForm.getIntArticleId());
        return "redirect:/article";
    }

    /**
     * 記事とコメントを削除する.
     *
     * @param commentForm フォーム
     * @param model       リクエストパラメータ
     * @return indexメソッドにリダイレクト
     */
    @PostMapping("/delete")
    public String deleteArticleAndComment(CommentForm commentForm, Model model) {
        commentService.deleteCommentByArticleId(commentForm.getIntArticleId());
        articleService.deleteArticleById(commentForm.getIntArticleId());
        return "redirect:/article";
    }
}
