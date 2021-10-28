package dao;

import model.Movie;
import utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MovieDao {
    private static final String CREATE_MOVIE_QUERY = "INSERT INTO movie(imdb_id,title,year,imDbRating) VALUES (?,?,?,?);";
    private static final String FIND_ALL_MOVIES_QUERY = "SELECT * FROM movie ORDER BY imDbRating DESC;";

    public Movie create(Movie movie){
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_MOVIE_QUERY);
            preparedStatement.setString(1, movie.id);
            preparedStatement.setString(2, movie.title);
            preparedStatement.setString(3, movie.year);
            preparedStatement.setString(4, movie.imDbRating);
            int result = preparedStatement.executeUpdate();
            if (result == 1) {
                System.out.println("The given movie has been added to the list. Select what you want to do next from the menu.");;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



}
