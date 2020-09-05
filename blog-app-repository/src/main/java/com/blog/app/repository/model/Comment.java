package com.blog.app.repository.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment extends IdentifiableEntity {

    @Column
    private String content;
    @ManyToOne
    @JoinColumn(name = "story_id")
    private BlogStory blogStory;
}
