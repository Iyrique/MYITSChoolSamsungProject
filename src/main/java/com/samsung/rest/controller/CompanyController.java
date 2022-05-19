package com.samsung.rest.controller;

import com.samsung.domain.Company;
import com.samsung.rest.dto.CompanyDto;
import com.samsung.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping("/company")
    public CompanyDto createNewCompany(@RequestBody CompanyDto companyDto) {

        Company company = companyService.insert(CompanyDto.toDomainObject(companyDto));
        return CompanyDto.toDto(company);
    }

    @GetMapping("/company")
    public List<CompanyDto> getAllCompanies() {

        return companyService
                .getAll()
                .stream()
                .map(CompanyDto::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/company/{id}")
    public CompanyDto getCompanyById(@PathVariable int id) {

        return CompanyDto.toDto(companyService.getById(id));
    }

    @GetMapping("/company/name")
    public CompanyDto getCompanyByName(@RequestParam String name) {

        return CompanyDto.toDto(companyService.getByName(name));
    }

    @PostMapping("/company/{id}/name")
    public CompanyDto updateNameById(
            @PathVariable int id,
            @RequestParam String name,
            @RequestParam String country,
            @RequestParam int openDate,
            @RequestParam int staff
    ) {

        return CompanyDto.toDto(
                companyService.update(id, name, country, openDate, staff)
        );
    }

    @DeleteMapping("/company/{id}")
    public void deleteCompanyById(@PathVariable int id) {

        companyService.deleteById(id);
    }
}
