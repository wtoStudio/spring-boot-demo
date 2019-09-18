package demo.repository;

import demo.entity.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends Repository<Article, Long> {

    @Query(" from Article where author.id = :authorId")
    List<Article> findSomeArticle(@Param("authorId") Long userId);

    @Modifying
    @Query(" update Article set title = :title where author.id = :authorId")
    void updateSomeArticleTitle(@Param("authorId") Long userId, @Param("title") String title);
}
