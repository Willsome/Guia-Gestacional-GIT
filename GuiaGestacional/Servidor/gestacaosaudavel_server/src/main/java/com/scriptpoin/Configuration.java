package com.scriptpoin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@EnableAutoConfiguration
@SpringBootApplication
public class Configuration extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Configuration.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Configuration.class, args);
    }

    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

//        CONFIGURAÇÕES PROD
//        dataSource.setUrl("jdbc:mysql://gestacaosaudavel.com.br:3306/gestacao_saudavel_db");
//        dataSource.setUsername("gestacao_adm");
//        dataSource.setPassword("wrrB!A5BN_NK");

//        CONFIGURAÇÕES DEV
        dataSource.setUrl("jdbc:mysql://localhost:3306/gestacao_saudavel");
        dataSource.setUsername("root");
        dataSource.setPassword("");

        return dataSource;
    }

}
