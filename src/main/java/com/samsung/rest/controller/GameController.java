package com.samsung.rest.controller;

import com.samsung.domain.Game;
import com.samsung.rest.dto.GameDto;
import com.samsung.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping("/game")
    public GameDto createNewGame(
            @RequestParam String nameGame,
            @RequestParam String nameGenre, @RequestParam String release,
            @RequestParam String nameCompany, @RequestParam String country, @RequestParam String openDate, @RequestParam String staff
    ) {

        Game game = gameService.insert(nameGame, nameCompany, country, openDate, staff, nameGenre, release);
        return GameDto.toDto(game);
    }

    @GetMapping("/game")
    public List<GameDto> getAllGames() {

        return gameService
                .getAll()
                .stream()
                .map(GameDto::toDto)
                .collect(Collectors.toList());
    }


    @PostMapping("/game/{id}/")
    public GameDto updateBookById(
            @PathVariable int id,
            @RequestParam String newGameName, @RequestParam String country,
            @RequestParam String newGenreName, @RequestParam String openDate,
            @RequestParam String newCompanyName, @RequestParam String release
    ) {

        Game game = gameService.update(
                id,
                newGameName,
                newCompanyName, country, openDate, release, newGenreName
        );

        return GameDto.toDto(game);
    }

    @GetMapping("/game/{id}")
    public GameDto getGameById(@PathVariable int id) {

        return GameDto.toDto(gameService.getById(id));
    }

    @GetMapping("/game/name")
    public GameDto getGameByName(@RequestParam String name) {

        return GameDto.toDto(gameService.getByName(name));
    }

    @DeleteMapping("/game/{id}")
    //@PostMapping("/deleteBookById")
    public void deleteGameById(@PathVariable int id) {

        gameService.deleteById(id);
    }

}
