public class Movie {
    public String id;
    public String title;
    public String year;
    public String imDbRating;

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", imDbRating='" + imDbRating + '\'' +
                '}';
    }
}
