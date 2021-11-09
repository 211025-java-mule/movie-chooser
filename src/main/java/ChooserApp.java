import client.MovieClient;
import dao.MovieDao;
import model.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.TomcatUtil;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class ChooserApp {

    static Logger logger = LoggerFactory.getLogger(ChooserApp.class.getName());

    public static void main(String[] args) {

        try {
            userInterface();
        } catch (InterruptedException e) {
            logger.error(e.toString());
        }
    }

    //application interface, displays available options and assigns actions to them
    static void userInterface() throws InterruptedException {
        System.out.println("Welcome to Movie Chooser!");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            logger.error(e.toString());
        }
        System.out.println("What do you want to do?");
        Scanner scanner = new Scanner(System.in);
        String menu = "1 - Find movie ID by title\n"
                + "2 - Add movie to your list by ID\n"
                + "3 - Show your list ordered by ratings\n"
                + "4 - Show random movie form your list\n"
                + "5 - Run the web application\n"
                + "6 - Close application";

        char response;
        do {
            System.out.println(menu);
            response = scanner.next().charAt(0);
            switch (response) {
                case '1' -> findMovieByTitle();
                case '2' -> addMovie();
                case '3' -> showList();
                case '4' -> showRandomMovie();
                case '5' -> {
                    TomcatUtil tomcatUtil = new TomcatUtil();
                    tomcatUtil.runTomcatServer();
                }
                case '6' -> {
                    System.out.println("Closing down the application, see you later.");
                    System.exit(0);
                }
                default -> System.out.println("Incorrect input. Select one of the numbers available in the menu");
            }
        } while (true);
    }

    //finds movies by the title entered on the terminal and prints them
    static void findMovieByTitle() throws InterruptedException {
        System.out.println("Enter a movie title");
        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine();
        MovieClient movieClient = new MovieClient();
        System.out.println("List of found movies: \n" + movieClient.findMoviesByTitle(title).toString());
        TimeUnit.SECONDS.sleep(2);
        System.out.println("\n Select what you want to do next from the menu.");
    }

    //adds a movie to the database based on the id entered in the terminal
    static void addMovie() throws InterruptedException {
        System.out.println("Enter the id of the movie you want to add");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.next();
        MovieClient movieClient = new MovieClient();
        Movie movie = movieClient.findMovieById(id);
        if (movie.title == null) {
            System.out.println("The ID you provided was incorrect.");
        } else {
            MovieDao movieDao = new MovieDao();
            movieDao.create(movie);
            TimeUnit.SECONDS.sleep(2);
        }
        System.out.println("\nSelect what you want to do next from the menu.");
    }

    //displays the list of movies stored in the database
    static void showList() throws InterruptedException {
        System.out.println("This is a list of movies added by you:");
        MovieDao movieDao = new MovieDao();
        List<Movie> movieList = movieDao.findAll();
        for (int i = 0; i < movieList.size(); i++) {
            System.out.println(i+1 + " - " + movieList.get(i).title + ", year: " + movieList.get(i).year + ", imDb rating: " + movieList.get(i).imDbRating);
        }
        TimeUnit.SECONDS.sleep(2);
        System.out.println("\n Select what you want to do next from the menu.");
    }

    //displays a random movie from the list stored in the database
    static void showRandomMovie() throws InterruptedException {
        System.out.println("This is a random movie chosen from your list:");
        MovieDao movieDao = new MovieDao();
        List<Movie> movieList = movieDao.findAll();
        Random random = new Random();
        int index = random.nextInt(movieList.size());
        System.out.println(movieList.get(index).title + ", year: " + movieList.get(index).year + ", imDb rating: " + movieList.get(index).imDbRating);
        TimeUnit.SECONDS.sleep(2);
        System.out.println("\n Select what you want to do next from the menu.");
    }
}
