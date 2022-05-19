package com.samsung.service;


import com.samsung.domain.Company;
import com.samsung.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    @Transactional
    public Company insert(Company company) {

        return companyRepository.save(company);
    }


    @Override
    @Transactional
    public Company update(int id, String newNameCompany, String country, int openDate, int staff) {


        Company author = Company.builder()
                .id(id)
                .name(newNameCompany)
                .openDate(openDate)
                .country(country)
                .staff(staff)
                .build();

        return companyRepository.save(author);
    }

    @Override
    public List<Company> getAll() {

        return companyRepository.findAll();
    }

    @Override
    public Company getById(int id) {

        return companyRepository.getById(id);
    }

    @Override
    public Company getByName(String nameCompany) {

        return companyRepository.findByName(nameCompany);
    }

    @Override
    @Transactional
    public void deleteById(int id) {

        companyRepository.deleteById(id);
    }
}
