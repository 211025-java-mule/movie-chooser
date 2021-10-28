package model;

import lombok.Data;

import java.util.List;

@Data
public class Search {
    public String searchType;
    public String expression;
    public List<SearchResult> results;

}
