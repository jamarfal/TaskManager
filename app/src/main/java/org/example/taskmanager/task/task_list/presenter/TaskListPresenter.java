package org.example.taskmanager.task.task_list.presenter;

import org.example.taskmanager.base.ErrorBundle;
import org.example.taskmanager.base.Presenter;
import org.example.taskmanager.base.UseCase;
import org.example.taskmanager.entities.Task;
import org.example.taskmanager.task.task_list.domain.GetTasks;
import org.example.taskmanager.task.task_list.domain.MarkTaskAsCompleted;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by jamarfal on 4/2/18.
 */

public class TaskListPresenter extends Presenter<TaskListPresenter.TaskListView> {


    private final GetTasks getTasks;
    private final MarkTaskAsCompleted markTaskAsCompleted;

    @Inject
    public TaskListPresenter(GetTasks getTasks, MarkTaskAsCompleted markTaskAsCompleted) {
        this.getTasks = getTasks;
        this.markTaskAsCompleted = markTaskAsCompleted;
    }

    public void initialize(String userName) {
        super.initialize();
        getView().showLoading();
        getTasks.execute(userName, new UseCase.UseCaseCallback<List<Task>>() {
            @Override
            public void onSuccess(List<Task> tasks) {
                if (getView() != null) {
                    getView().hideLoading();
                    getView().loadTask(tasks);
                }
            }

            @Override
            public void onError(ErrorBundle errorBundle) {
                getView().hideLoading();
            }
        });
    }

    public void markTaskAsCompleted(Task task) {
        markTaskAsCompleted.execute(task, new UseCase.UseCaseCallback<Task>() {
            @Override
            public void onSuccess(Task task) {
                if (getView() != null) {
                    getView().updateTask(task);
                }
            }

            @Override
            public void onError(ErrorBundle errorBundle) {

            }
        });
    }

    //endregion

    public interface TaskListView extends Presenter.View {
        void loadTask(List<Task> bunchOfTasks);

        void updateTask(Task task);
    }
}
