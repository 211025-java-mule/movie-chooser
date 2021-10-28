import dao.MovieDao;
import model.Movie;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class ChooserApp {
    public static void main(String[] args) throws IOException, InterruptedException {
        ChooserApp chooserApp = new ChooserApp();
        chooserApp.userInterface();
    }

    public void userInterface() throws IOException, InterruptedException {
        System.out.println("Welcome to Movie Chooser!");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("What do you want to do?");
        Scanner scanner = new Scanner(System.in);
        String menu = "1 - Find movie ID by title\n"
                + "2 - Add movie to your list by ID\n"
                + "3 - Show your list ordered by ratings\n"
                + "4 - Show random movie form your list\n"
                + "5 - Close application";

        char response;
        do {
            System.out.println(menu);
            response = scanner.next().charAt(0);
            switch (response){
                case '1':
                    findMovieByTitle();
                    break;
                case '2':
                    addMovie();
                    break;
                case '3':
                    //TODO
                    break;
                case '4':
                    //TODO
                    break;
                case '5':
                    System.out.println("Closing down the application, see you later");
                    System.exit(0);
                default:
                    System.out.println("Incorrect input. Select one of the numbers available in the menu");
            }
        } while (response != 1 || response != 2 || response != 3 || response != 4 || response != 5);
    }

    public void findMovieByTitle() throws IOException {
        System.out.println("Enter a movie title");
        Scanner scanner = new Scanner(System.in);
        String title = scanner.next();
        MovieClient movieClient = new MovieClient();
        System.out.println(movieClient.findMovie(title));
    }

    public void addMovie() throws IOException {
        System.out.println("Enter the id of the movie you want to add");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.next();
        MovieClient movieClient = new MovieClient();
        Movie movie = movieClient.findMovieById(id);
        MovieDao movieDao = new MovieDao();
        movieDao.create(movie);

    }
}
