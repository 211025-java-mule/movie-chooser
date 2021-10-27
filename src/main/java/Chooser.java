import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;



public class Chooser {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        //URL url = new URL("https://imdb-api.com/en/API/Title/k_21xe62oi/tt0110413");
        URL url = new URL("https://imdb-api.com/en/API/SearchMovie/k_21xe62oi/inception 2010");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream inputStream = connection.getInputStream();
        String body = new String(inputStream.readAllBytes());

        //Movie movie = objectMapper.readValue(body, Movie.class);
        Search search = objectMapper.readValue(body, Search.class);
        System.out.println(search);
    }
}
