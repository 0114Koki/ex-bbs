package com.example.form;

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
    private String name;
    /**
     * コメント内容
     */
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
