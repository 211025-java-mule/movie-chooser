package utils;

import client.MovieClient;
import dao.MovieDao;
import model.Movie;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

public class TomcatUtil {
    Logger logger = LoggerFactory.getLogger(TomcatUtil.class.getName());

    public void runTomcatServer (){
        MovieClient movieClient = new client.MovieClient();
        MovieDao movieDao = new MovieDao();
        String docBase = "src/main/webapp/";
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8082);

        try {
            tomcat.addWebapp("", new File(docBase).getAbsolutePath());
        } catch (ServletException e) {
            logger.error(e.toString());
        }

        //Find movies by title servlet
        tomcat.addServlet("", "findMovieServlet", new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                String title = request.getParameter("title");
                String titles = movieClient.findMoviesByTitle(title).toString();
                request.setAttribute("titles", titles);
                getServletContext().getRequestDispatcher("/foundMovies.jsp").forward(request,response);
            }
        }).addMapping("/findMovie");

        //Add movie by ID servlet
        tomcat.addServlet("", "addMovieServlet", new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                String id = request.getParameter("id");
                Movie movie = movieClient.findMovieById(id);
                movieDao.create(movie);
                getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
            }
        }).addMapping("/addMovie");

        //Show list of movies form DB
        tomcat.addServlet("", "showList", new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                List<Movie> movieList = movieDao.findAll();
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < movieList.size(); i++) {
                    stringBuilder.append(i+1 + " - " + movieList.get(i).title + ", year: " + movieList.get(i).year + ", imDb rating: " + movieList.get(i).imDbRating);
                }
                request.setAttribute("movieList", stringBuilder);
                getServletContext().getRequestDispatcher("/movieList.jsp").forward(request,response);
            }
        }).addMapping("/showList");

        //Show random movie form list
        tomcat.addServlet("", "showRandomMovie", new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                List<Movie> movieList = movieDao.findAll();
                Random random = new Random();
                int index = random.nextInt(movieList.size());
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(movieList.get(index).title + ", year: " + movieList.get(index).year + ", imDb rating: " + movieList.get(index).imDbRating);
                request.setAttribute("randomMovie", stringBuilder);
                getServletContext().getRequestDispatcher("/randomMovie.jsp").forward(request,response);
            }
        }).addMapping("/showRandomMovie");

        try {
            tomcat.start();
        } catch (LifecycleException e) {
            logger.error(e.toString());
        }
        tomcat.getServer().await();
    }
}
