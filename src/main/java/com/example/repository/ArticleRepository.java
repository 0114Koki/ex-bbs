package com.example.repository;

import com.example.domain.Article;
import com.example.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * articlesテーブルを操作するリポジトリ.
 *
 * @author koki.kurihara
 */
@Repository
public class ArticleRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    //一行Mapper
    public static final RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, i) -> {
        Article article = new Article();
        article.setId(rs.getInt("id"));
        article.setName(rs.getString("name"));
        article.setContent(rs.getString("content"));
        article.setCommentList(new ArrayList<>());
        return article;
    };

    //全行Mapper
    public static final ResultSetExtractor<List<Article>> LIST_RESULT_SET_EXTRACTOR = (rs) -> {
        Map<Integer, Article> map = new LinkedHashMap<>();
        while (rs.next()) {
            int articleId = rs.getInt("id");
            Article article = map.get(articleId);
            if (article == null) {
                article = new Article();
                article.setId(articleId);
                article.setName(rs.getString("name"));
                article.setContent(rs.getString("content"));
                article.setCommentList(new ArrayList<>());
                map.put(articleId, article);
            }
            int comId = rs.getInt("com_id");
            if (comId != 0) {//rsあればtrue
                Comment comment = new Comment();
                comment.setId(comId);
                comment.setName(rs.getString("com_name"));
                comment.setContent(rs.getString("com_content"));
                article.getCommentList().add(comment);
            }
        }
        return new ArrayList<>(map.values());
    };

    /**
     * 検索をする.
     *
     * @return 全件検索結果
     */
    public List<Article> findAllWithComments(){
        String sql = """
                SELECT
                    a.id AS id,
                    a.name AS name,
                    a.content AS content,
                    c.id AS com_id,
                    c.name AS com_name,
                    c.content AS com_content,
                    c.article_id AS article_id
                    FROM articles a
                    LEFT OUTER JOIN comments c
                    ON a.id = c.article_id
                    ORDER BY a.id DESC, c.id DESC
                    ;
                """;
        List<Article> articleList = template.query(sql, LIST_RESULT_SET_EXTRACTOR);
        return articleList;
    }
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
