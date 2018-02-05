
package org.example.taskmanager.di;

import org.example.taskmanager.base.APIService;
import org.example.taskmanager.base.RetrofitClient;
import org.example.taskmanager.login.RealmProvider;
import org.example.taskmanager.login.TaskDataSource;
import org.example.taskmanager.login.TaskRealmDataSource;
import org.example.taskmanager.login.UserDataSource;
import org.example.taskmanager.login.UserRealmDataSource;
import org.example.taskmanager.login.UserRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    @Singleton
    public UserRepository provideUserRepository(UserDataSource userDataSource) {
        return new UserRepository(userDataSource);
    }

    @Provides
    public UserDataSource provideUSerDataSource() {
        return new UserRealmDataSource(new RealmProvider());
    }

    @Provides
    public TaskDataSource provideTaskDataSource() {
        return new TaskRealmDataSource(new RealmProvider());
    }

    @Provides
    public APIService provideApiService(){
        return RetrofitClient.getAPIService();
    }


}
