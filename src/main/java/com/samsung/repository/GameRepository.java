package com.samsung.repository;

import com.samsung.domain.Game;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Integer> {

    @Override
    @EntityGraph(attributePaths = {"genre", "company", "reviews"})
        // Решение N + 1, по сути внутри join
    List<Game> findAll();

    // SELECT * FROM book WHERE name = ?
    Game findByName(String name);
}
