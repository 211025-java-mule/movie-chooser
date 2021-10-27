import java.util.List;

public class Search {
    public String searchType;
    public String expression;
    public List<SearchResult> results;

    @Override
    public String toString() {
        return "Search{" +
                "searchType='" + searchType + '\'' +
                ", expression='" + expression + '\'' +
                ", results=" + results +
                '}';
    }
}
