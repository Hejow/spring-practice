package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @ManyToOne
    private Member member;

    @Column(columnDefinition = "integer default 0")
    private int viewCount;
}
