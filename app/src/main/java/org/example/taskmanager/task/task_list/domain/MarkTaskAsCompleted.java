package org.example.taskmanager.task.task_list.domain;

import android.os.Handler;
import android.os.Looper;

import org.example.taskmanager.common.UseCase;
import org.example.taskmanager.entities.Task;
import org.example.taskmanager.task.TaskDataSource;

import javax.inject.Inject;

/**
 * Created by jamarfal on 4/2/18.
 */

public class MarkTaskAsCompleted extends UseCase<Task, Task> {


    private final TaskDataSource taskDataSource;
    private Task taskToComplete;
    UseCaseCallback<Task> useCaseCallback;

    @Inject
    public MarkTaskAsCompleted(TaskDataSource taskDataSource) {
        this.taskDataSource = taskDataSource;
    }

    @Override
    public void execute(Task taskToComplete, UseCaseCallback<Task> useCaseCallback) {
        this.taskToComplete = taskToComplete;
        this.useCaseCallback = useCaseCallback;
        new Thread(this).start();
    }

    @Override
    public void run() {
        final Task completedTask = taskDataSource.markTaskAsCompleted(taskToComplete);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                useCaseCallback.onSuccess(completedTask);
            }
        });

    }
}
