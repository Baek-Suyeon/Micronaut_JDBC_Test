package com.ipageon;

import io.micronaut.context.annotation.Requires;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Singleton
@Requires(beans = DataSource.class)
public class GenreRepository {

    @Inject
    private DataSource dataSource;

    public void saveGenre(String name) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO genre (id, name) VALUES (genre_id_seq.nextval, ?)")) {
            statement.setString(1, name);
            statement.executeUpdate();
        }
    }

    public ResultSet listGenres() throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM genre order by id");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                System.out.println("Genre: " + resultSet.getString("id") + ", " + resultSet.getString("name"));
            }

            return resultSet;
        }
    }

    public void deleteGenre(int id) throws SQLException{
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("delete from genre where id = ? ")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}