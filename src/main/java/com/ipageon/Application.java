package com.ipageon;

import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.Micronaut;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Application {

    public static void main(String[] args) throws SQLException {
        ApplicationContext applicationContext = Micronaut.run(Application.class, args);
        GenreController genreController = applicationContext.getBean(GenreController.class);
//        genreController.addGenre("1010");
        int id = 3;
        genreController.deleteGenre(id);

        genreController.listGenres();
    }
}