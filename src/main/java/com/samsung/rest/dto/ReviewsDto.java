package com.samsung.rest.dto;

import com.samsung.domain.Game;
import com.samsung.domain.Reviews;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewsDto {

    private int id;

    private String content;

    public static Reviews toDomainObject(ReviewsDto reviewsDto, Game game) {

        return new Reviews(reviewsDto.getId(), reviewsDto.getContent(), game);
    }

    public static ReviewsDto toDto(Reviews reviews) {

        return new ReviewsDto(reviews.getId(), reviews.getContent());
    }
}
