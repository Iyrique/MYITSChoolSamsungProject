package com.samsung.service;


import com.samsung.domain.Company;
import com.samsung.domain.Game;
import com.samsung.domain.Genre;
import com.samsung.domain.Reviews;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class LibDemoService implements LibDemo{

    private final CompanyService companyService;
    private final GenreService genreService;
    private final GameService gameService;
    private final ReviewsService reviewsService;

    @Transactional
    public void companyDemo() {

        Company newCompany = Company.builder()
                .name("Новая Компания")
                .country("Страна Компании")
                .openDate("2021")
                .staff("50")
                .build();

        companyService.insert(newCompany);
//        companyService.update(1, "Ivan", "Rus", 2, 4);
        System.out.println("======Все компании======");

        for (Company company : companyService.getAll()) {

            System.out.println(company);
        }

        System.out.println("======================\n");
    }

    @Override
    @Transactional
    public void genreDemo() {

        Genre newGenre = Genre.builder()
                .name("Новый Жанр")
                .build();

//        genreService.insert(newGenre);

        System.out.println("======Все жанры======");

        for (Genre genre : genreService.getAll()) {

            System.out.println(genre);
        }

        System.out.println("======================\n");
    }

    @Transactional
    public void reviewsDemo() {

        System.out.println("======Все отзывы======");

        for (Reviews reviews : reviewsService.getAll()) {

            System.out.println(reviews.getGame().getName() + " : " + reviews.getContent());
        }

        System.out.println("======================\n");
    }

    @Transactional
    public void gameDemo() {

        Game game = Game.builder()
                .name("Новая игра")
                .company(companyService.getByName("Новая Компания"))
                .releaseDate("50")
                .genre(genreService.getByName("Новый Жанр"))
                .build();

//        gameService.insert("CHEF", "4A Games", "Ukraine", 555, 444, "RPG", 2020);
        System.out.println("======Все игры======");

        for (Game game1 : gameService.getAll()) {

            System.out.println(
                    game1.getName() + " : " +
                            game1.getCompany().getName() + ", " +
                            game1.getGenre().getName()
            );
        }

        System.out.println("======================\n");

    }

    @Override
    @Transactional
    public void libDemo() {

        companyDemo();
        genreDemo();
        gameDemo();
        reviewsDemo();

    }
}
