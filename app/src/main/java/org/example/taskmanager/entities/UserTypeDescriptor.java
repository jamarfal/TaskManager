package org.example.taskmanager.entities;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static org.example.taskmanager.entities.UserTypeDescriptor.UserTypeDef.ADMIN;
import static org.example.taskmanager.entities.UserTypeDescriptor.UserTypeDef.TECHNICIAN;

/**
 * Created by josealbertomartinfalcon on 3/2/18.
 * Email: jalbertomartinfalcon@gmail.com
 */

public class UserTypeDescriptor {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ADMIN, TECHNICIAN})
    public @interface UserTypeDef{
        int ADMIN = 0;
        int TECHNICIAN = 1;
    }


}

