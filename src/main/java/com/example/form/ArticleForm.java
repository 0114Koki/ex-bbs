package com.example.form;

/**
 * 記事表示に使うフォーム.
 *
 * @author koki.kurihara
 */
public class ArticleForm {
    /**
     * 名前
     */
    private String name;
    /**
     * 記事内容
     */
    private String content;

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
        return "ArticleForm{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
