package org.example.taskmanager.task.task_list.domain;

import org.example.taskmanager.base.Error;
import org.example.taskmanager.base.UseCase;
import org.example.taskmanager.entities.Task;
import org.example.taskmanager.entities.User;
import org.example.taskmanager.login.repository.UserRepository;
import org.example.taskmanager.login.domain.ErrorWrapper;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by jamarfal on 4/2/18.
 */

public class GetTasks extends UseCase<String, List<Task>> {

    private final UserRepository userRepository;
    private String userName;
    private UseCaseCallback<List<Task>> useCaseCallback;

    @Inject
    public GetTasks(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void execute(String userName, UseCaseCallback<List<Task>> useCaseCallback) {
        this.userName = userName;
        this.useCaseCallback = useCaseCallback;
        new Thread(this).start();
    }

    @Override
    public void run() {
        User searchedUser = userRepository.getUser(userName);
        if (searchedUser != User.EMPTY_USER) {
            List<Task> assignedTask = searchedUser.getAssignedTasks();
            useCaseCallback.onSuccess(assignedTask);
        } else {
            useCaseCallback.onError(new ErrorWrapper(Error.Type.USER_NOT_FOUND));
        }
    }
}
