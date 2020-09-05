package com.blog.app.service.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogStoryResponse {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createDateTime;
    private String firstName;
    private String lastName;
}
