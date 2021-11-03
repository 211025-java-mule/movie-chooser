package dao;

import model.Movie;
import utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDao {
    private static final String CREATE_MOVIE_QUERY = "INSERT INTO movie(id,title,year,imDbRating) VALUES (?,?,?,?);";
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
                System.out.println("The given movie has been added to the list.");;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Movie> findAll(){
        List<Movie> movieList = new ArrayList<>();
            try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_MOVIES_QUERY);
            ResultSet resultSet = preparedStatement .executeQuery();
            while (resultSet.next()) {
                Movie movieToAdd = new Movie();
                movieToAdd.setId(resultSet.getString("id"));
                movieToAdd.setTitle(resultSet.getString("title"));
                movieToAdd.setYear(resultSet.getString("year"));
                movieToAdd.setImDbRating(resultSet.getString("imDbRating"));
                movieList.add(movieToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return movieList;
    }

}
