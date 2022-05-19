package com.samsung.rest.dto;

import com.samsung.domain.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {

    private int id;

    private String name;

    private String country;

    private int openDate;

    private int staff;

    public static Company toDomainObject(CompanyDto companyDto) {

        return new Company(companyDto.getId(), companyDto.getName(), companyDto.getCountry(), companyDto.getOpenDate(), companyDto.getStaff());
    }

    public static CompanyDto toDto(Company companyDto) {

        return new CompanyDto(companyDto.getId(), companyDto.getName(), companyDto.getCountry(), companyDto.getOpenDate(), companyDto.getStaff());
    }
}
