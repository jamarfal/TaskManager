package org.example.taskmanager.base;

/**
 * Created by josealbertomartinfalcon on 3/2/18.
 */

public interface DataSourceProvider<T> {

    T getDataSource();
}
