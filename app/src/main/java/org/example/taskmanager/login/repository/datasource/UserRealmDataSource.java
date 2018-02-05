package org.example.taskmanager.login.repository.datasource;

import android.support.annotation.NonNull;

import org.example.taskmanager.base.DataSourceProvider;
import org.example.taskmanager.entities.User;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Created by josealbertomartinfalcon on 3/2/18.
 * Email: jalbertomartinfalcon@gmail.com
 */

public class UserRealmDataSource implements UserDataSource {

    private final DataSourceProvider<Realm> realmProvider;

    @Inject
    public UserRealmDataSource(DataSourceProvider<Realm> realmProvider) {
        this.realmProvider = realmProvider;
    }

    @Override
    public User getUser(String username) {
        Realm realm = getRealm();
        User unlinkedFromRealmUSer = User.EMPTY_USER;
        User storedUser = realm.where(User.class).equalTo("userName", username).findFirst();
        if (storedUser != null) {
            unlinkedFromRealmUSer = realm.copyFromRealm(storedUser);
        }
        realm.close();
        return unlinkedFromRealmUSer;
    }

    @Override
    public void addUser(final User user) {
        Realm realm = getRealm();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                realm.copyToRealmOrUpdate(user);
            }
        });
        realm.close();
    }

    @Override
    public void addUsers(final List<User> bunchOfUsers) {
        Realm realm = getRealm();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(bunchOfUsers);
            }
        });
        realm.close();
    }

    @Override
    public List<User> getAllUsers() {
        Realm realm = getRealm();
        List<User> linkedToRealmBunchOfUsers = realm.where(User.class).findAll();
        List<User> bunchOfUsers = realm.copyFromRealm(linkedToRealmBunchOfUsers);
        realm.close();
        return bunchOfUsers;
    }

    @Override
    public User getUserWithLessWorkloadByType(int taskType) {
        Realm realm = getRealm();

        List<User> users = realm.where(User.class)
                .equalTo("abilities.value", taskType)
                .findAll();

        int totalDuration = 0;
        int comparedDuration = -1;
        User availableUser = null;
        for (User user : users) {

            if (user.getAssignedTasks().isEmpty()) {
                return user;
            } else {
                totalDuration = user.getAssignedTasks().sum("durationInMinutes").intValue();
                if (comparedDuration == -1) {
                    comparedDuration = totalDuration;
                    availableUser = user;
                }

                if (totalDuration < comparedDuration) {
                    availableUser = user;
                }
            }
        }
        return availableUser;
    }

    private Realm getRealm() {
        return realmProvider.getDataSource();
    }
}
