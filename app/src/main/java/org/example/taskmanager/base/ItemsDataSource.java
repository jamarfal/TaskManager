package org.example.taskmanager.base;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;

/**
 * Created by jamarfal on 5/2/18.
 */

public class ItemsDataSource {

    private final APIService apiService;

    @Inject
    public ItemsDataSource(APIService apiService) {
        this.apiService = apiService;
    }

    public List<ApiItemDto> getItems() {
        List<ApiItemDto> bunchOfApitItems = null;
        try {
            Response<List<ApiItemDto>> response = apiService.getItems().execute();
            if (response.isSuccessful()) {
                bunchOfApitItems = response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
            bunchOfApitItems = Collections.emptyList();
        }
        return bunchOfApitItems;
    }
}
