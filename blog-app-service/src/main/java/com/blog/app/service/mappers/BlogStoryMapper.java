package com.blog.app.service.mappers;

import com.blog.app.repository.model.BlogStory;
import com.blog.app.service.response.BlogStoryResponse;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface BlogStoryMapper {

    @Mappings({
            @Mapping(source = "user.firstName", target = "firstName"),
            @Mapping(source = "user.lastName", target = "lastName")
    })
    BlogStoryResponse mapFromBlogStoryToBlogStoryResponse(@NonNull BlogStory blogStory);
}
