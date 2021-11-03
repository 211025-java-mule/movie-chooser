package model;

import lombok.Data;

import java.util.List;

@Data
public class Search {
    public String searchType;
    public String expression;
    public List<SearchResult> results;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (SearchResult searchResult : results){
            stringBuilder.append("- ").append(searchResult.title).append(", ").append(searchResult.description).append(", ID: ").append(searchResult.id).append("\n");
        }
        return stringBuilder.toString();
    }
}
