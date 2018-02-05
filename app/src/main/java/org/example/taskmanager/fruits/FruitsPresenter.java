package org.example.taskmanager.fruits;

import org.example.taskmanager.common.ApiItemDto;
import org.example.taskmanager.common.ErrorBundle;
import org.example.taskmanager.common.Presenter;
import org.example.taskmanager.common.UseCase;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by jamarfal on 5/2/18.
 */

public class FruitsPresenter extends Presenter<FruitsPresenter.FruitsView> implements UseCase.UseCaseCallback<List<ApiItemDto>> {

    private final GetFruits getFruits;

    @Inject
    public FruitsPresenter(GetFruits getFruits) {
        this.getFruits = getFruits;
    }

    @Override
    public void initialize() {
        super.initialize();
        if (getView() != null){
            getView().showLoading();
            getFruits.execute(null, this);
        }

    }

    @Override
    public void onSuccess(List<ApiItemDto> apiItemDtos) {
        if (getView() != null) {
            getView().hideLoading();
            getView().loadFruits(apiItemDtos);
        }
    }

    @Override
    public void onError(ErrorBundle errorBundle) {
        if (getView() != null) {
            getView().hideLoading();
        }
    }

    public interface FruitsView extends Presenter.View {
        void loadFruits(List<ApiItemDto> bunchOfApiItems);
    }

}
