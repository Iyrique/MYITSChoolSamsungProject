package com.samsung.service;

import com.samsung.domain.Company;
import com.samsung.domain.Game;
import com.samsung.domain.Genre;
import com.samsung.repository.CompanyRepository;
import com.samsung.repository.GameRepository;
import com.samsung.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GenreRepository genreRepository;
    private final GameRepository gameRepository;
    private final CompanyRepository companyRepository;
    private final CompanyService companyService;
    private final GenreService genreService;

    @Override
    @Transactional
    public Game insert(String nameGame, String nameAuthor, String nameGenre, String release) {

        Company company = companyRepository.findByName(nameAuthor);
        if (company == null) {
            company = Company.builder()
                    .name(nameAuthor)
                    .build();
        }

        Genre genre = genreRepository.findByName(nameGenre);
        if (genre == null) {
            genre = Genre.builder()
                    .name(nameGenre)
                    .build();
        }

        Game game = Game.builder()
                .name(nameGame)
                .company(company)
                .genre(genre)
                .releaseDate(release)
                .build();

        return gameRepository.save(game);
    }


    @Override
    @Transactional
    public Game update(int id, String nameGame, String nameCompany, String release, String nameGenre) {

        Game game = Game.builder()
                .id(id)
                .name(nameGame)
                .company(companyService.getByName(nameCompany))
                .genre(genreService.getByName(nameGenre))
                .releaseDate(release)
                .build();

        return gameRepository.save(game);
    }

    @Override
    public List<Game> getAll() {

        return gameRepository.findAll();
    }

    @Override
    public Game getById(int id) {

        return gameRepository.getById(id);
    }

    @Override
    public Game getByName(String name) {

        return gameRepository.findByName(name);
    }

    @Transactional
    @Override
    public void deleteById(int id) {

        gameRepository.deleteById(id);
    }
}
