package com.example.domain;

/**
 * コメント情報を表すドメイン.
 *
 * @author koki.kurihara
 */
public class Comment {
    /**
     * ID
     */
    private Integer id;
    /**
     * コメント者名
     */
    private String name;
    /**
     * コメント内容
     */
    private String content;
    /**
     * 投稿ID
     */
    private Integer articleId;

    public Comment() {
    }

    public Comment(Integer id, String name, String content, Integer articleId) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.articleId = articleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", articleId=" + articleId +
                '}';
    }
}
