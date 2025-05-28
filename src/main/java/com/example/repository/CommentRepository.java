package com.example.repository;

import com.example.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * commentsテーブルを操作するリポジトリ.
 *
 * @author koki.kurihara
 */
@Repository
public class CommentRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    public static final RowMapper<Comment> COMMENT_ROW_MAPPER = (rs, i) -> {
        Comment comment = new Comment();
        comment.setId(rs.getInt("id"));
        comment.setName(rs.getString("name"));
        comment.setContent(rs.getString("content"));
        comment.setArticleId(rs.getInt("article_id"));
        return comment;
    };

    /**
     * コメントを検索する.
     *
     * @param articleId 投稿ID
     * @return コメント検索結果(投稿ID一致)
     */
    public List<Comment> findByArticleId(Integer articleId) {
        String sql
                = """
                SELECT id, name, content, article_id
                 FROM comments
                 WHERE article_id=:articleId
                 ORDER BY id DESC;
                """;
        SqlParameterSource param
                = new MapSqlParameterSource().addValue("articleId", articleId);
        List<Comment> commentList = template.query(sql, param, COMMENT_ROW_MAPPER);
        return commentList;
    }

    /**
     * コメントを挿入する.
     *
     * @param name      コメント者名
     * @param content   コメント内容
     * @param articleId 投稿ID
     */
    public void insertComment(String name, String content, Integer articleId) {
        String sql = """
                INSERT INTO comments(name, content, article_id)
                 VALUES(:name, :content, :articleId);
                """;
        SqlParameterSource param
                = new MapSqlParameterSource()
                .addValue("name", name)
                .addValue("content", content)
                .addValue("articleId", articleId);
        template.update(sql, param);
    }

    /**
     * コメントをDBから削除する/
     *
     * @param articleId 投稿ID
     */
    public void deleteCommentByArticleId(Integer articleId) {
        String sql = """
                DELETE FROM comments
                 WHERE article_id=:articleId;
                """;
        SqlParameterSource param
                = new MapSqlParameterSource().addValue("articleId", articleId);
        template.update(sql, param);
    }
}
