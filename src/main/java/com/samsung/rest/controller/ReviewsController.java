package com.samsung.rest.controller;

import com.samsung.domain.Reviews;
import com.samsung.rest.dto.ReviewsDto;
import com.samsung.service.ReviewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ReviewsController {

    private final ReviewsService reviewsService;

    @PostMapping("/reviews")
    public ReviewsDto createNewComment(
            @RequestParam String content,
            @RequestParam int bookId
    ) {

        Reviews comment = reviewsService.insert(content, bookId);

        return ReviewsDto.toDto(comment);
    }

    @GetMapping("/reviews")
    public List<ReviewsDto> getAllComments() {

        return reviewsService
                .getAll()
                .stream()
                .map(ReviewsDto::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/reviews/{id}/content")
    public void updateCommentById(
            @PathVariable int id,
            @RequestParam String content
    ) {

        reviewsService.update(id, content);
    }

    @DeleteMapping("/reviews/{id}")
    public void deleteCommentById(@PathVariable int id) {

        reviewsService.deleteById(id);
    }

    @GetMapping("/game/{id}/reviews")
    public List<ReviewsDto> getCommentsByBookId(@PathVariable int id) {

        return reviewsService
                .getByBookId(id)
                .stream()
                .map(ReviewsDto::toDto)
                .collect(Collectors.toList());
    }
}
