package org.zerock.board.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity @Data
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseEntity {
    @Id
    private String email;

    private String password;

    private String name;
}
