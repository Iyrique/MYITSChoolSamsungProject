package com.samsung.repository;

import com.samsung.domain.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RevievsRepository extends JpaRepository<Reviews, Integer> {

    @Modifying
    @Query("update Reviews r set r.content = :content where r.id = :id")
    void updateCommentById(@Param("id") int id,
                           @Param("content") String content);

    List<Reviews> findByGameId(int id);
}
