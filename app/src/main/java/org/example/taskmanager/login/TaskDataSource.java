package org.example.taskmanager.login;

import org.example.taskmanager.entities.Task;
import org.example.taskmanager.entities.User;

import java.util.List;

/**
 * Created by josealbertomartinfalcon on 4/2/18.
 * Email: jalbertomartinfalcon@gmail.com
 */

public interface TaskDataSource {

    Task getTask(String id);

    void assignTaskToUser(Task task, User user);

    Task markTaskAsCompleted(Task task);
}
