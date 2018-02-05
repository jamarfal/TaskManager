
package org.example.taskmanager.di;

import org.example.taskmanager.common.repository.APIService;
import org.example.taskmanager.common.repository.RetrofitClient;
import org.example.taskmanager.common.repository.RealmProvider;
import org.example.taskmanager.task.create_task.repository.TaskDataSource;
import org.example.taskmanager.task.create_task.repository.TaskRealmDataSource;
import org.example.taskmanager.login.repository.datasource.UserDataSource;
import org.example.taskmanager.login.repository.datasource.UserRealmDataSource;
import org.example.taskmanager.login.repository.UserRepository;

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
