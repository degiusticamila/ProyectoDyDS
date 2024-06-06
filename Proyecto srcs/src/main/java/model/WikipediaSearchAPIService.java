package model;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
public class WikipediaSearchAPIService {

    public static WikipediaSearchAPI createWikiSearchApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://en.wikipedia.org/w/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        return retrofit.create(WikipediaSearchAPI.class);
    }
}
