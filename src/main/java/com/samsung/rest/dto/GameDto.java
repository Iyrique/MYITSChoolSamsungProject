package com.samsung.rest.dto;

import com.samsung.domain.Game;
import com.samsung.domain.Reviews;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameDto {

    private int id;

    private String name;

    private int releaseDate;

    private CompanyDto companyDto;

    private GenreDto genreDto;

    private List<ReviewsDto> reviewsDtoList;

    public static Game toDomainObject(GameDto gameDto, List<Reviews> reviews) {

        return new Game(gameDto.getId(), gameDto.getName(), gameDto.getReleaseDate(),reviews, CompanyDto.toDomainObject(gameDto.getCompanyDto()), GenreDto.toDomainObject(gameDto.getGenreDto()));
    }

    public static GameDto toDto(Game game) {

        List<ReviewsDto> commentDtoList;
        if (game.getReviews() != null) {
            commentDtoList = game.getReviews().stream().map(ReviewsDto::toDto).collect(Collectors.toList());
        } else {
            commentDtoList = new ArrayList<>();
        }
        return new GameDto(
                game.getId(),
                game.getName(),
                game.getReleaseDate(),
                CompanyDto.toDto(game.getCompany()),
                GenreDto.toDto(game.getGenre()),
                commentDtoList
        );

    }
}
