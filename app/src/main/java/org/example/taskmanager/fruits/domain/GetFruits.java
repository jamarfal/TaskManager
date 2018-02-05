package org.example.taskmanager.fruits.domain;

import android.os.Handler;
import android.os.Looper;

import org.example.taskmanager.common.repository.dto.ApiItemDto;
import org.example.taskmanager.common.repository.ItemsDataSource;
import org.example.taskmanager.common.domain.UseCase;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by jamarfal on 5/2/18.
 */

public class GetFruits extends UseCase<Void, List<ApiItemDto>> {

    private final ItemsDataSource itemsDataSource;
    private UseCaseCallback<List<ApiItemDto>> useCaseCallback;

    @Inject
    public GetFruits(ItemsDataSource itemsDataSource) {
        this.itemsDataSource = itemsDataSource;
    }


    @Override
    public void execute(Void aVoid, UseCaseCallback<List<ApiItemDto>> useCaseCallback) {
        this.useCaseCallback = useCaseCallback;
        new Thread(this).start();
    }

    @Override
    public void run() {
        final List<ApiItemDto> bunchOfApiItems = itemsDataSource.getItems();


        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                useCaseCallback.onSuccess(bunchOfApiItems);
            }
        });

    }
}
