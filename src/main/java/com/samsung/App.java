package com.samsung;


import com.samsung.domain.Company;
import com.samsung.repository.CompanyRepository;
import com.samsung.service.LibDemo;
import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.SQLException;
import java.util.List;

// url h2 консоли: http://localhost:8081/h2-console
// url базы: jdbc:h2:mem:testdb

@SpringBootApplication
public class App {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
//
//        List<Company> companies = context.getBean(CompanyRepository.class).findAll();
//
//        companies.stream().forEach(System.out::println);
//
        LibDemo libraryDemo = context.getBean(LibDemo.class);
        libraryDemo.libDemo();

        context.close();
//        try {
//            //Консоль для визуализации бд в браузере
//            Console.main(args);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }
}
