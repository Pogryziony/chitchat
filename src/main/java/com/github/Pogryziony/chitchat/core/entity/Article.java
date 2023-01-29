package com.github.Pogryziony.chitchat.core.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 150)
    private String title;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "comment_count", nullable = false)
    private int commentCount;

    @Column(name = "view_count", nullable = false)
    private int viewCount;

    @OneToMany(mappedBy = "article", orphanRemoval = true)
    private Set<Tag> tags = new LinkedHashSet<>();

    @Column(name = "modification_date")
    private LocalDateTime modificationDate;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "content", nullable = false, length = 50000)
    private String content;

    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @Column(name = "published", nullable = false)
    private Boolean published = false;
}
