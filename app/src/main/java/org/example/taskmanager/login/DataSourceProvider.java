package org.example.taskmanager.login;

/**
 * Created by josealbertomartinfalcon on 3/2/18.
 */

public interface DataSourceProvider<T> {

    T getDataSource();
}
