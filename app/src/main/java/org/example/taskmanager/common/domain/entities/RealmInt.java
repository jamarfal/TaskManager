package org.example.taskmanager.common.domain.entities;

import io.realm.RealmObject;

/**
 * Created by josealbertomartinfalcon on 4/2/18.
 * Email: jalbertomartinfalcon@gmail.com
 */

public class RealmInt extends RealmObject {

    private int value;

    public RealmInt() {
    }

    public RealmInt(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
