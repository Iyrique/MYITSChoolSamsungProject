package com.samsung.service;

import com.samsung.domain.Game;

import java.util.List;

public interface GameService {

    Game insert(String name, String company, String country, int openDate, int staff, String genre, int release);

    Game update(int id, String name, String company, String country, int openDate, int release, String genre);

    List<Game> getAll();

    Game getById(int id);

    Game getByName(String name);

    void deleteById(int id);
}
