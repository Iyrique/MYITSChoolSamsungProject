package com.samsung.repository;

import com.samsung.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Integer> {

}
