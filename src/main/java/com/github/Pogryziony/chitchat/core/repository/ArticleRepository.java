package com.github.Pogryziony.chitchat.core.repository;

import com.github.Pogryziony.chitchat.core.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

}
