package com.daniil.comicshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class ComicshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComicshopApplication.class, args);
    }

}
