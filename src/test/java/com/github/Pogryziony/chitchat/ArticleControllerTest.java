package com.github.Pogryziony.chitchat;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.Pogryziony.chitchat.web.controller.ArticleController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.github.Pogryziony.chitchat.core.entity.Article;
import com.github.Pogryziony.chitchat.core.service.ArticleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ArticleController.class)
public class ArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArticleService articleService;

    @Test
    public void testGetAllArticles() throws Exception {
        List<Article> articles = new ArrayList<>();
        articles.add(new Article());
        articles.add(new Article());
        given(articleService.getAllArticles()).willReturn(articles);

        mockMvc.perform(get("/articles"))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(articles)));
    }

    @Test
    public void testGetArticleById() throws Exception {
        Article article = new Article();
        given(articleService.getArticleById(1L)).willReturn(Optional.of(article));

        mockMvc.perform(get("/articles/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(article)));
    }

    @Test
    public void testAddArticle() throws Exception {
        Article article = new Article();
        given(articleService.createArticle(article)).willReturn(article);

        mockMvc.perform(post("/articles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(article)))
                .andExpect(status().isCreated())
                .andExpect(content().json(asJsonString(article)));
    }

    @Test
    public void testUpdateArticle() throws Exception {
        Article article = new Article();
        given(articleService.updateArticle(1L, article)).willReturn(article);

        mockMvc.perform(put("/articles/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(article)))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(article)));
    }

    @Test
    public void testDeleteArticle() throws Exception {
        mockMvc.perform(delete("/articles/1"))
                .andExpect(status().isNoContent());
    }

    private String asJsonString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}