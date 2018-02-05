package org.example.taskmanager.base;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by jamarfal on 4/2/18.
 */

public interface APIService {

    String ENDPOINT = "https://data.ct.gov";

    @GET("resource/hma6-9xbg.json?category=Fruit&item=Peaches")
    Call<List<ApiItemDto>> getItems();
}
