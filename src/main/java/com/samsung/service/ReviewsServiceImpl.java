package com.samsung.service;


import com.samsung.domain.Game;
import com.samsung.domain.Reviews;
import com.samsung.repository.GameRepository;
import com.samsung.repository.RevievsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewsServiceImpl implements ReviewsService{

    private final RevievsRepository revievsRepository;
    private final GameRepository gameRepository;

    @Override
    @Transactional
    public Reviews insert(String content, int gameId) {

        Game game = gameRepository.findById(gameId).orElse(null);

        Reviews reviews = Reviews.builder()
                .content(content)
                .game(game)
                .build();

        return revievsRepository.save(reviews);
    }

    @Override
    public List<Reviews> getAll() {

        return revievsRepository.findAll();
    }

    @Override
    public Reviews getById(int id) {

        return revievsRepository.getById(id);
    }

    @Override
    public List<Reviews> getByBookId(int id) {

        return revievsRepository.findByGameId(id);
    }

    @Override
    @Transactional
    public void update(int id, String content) {

        revievsRepository.updateCommentById(id, content);
    }

    @Override
    @Transactional
    public void deleteById(int id) {

        revievsRepository.deleteById(id);
    }
}
