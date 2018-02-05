package org.example.taskmanager.login.repository.datasource;

import org.example.taskmanager.common.domain.entities.User;

import java.util.List;

/**
 * Created by josealbertomartinfalcon on 3/2/18.
 * Email: jalbertomartinfalcon@gmail.com
 */

public interface UserDataSource {

    User getUser(String username);

    void addUser(User user);

    void addUsers(List<User> bunchOfUsers);

    List<User> getAllUsers();

    User getUserWithLessWorkloadByType(int taskType);
}
