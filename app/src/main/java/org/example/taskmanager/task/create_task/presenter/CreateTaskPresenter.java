package org.example.taskmanager.task.create_task.presenter;

import org.example.taskmanager.common.ErrorBundle;
import org.example.taskmanager.common.ErrorMessageFactory;
import org.example.taskmanager.common.NumberConverter;
import org.example.taskmanager.common.Presenter;
import org.example.taskmanager.common.UseCase;
import org.example.taskmanager.entities.TaskType;
import org.example.taskmanager.login.domain.TaskParams;
import org.example.taskmanager.task.create_task.domain.CreateTask;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by josealbertomartinfalcon on 4/2/18.
 * Email: jalbertomartinfalcon@gmail.com
 */

public class CreateTaskPresenter extends Presenter<CreateTaskPresenter.CreateTaskView> implements UseCase.UseCaseCallback<Void> {

    private final CreateTask createTask;

    @Inject
    public CreateTaskPresenter(CreateTask createTask) {
        this.createTask = createTask;
    }


    @Override
    public void initialize() {
        super.initialize();

        if (getView() != null) {
            List<TaskType> bunchOfTaskTypes = TaskType.getAllTaskTypes();
            getView().fillTaskTypeSelector(bunchOfTaskTypes);
        }
    }

    public void createTask(String description, String duration, int taskType) {
        if (getView() != null) {
            getView().showLoading();

            TaskParams taskParams = new TaskParams(
                    NumberConverter.tryParseInt(duration), description, taskType);
            createTask.execute(taskParams, this);
        }

    }

    //region CREATE TASK LISTENER
    @Override
    public void onSuccess(Void aVoid) {
        if (getView() != null) {
            getView().hideLoading();
            getView().cleanForm();
            getView().showSuccessMessage();
        }
    }

    @Override
    public void onError(ErrorBundle errorBundle) {
        if (getView() != null) {
            getView().hideLoading();
            getView().showError(ErrorMessageFactory.createErrorMessage(errorBundle));
        }
    }

    public void doLogout() {
        if (getView() != null) {
            // Here, everything necessary for this user should be cleaned up
            // We only close the view and back to login;
            getView().close();
        }
    }
    //endregion


    public interface CreateTaskView extends Presenter.View {

        void fillTaskTypeSelector(List<TaskType> bunchOfTaskTypes);

        void showError(String errorMessage);

        void showSuccessMessage();

        void cleanForm();

        void close();
    }
}
