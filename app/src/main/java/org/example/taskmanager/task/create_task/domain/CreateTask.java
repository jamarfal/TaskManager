package org.example.taskmanager.task.create_task.domain;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import org.example.taskmanager.common.error_control.Error;
import org.example.taskmanager.common.domain.UseCase;
import org.example.taskmanager.common.domain.entities.Task;
import org.example.taskmanager.common.domain.entities.User;
import org.example.taskmanager.task.create_task.repository.TaskDataSource;
import org.example.taskmanager.login.repository.datasource.UserDataSource;
import org.example.taskmanager.common.error_control.ErrorWrapper;

import java.util.UUID;

import javax.inject.Inject;

/**
 * Created by josealbertomartinfalcon on 4/2/18.
 * Email: jalbertomartinfalcon@gmail.com
 */

public class CreateTask extends UseCase<TaskParams, Void> {

    private final TaskDataSource taskDataSource;
    private final UserDataSource userDataSource;
    private TaskParams taskParams;
    private UseCaseCallback<Void> useCaseCallback;

    @Inject
    public CreateTask(TaskDataSource taskDataSource, UserDataSource userDataSource) {
        this.taskDataSource = taskDataSource;
        this.userDataSource = userDataSource;
    }

    @Override
    public void execute(TaskParams taskParams, UseCaseCallback<Void> useCaseCallback) {
        this.taskParams = taskParams;
        this.useCaseCallback = useCaseCallback;
        new Thread(this).start();
    }

    @Override
    public void run() {
        if (taskParams.paramsAreValid()) {
            Task newTask = prepareNewTask();

            User userAvailable = userDataSource.getUserWithLessWorkloadByType(taskParams.getTaskType());
            taskDataSource.assignTaskToUser(newTask, userAvailable);

            notifySuccessOperation();

        } else {
            notifyErrorOperation();
        }
    }

    @NonNull
    private Task prepareNewTask() {
        return new Task.Builder()
                .id(UUID.randomUUID().toString())
                .description(taskParams.getDescription())
                .durationInMinutes(taskParams.getDurationInMinutes())
                .taskType(taskParams.getTaskType())
                .completed(false)
                .build();
    }

    private void notifySuccessOperation() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                useCaseCallback.onSuccess(null);
            }
        });
    }

    private void notifyErrorOperation() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                useCaseCallback.onError(new ErrorWrapper(Error.Type.TASK_PARAMS_INVALID));
            }
        });

    }
}
