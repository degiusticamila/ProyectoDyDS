package utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dyds.tvseriesinfo.fulllogic.SearchResult;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SearchPresenterUtilities {
    public static JsonArray calculateJSonObjects(Response<String> lastSearchResponse){
        Gson gson = new Gson();
        JsonObject jobj = gson.fromJson(lastSearchResponse.body(), JsonObject.class);
        JsonObject query = jobj.get("query").getAsJsonObject();
        Iterator<JsonElement> resultIterator = query.get("search").getAsJsonArray().iterator();
        JsonArray jsonResults = query.get("search").getAsJsonArray();
        return jsonResults;
    }
    public static Iterable<SearchResult> calculateSearchResults(JsonArray jsonResults){
        LinkedList<SearchResult> searchResultsList = new LinkedList<SearchResult>();
        for (JsonElement je : jsonResults) {
            JsonObject searchResult = je.getAsJsonObject();
            String searchResultTitle = searchResult.get("title").getAsString();
            String searchResultPageId = searchResult.get("pageid").getAsString();
            String searchResultSnippet = searchResult.get("snippet").getAsString();

            SearchResult sr = new SearchResult(searchResultTitle, searchResultPageId, searchResultSnippet);
            searchResultsList.add(sr);
        }
        return searchResultsList;
    }
}
