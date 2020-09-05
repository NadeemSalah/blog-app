package com.blog.app.service;

import com.blog.app.repository.BlogStoryRepository;
import com.blog.app.repository.model.BlogStory;
import com.blog.app.service.mappers.BlogStoryMapper;
import com.blog.app.service.response.BlogStoryResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class BlogStoryService {

    private final BlogStoryRepository blogStoryRepository;
    private final BlogStoryMapper blogStoryMapper;

    public List<BlogStoryResponse> getAllBlogStories() {
        return blogStoryRepository.findAllByOrderByCreateDateTimeDesc()
                .stream()
                .map(blogStoryMapper::mapFromBlogStoryToBlogStoryResponse)
                .collect(Collectors.toList());
    }

    public Optional<BlogStoryResponse> getStoryById(final @NonNull Long id) {
        final Optional<BlogStory> story = blogStoryRepository.findById(id);

        if (story.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(blogStoryMapper.mapFromBlogStoryToBlogStoryResponse(story.get()));
    }
}
