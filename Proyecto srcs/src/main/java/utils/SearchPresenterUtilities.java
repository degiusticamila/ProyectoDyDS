package utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dyds.tvseriesinfo.fulllogic.SearchResult;
import retrofit2.Response;

import java.util.*;

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
    public static String textToHtml(String text) {

        StringBuilder builder = new StringBuilder();

        builder.append("<font face=\"arial\">");

        String fixedText = text
                .replace("'", "`"); //Replace to avoid SQL errors, we will have to find a workaround..

        builder.append(fixedText);

        builder.append("</font>");

        return builder.toString();
    }
    //No esta bueno que reciba tantos parametros.
    public static String calculatePageResults(Response<String> callForPageResponse,SearchResult sr){
        //poner nombres significativos.
        Gson gson = new Gson();
        String selectedResultTitle = null;
        String text = "";

        JsonObject jobj2 = gson.fromJson(callForPageResponse.body(), JsonObject.class);
        JsonObject query2 = jobj2.get("query").getAsJsonObject();
        JsonObject pages = query2.get("pages").getAsJsonObject();

        Set<Map.Entry<String, JsonElement>> pagesSet = pages.entrySet();
        Map.Entry<String, JsonElement> first = pagesSet.iterator().next();

        JsonObject page = first.getValue().getAsJsonObject();
        JsonElement searchResultExtract2 = page.get("extract");
        if (searchResultExtract2 == null) {
            text = "No Results";
        } else {
            text = "<h1>" + sr.title + "</h1>";
            selectedResultTitle = sr.title;
            text += searchResultExtract2.getAsString().replace("\\n", "\n");
            text = textToHtml(text);
        }
        return text;
    }
}
