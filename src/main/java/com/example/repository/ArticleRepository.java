package com.example.repository;

import com.example.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * articlesテーブルを操作するリポジトリ.
 *
 * @author koki.kurihara
 */
@Repository
public class ArticleRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    public static final RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, i) -> {
        Article article = new Article();
        article.setId(rs.getInt("id"));
        article.setName(rs.getString("name"));
        article.setContent(rs.getString("content"));
        /// あとでセットがいいのかも
        // article.setCommentList((List<Comment>) rs.getObject("commentList"));
        return article;
    };

    /**
     * 全件検索を行う.
     *
     * @return 全件検索結果
     */
    public List<Article> findAll() {
        String sql =
                """
                        SELECT id, name, content
                         FROM articles
                         ORDER BY id DESC;
                        """;
        List<Article> articleList
                = template.query(sql, ARTICLE_ROW_MAPPER);
        return articleList;
    }

    /**
     * 記事を挿入する.
     *
     * @param name    記事名
     * @param content 投稿内容
     */
    public void insertArticle(String name, String content) {
        String sql = """
                INSERT INTO articles(name, content)
                 VALUES(:name, :content);
                """;
        SqlParameterSource param
                = new MapSqlParameterSource()
                .addValue("name", name)
                .addValue("content", content);
        template.update(sql, param);
    }

    /**
     * 記事をDBから削除する.
     *
     * @param id ID
     */
    public void deleteArticleById(Integer id) {
        String sql = """
                DELETE FROM articles
                 WHERE id=:id;
                """;
        SqlParameterSource param
                = new MapSqlParameterSource().addValue("id", id);
        template.update(sql, param);
    }
}
