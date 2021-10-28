package model;

import lombok.Data;

@Data
public class Movie {
    public String id;
    public String title;
    public String year;
    public String imDbRating;

//    @Override
//    public String toString() {
//        return "model.Movie{" +
//                "id='" + id + '\'' +
//                ", title='" + title + '\'' +
//                ", year='" + year + '\'' +
//                ", imDbRating='" + imDbRating + '\'' +
//                '}';
//    }
}
