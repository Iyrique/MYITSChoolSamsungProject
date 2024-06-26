package com.samsung.service;

import com.samsung.domain.Company;

import java.util.List;

public interface CompanyService {

    Company insert(Company company);

    Company update(int id, String newNameCompany);

    List<Company> getAll();

    Company getById(int id);

    Company getByName(String nameCompany);

    void deleteById(int id);
}
