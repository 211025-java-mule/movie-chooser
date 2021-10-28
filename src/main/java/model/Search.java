package model;

import java.util.List;

public class Search {
    public String searchType;
    public String expression;
    public List<SearchResult> results;

    @Override
    public String toString() {
        return "model.Search{" +
                "searchType='" + searchType + '\'' +
                ", expression='" + expression + '\'' +
                ", results=" + results +
                '}';
    }
}
