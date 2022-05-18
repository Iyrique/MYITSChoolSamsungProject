package com.samsung.repository;

import com.samsung.domain.Company;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
class CompanyRepositoryTest {

    public static final int EXISTING_AUTHOR_COUNT = 3;
    public static final int EXISTING_ID1 = 1;
    public static final String EXISTING_NAME1 = "Сергей Лукьяненко";
    public static final int EXISTING_ID2 = 2;
    public static final int EXISTING_ID3 = 3;
    public static final String EXISTING_NAME2 = "Александр Сергеевич Грибоедов";
    public static final String EXISTING_NAME3 = "Гарри Гаррисон";

    @Autowired
    private CompanyRepository companyRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @DisplayName("должен добавлять компанию")
    @Test
    void shouldInsertAuthor() {

        Company expectedAuthor = Company.builder()
                .id(4)
                .name("AAA")
                .country("Russia")
                .openDate(2022)
                .staff(700)
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
                .name("Ivan")
                .build();

        companyRepository.save(expectedAuthor1);
        Company actualAuthor = companyRepository.getById(EXISTING_ID1);

        assertThat(actualAuthor).isEqualTo(expectedAuthor1);
    }

    @DisplayName("должен возвращать все компании")
    @Test
    void shouldGetAllAuthors() {

        Company expectedCompany1 = Company.builder()
                .id(EXISTING_ID1)
                .name(EXISTING_NAME1)
                .build();
        Company expectedCompany2 = Company.builder()
                .id(EXISTING_ID2)
                .name(EXISTING_NAME2)
                .build();
        Company expectedCompany3 = Company.builder()
                .id(EXISTING_ID3)
                .name(EXISTING_NAME3)
                .build();

        assertThat(companyRepository.findAll().size()).isEqualTo(EXISTING_AUTHOR_COUNT);
        assertThat(companyRepository.findAll())
                .containsExactlyInAnyOrder(expectedCompany1, expectedCompany2, expectedCompany3);
    }

    @DisplayName("должен возвращать компанию по id")
    @Test
    void shouldGetAuthorById() {

        Company expectedCompany = Company.builder()
                .id(EXISTING_ID1)
                .name(EXISTING_NAME1)
                .build();

        Company actualCompany = companyRepository.getById(EXISTING_ID1);

        assertThat(actualCompany).isEqualTo(expectedCompany);
    }

    @DisplayName("должен возвращать компанию по имени")
    @Test
    void shouldGetAuthorByName() {

        Company expectedCompany = Company.builder()
                .id(EXISTING_ID1)
                .name(EXISTING_NAME1)
                .build();

        Company actualCompany = companyRepository.findByName(EXISTING_NAME1);

        assertThat(actualCompany).isEqualTo(expectedCompany);
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