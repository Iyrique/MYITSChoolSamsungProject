package com.samsung.repository;

import com.samsung.domain.Company;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Класс CompanyRepositoryTest")
@DataJpaTest
class CompanyRepositoryTest {

    public static final int EXISTING_AUTHOR_COUNT = 3;
    public static final int EXISTING_ID1 = 1;
    public static final String EXISTING_NAME1 = "4A Games";
    public static final int EXISTING_ID2 = 2;
    public static final int EXISTING_ID3 = 3;
    public static final String EXISTING_NAME2 = "CD Projekt RED";
    public static final String EXISTING_NAME3 = "GSC Game World";

    @Autowired
    private CompanyRepository companyRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @DisplayName("должен добавлять компанию")
    @Test
    void shouldInsertAuthor() {

        Company expectedAuthor = Company.builder()
                .id(4)
                .name("Wargaming.net")
                .openDate(1998)
                .staff(1750)
                .country("Belarus")
                .build();

        companyRepository.save(expectedAuthor);
        Company actualAuthor = companyRepository.getById(4);

        assertThat(actualAuthor).isEqualTo(expectedAuthor);
    }

    @DisplayName("должен обновлять компанию")
    @Test
    void shouldUpdateAuthor() {
        Company expectedAuthor1 = Company.builder()
                .id(EXISTING_ID1)
                .name("3432")
                .build();

        companyRepository.save(expectedAuthor1);
        Company actualAuthor = companyRepository.getById(EXISTING_ID1);

        assertThat(actualAuthor).isEqualTo(expectedAuthor1);
    }

    @DisplayName("должен возвращать все компании")
    @Test
    void shouldGetAllAuthors() {

        Company expectedAuthor1 = Company.builder()
                .id(EXISTING_ID1)
                .name(EXISTING_NAME1)
                .country("Ukraine")
                .staff(146)
                .openDate(2006)
                .build();
        Company expectedAuthor2 = Company.builder()
                .id(EXISTING_ID2)
                .name(EXISTING_NAME2)
                .country("Poland")
                .staff(800)
                .openDate(2002)
                .build();
        Company expectedAuthor3 = Company.builder()
                .id(EXISTING_ID3)
                .name(EXISTING_NAME3)
                .country("Ukraine")
                .staff(50)
                .openDate(1995)
                .build();

        assertThat(companyRepository.findAll().size()).isEqualTo(EXISTING_AUTHOR_COUNT);
        assertThat(companyRepository.findAll())
                .containsExactlyInAnyOrder(expectedAuthor1, expectedAuthor2, expectedAuthor3);
    }

    @DisplayName("должен возвращать компанию по id")
    @Test
    void shouldGetAuthorById() {

        Company expectedAuthor = Company.builder()
                .id(EXISTING_ID1)
                .name(EXISTING_NAME1)
                .country("Ukraine")
                .staff(146)
                .openDate(2006)
                .build();

        Company actualAuthor = companyRepository.getById(EXISTING_ID1);

        assertThat(actualAuthor).isEqualTo(expectedAuthor);
    }

    @DisplayName("должен возвращать компанию по названию")
    @Test
    void shouldGetAuthorByName() {

        Company expectedAuthor = Company.builder()
                .id(EXISTING_ID1)
                .name(EXISTING_NAME1)
                .country("Ukraine")
                .staff(146)
                .openDate(2006)
                .build();

        Company actualAuthor = companyRepository.findByName(EXISTING_NAME1);

        assertThat(actualAuthor).isEqualTo(expectedAuthor);
    }

    @DisplayName("должен удалять компанию по id")
    @Test
    void shouldDeleteAuthorById() {

        int sizeBefore = companyRepository.findAll().size();

        companyRepository.deleteById(EXISTING_ID1);
        entityManager.flush();

        int sizeAfter = companyRepository.findAll().size();
        assertThat(sizeBefore - sizeAfter).isEqualTo(1);
    }
}