package com.samsung.service;

import com.samsung.domain.Reviews;

import java.util.List;

public interface ReviewsService {

    Reviews insert(String content, int gameId);

    List<Reviews> getAll();

    Reviews getById(int id);

    List<Reviews> getByBookId(int id);

    void update(int id, String content);

    void deleteById(int id);
}
