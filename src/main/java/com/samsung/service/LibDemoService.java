package com.samsung.service;


import com.samsung.domain.Company;
import com.samsung.domain.Game;
import com.samsung.domain.Genre;
import com.samsung.domain.Reviews;
import com.samsung.repository.CompanyRepository;
import com.samsung.repository.GameRepository;
import com.samsung.repository.GenreRepository;
import com.samsung.repository.RevievsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class LibDemoService implements LibDemo{

    private final CompanyRepository companyRepository;
    private final GenreRepository genreRepository;
    private final GameRepository gameRepository;
    private final RevievsRepository revievsRepository;

    @Transactional
    public void companyDemo() {

        Company newCompany = Company.builder()
                .name("Новая Компания")
                .country("Страна Компании")
                .openDate(2021)
                .staff(50)
                .build();

        companyRepository.save(newCompany);

        System.out.println("======Все компании======");

        for (Company company : companyRepository.findAll()) {

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

        genreRepository.save(newGenre);

        System.out.println("======Все жанры======");

        for (Genre genre : genreRepository.findAll()) {

            System.out.println(genre);
        }

        System.out.println("======================\n");
    }

    @Transactional
    public void reviewsDemo() {

        System.out.println("======Все отзывы======");

        for (Reviews reviews : revievsRepository.findAll()) {

            System.out.println(reviews.getGame().getName() + " : " + reviews.getContent());
        }

        System.out.println("======================\n");
    }

    @Transactional
    public void gameDemo() {

        Game game = Game.builder()
                .name("Новая игра")
                .company(companyRepository.findByName("Новая Компания"))
                .releaseDate(50)
                .genre(genreRepository.findByName("Новый Жанр"))
                .build();

        gameRepository.save(game);

        System.out.println("======Все игры======");

        for (Game game1 : gameRepository.findAll()) {

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
