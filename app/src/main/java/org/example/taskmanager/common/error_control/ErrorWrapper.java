package org.example.taskmanager.common.error_control;

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
