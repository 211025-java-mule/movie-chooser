import client.MovieClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MovieClientTest {
    ObjectMapper objectMapper;
    MovieClient movieClient;

    @Before
    public void setup() {
        objectMapper = new ObjectMapper();
        movieClient = new MovieClient();
    }

    @Test
    public void testFindMovieByTitle() {
        assertEquals("tt0120737", movieClient.findMoviesByTitle("The Lord of the Rings: The Fellowship of the Ring").getResults().get(0).getId());
    }

    @Test
    public void testFindMovieById() {
        assertEquals("The Matrix", movieClient.findMovieById("tt0133093").getTitle());
    }
}