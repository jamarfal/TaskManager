package org.example.taskmanager.base;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jamarfal on 4/2/18.
 */

public class RetrofitClient {

    private static Retrofit retrofit = null;

    private static Retrofit getClient(String baseUrl) {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static APIService getAPIService() {

        return RetrofitClient.getClient(APIService.ENDPOINT).create(APIService.class);
    }
}
