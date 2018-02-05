package org.example.taskmanager.login.repository;

import org.example.taskmanager.common.domain.entities.User;
import org.example.taskmanager.login.repository.datasource.UserDataSource;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by josealbertomartinfalcon on 3/2/18.
 * Email: jalbertomartinfalcon@gmail.com
 */

public class UserRepository {

    private final UserDataSource userDataSource;

    @Inject
    public UserRepository(UserDataSource userDataSource) {
        this.userDataSource = userDataSource;
    }


    public User getUser(String userName) {
        return userDataSource.getUser(userName);
    }


    public void addUser(User user) {
        userDataSource.addUser(user);
    }


    public void addUsers(List<User> bunchOfUsers) {
        userDataSource.addUsers(bunchOfUsers);
    }


    public List<User> getAllUsers() {
        return userDataSource.getAllUsers();
    }

    public User getUserWithLessWorkloadByType(int taskType){
        return userDataSource.getUserWithLessWorkloadByType(taskType);
    }


}
