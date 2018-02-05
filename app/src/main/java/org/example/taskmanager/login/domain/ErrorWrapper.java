package org.example.taskmanager.login.domain;

import org.example.taskmanager.base.Error;
import org.example.taskmanager.base.ErrorBundle;

/**
 * Created by josealbertomartinfalcon on 4/2/18.
 * Email: jalbertomartinfalcon@gmail.com
 */

public class ErrorWrapper implements ErrorBundle {

    private final @Error.Type int errorType;

    public ErrorWrapper(@Error.Type int errorType) {
        this.errorType = errorType;
    }

    @Override
    public int getErrorType() {
        return errorType;
    }
}
