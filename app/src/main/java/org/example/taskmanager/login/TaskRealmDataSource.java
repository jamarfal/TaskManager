package org.example.taskmanager.login;

import android.support.annotation.NonNull;

import org.example.taskmanager.entities.Task;
import org.example.taskmanager.entities.User;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Created by josealbertomartinfalcon on 4/2/18.
 * Email: jalbertomartinfalcon@gmail.com
 */

public class TaskRealmDataSource implements TaskDataSource {

    private final DataSourceProvider<Realm> realmProvider;

    @Inject
    public TaskRealmDataSource(DataSourceProvider<Realm> realmProvider) {
        this.realmProvider = realmProvider;
    }

    @Override
    public Task getTask(String id) {
        Realm realm = getRealm();
        Task unlinkedFromRealmTask = Task.EMPTY_TASK;
        Task storedTask = realm.where(Task.class).equalTo("id", id).findFirst();
        if (storedTask != null) {
            unlinkedFromRealmTask = realm.copyFromRealm(storedTask);
        }
        realm.close();
        return unlinkedFromRealmTask;
    }

    @Override
    public void assignTaskToUser(final Task task, final User user) {
        Realm realm = getRealm();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                user.getAssignedTasks().add(task);
                realm.copyToRealmOrUpdate(user);
            }
        });
        realm.close();
    }

    @Override
    public Task markTaskAsCompleted(final Task task) {
        Realm realm = getRealm();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                task.setCompleted(true);
                realm.copyToRealmOrUpdate(task);
            }
        });
        realm.close();
        return task;
    }

    private Realm getRealm() {
        return realmProvider.getDataSource();
    }
}
