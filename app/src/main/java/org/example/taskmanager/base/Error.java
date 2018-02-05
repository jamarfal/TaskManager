package org.example.taskmanager.base;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static org.example.taskmanager.base.Error.Type.LOGIN_ERROR;
import static org.example.taskmanager.base.Error.Type.TASK_PARAMS_INVALID;
import static org.example.taskmanager.base.Error.Type.USER_NOT_FOUND;


/**
 * Created by josealbertomartinfalcon on 4/2/18.
 * Email: jalbertomartinfalcon@gmail.com
 */

public class Error {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({LOGIN_ERROR, TASK_PARAMS_INVALID, USER_NOT_FOUND})
    public @interface Type{
        int LOGIN_ERROR = 0;
        int TASK_PARAMS_INVALID = 1;
        int USER_NOT_FOUND = 2;
    }
}
