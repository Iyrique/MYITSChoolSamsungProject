package com.samsung.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincrement
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "release")
    private int releaseDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "game") // Вид связи один ко многим (у одной игры много комментов)
    private List<Reviews> reviews;

    @ManyToOne(targetEntity = Company.class, fetch = FetchType.LAZY) // Вид связи многое к одному (у одной компании
    @JoinColumn(name = "company_id")                                 // много игр), выгружаем компанию, когда необходимо
    private Company company;

    @ManyToOne(targetEntity = Genre.class, fetch = FetchType.LAZY) // Вид связи многое к одному (у одного жанра
    @JoinColumn(name = "genre_id")                                 // много игр), выгружаем жанр, когда необходимо
    private Genre genre;
}
