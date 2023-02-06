package com.blog.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;

    private String title;

    @Column(length = 10000)
    private String content;
    private String imageName;
    private Date createdDate;

    /*relationship*/
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    private User user;
    /*end of relationship*/
}