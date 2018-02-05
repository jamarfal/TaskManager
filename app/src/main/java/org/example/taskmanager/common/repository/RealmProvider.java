package org.example.taskmanager.common.repository;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by josealbertomartinfalcon on 3/2/18.
 * Email: jalbertomartinfalcon@gmail.com
 */

public class RealmProvider implements DataSourceProvider<Realm> {

    private static final int REALM_SCHEMA_VERSION = 1;

    @Inject
    public RealmProvider() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(REALM_SCHEMA_VERSION)
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    @Override
    public Realm getDataSource() {
        return Realm.getDefaultInstance();
    }
}

