package com.example.form;

import jakarta.validation.constraints.Size;

/**
 * コメント表示に使うフォーム.
 *
 * @author koki.kurihara
 */
public class CommentForm {
    /**
     * 投稿ID
     */
    private String articleId;
    /**
     * コメント者名
     */
    @Size(min = 1, max = 50, message = "名前は1文字以上50文字以内で記載してください。")
    private String name;
    /**
     * コメント内容
     */
    @Size(min = 1, max = 140, message = "コメントは1文字以上140文字以内で記載してください。")
    private String content;

    public CommentForm() {
    }

    public CommentForm(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getArticleId() {
        return articleId;
    }

    public int getIntArticleId() {
        return Integer.parseInt(articleId);
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CommentForm{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
