package com.blog.app.boot.controller;

import com.blog.app.service.BlogStoryService;
import com.blog.app.service.request.SignUpUserRequest;
import com.blog.app.service.response.BlogStoryResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Controller
@AllArgsConstructor
@Slf4j
public class PagesController {

    private final BlogStoryService blogStoryService;

    @GetMapping("/sign-in")
    public String signIn() {
        return "sign-in";
    }

    @GetMapping("/sign-up")
    public String signUpPage(final SignUpUserRequest user) {
        return "sign-up";
    }

    @GetMapping("/index")
    public String index(final Model model) {
        // get list of stories ordered by create date time descending to view in home page.
        model.addAttribute("stories", blogStoryService.getAllBlogStories());
        return "home";
    }

    @GetMapping("/story")
    public String story(@RequestParam @NotNull final Long id, final Model model) {
        // get list of stories ordered by create date time descending to view in home page.
        final Optional<BlogStoryResponse> story = blogStoryService.getStoryById(id);
        model.addAttribute("story", story.get());
        return "blog-post";
    }
}
