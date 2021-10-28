import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Movie;
import model.Search;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class MovieClient {
    private static final String API_KEY = "k_21xe62oi";

    public String findMovie (String title) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        URL url = new URL("https://imdb-api.com/en/API/SearchMovie/" + API_KEY + "/" + title);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream inputStream = connection.getInputStream();
        String body = new String(inputStream.readAllBytes());
        Search search = objectMapper.readValue(body, Search.class);
        return search.toString();
    }

    public Movie findMovieById(String id) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        URL url = new URL("https://imdb-api.com/en/API/Title/" + API_KEY + "/" + id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream inputStream = connection.getInputStream();
        String body = new String(inputStream.readAllBytes());
        Movie movie = objectMapper.readValue(body, Movie.class);
        return movie;
    }
}
