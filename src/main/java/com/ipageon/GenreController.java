package com.ipageon;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Controller("/genres")
public class GenreController {

    @Inject
    private GenreRepository genreRepository;

    @Post("/add/{name}")
    public String addGenre(String name) {
        try {
            genreRepository.saveGenre(name);
            return "Genre added successfully";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error adding genre: " + e.getMessage();
        }
    }

    @Get("/")
    public String listGenres() {
        try {
             genreRepository.listGenres();
            return "Genres listed successfully";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error listing genres: " + e.getMessage();
        }
    }

    public void deleteGenre(int id) {
        try {
            genreRepository.deleteGenre(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}