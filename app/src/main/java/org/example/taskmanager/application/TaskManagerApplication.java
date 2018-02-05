package org.example.taskmanager.application;

import android.app.Application;

import org.example.taskmanager.di.DaggerMainComponent;
import org.example.taskmanager.di.MainComponent;
import org.example.taskmanager.common.repository.LocalDbPopulator;
import org.example.taskmanager.common.repository.RealmProvider;
import org.example.taskmanager.login.repository.datasource.UserRealmDataSource;
import org.example.taskmanager.login.repository.UserRepository;

import io.realm.Realm;

/**
 * Created by josealbertomartinfalcon on 3/2/18.
 * Email: jalbertomartinfalcon@gmail.com
 */

public class TaskManagerApplication extends Application {

    private MainComponent mainComponent;


    @Override
    public void onCreate() {
        super.onCreate();

        mainComponent = DaggerMainComponent.create();

        Realm.init(this);

        populateDb();
    }

    private void populateDb() {
        UserRepository userRepository = new UserRepository(
                new UserRealmDataSource(new RealmProvider()));
        LocalDbPopulator localDbPopulator = new LocalDbPopulator(userRepository);
        if (!localDbPopulator.hasBeenDbPopulatedWithDataPreviously()) {
            localDbPopulator.fillDbWithFakeUsers();
        }
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }

    public void setComponent(MainComponent mainComponent) {
        this.mainComponent = mainComponent;
    }
}
