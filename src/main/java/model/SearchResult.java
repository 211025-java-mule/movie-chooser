package model;

public class SearchResult {
    public String id;
    public String resultType;
    public String title;
    public String description;

    @Override
    public String toString() {
        return "model.SearchResult{" +
                "id='" + id + '\'' +
                ", resultType='" + resultType + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
