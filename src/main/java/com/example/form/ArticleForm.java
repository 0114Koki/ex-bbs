package com.example.form;

import jakarta.validation.constraints.Size;

/**
 * 記事表示に使うフォーム.
 *
 * @author koki.kurihara
 */
public class ArticleForm {
    /**
     * 名前
     */
    @Size(min = 1, max = 50, message = "名前は1文字以上50文字以内で記載してください。")
    private String name;
    /**
     * 記事内容
     */
    @Size(min = 1, max = 1000, message = "投稿は1文字以上1000文字以内で記載してください。")
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
