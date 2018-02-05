package org.example.taskmanager.base;

/**
 * Created by josealbertomartinfalcon on 4/2/18.
 * Email: jalbertomartinfalcon@gmail.com
 */

public class ErrorMessageFactory {

    public static String createErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = null;
        @Error.Type int errorType = errorBundle.getErrorType();

        switch (errorType) {
            case Error.Type.LOGIN_ERROR:
                errorMessage = "";
                break;
            case Error.Type.TASK_PARAMS_INVALID:
                errorMessage = "Alguno de los parámetros son inválidos";
                break;
        }
        return errorMessage;
    }
}
