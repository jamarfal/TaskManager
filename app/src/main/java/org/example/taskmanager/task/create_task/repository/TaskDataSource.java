package org.example.taskmanager.task.create_task.repository;

import org.example.taskmanager.common.domain.entities.Task;
import org.example.taskmanager.common.domain.entities.User;

/**
 * Created by josealbertomartinfalcon on 4/2/18.
 * Email: jalbertomartinfalcon@gmail.com
 */

public interface TaskDataSource {

    Task getTask(String id);

    void assignTaskToUser(Task task, User user);

    Task markTaskAsCompleted(Task task);
}
