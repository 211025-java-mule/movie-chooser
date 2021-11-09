package client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Movie;
import model.Search;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MovieClient {
    private static final String API_KEY = "k_21xe62oi";
    Logger logger = LoggerFactory.getLogger(MovieClient.class.getName());

    public Search findMoviesByTitle(String title) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        URL url = null;
        try {
            url = new URL("https://imdb-api.com/en/API/SearchMovie/" + API_KEY + "/" + title);
        } catch (MalformedURLException e) {
            logger.error(e.toString());
        }

        HttpURLConnection connection = null;
        Search search = null;

        try {
            connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            String body = new String(inputStream.readAllBytes());
            search = objectMapper.readValue(body, Search.class);
        } catch (IOException e) {
            logger.error(e.toString());
        }

        return search;
    }

    public Movie findMovieById(String id) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        URL url = null;
        try {
            url = new URL("https://imdb-api.com/en/API/Title/" + API_KEY + "/" + id);
        } catch (MalformedURLException e) {
            logger.error(e.toString());
        }

        HttpURLConnection connection = null;
        Movie movie = new Movie();
        try {
            connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            String body = new String(inputStream.readAllBytes());
            movie = objectMapper.readValue(body, Movie.class);
        } catch (IOException e) {
            logger.error(e.toString());
        }
        return movie;
    }
}
