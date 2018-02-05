package org.example.taskmanager.login.presenter;

import org.example.taskmanager.common.error_control.ErrorBundle;
import org.example.taskmanager.common.error_control.ErrorMessageFactory;
import org.example.taskmanager.common.view.Presenter;
import org.example.taskmanager.common.domain.UseCase;
import org.example.taskmanager.common.domain.entities.User;
import org.example.taskmanager.common.domain.entities.UserTypeDescriptor;
import org.example.taskmanager.login.domain.DoLogin;
import org.example.taskmanager.login.domain.LoginParams;

import javax.inject.Inject;

/**
 * Created by josealbertomartinfalcon on 4/2/18.
 * Email: jalbertomartinfalcon@gmail.com
 */

public class LoginPresenter extends Presenter<LoginPresenter.LoginView>
        implements UseCase.UseCaseCallback<User> {

    private final DoLogin doLogin;

    @Inject
    public LoginPresenter(DoLogin doLogin) {
        this.doLogin = doLogin;
    }


    public void doLogin(String userName, String password) {
        if (getView() != null) {
            getView().showLoading();

            LoginParams loginParams = new LoginParams(userName, password);
            doLogin.execute(loginParams, this);
        }
    }


    //region LOGIN LISTENER
    @Override
    public void onSuccess(User user) {
        final LoginView loginView = getView();

        if (loginView != null) {
            loginView.hideLoading();

            if (user.getUserType() == UserTypeDescriptor.UserTypeDef.ADMIN) {
                loginView.showAdminView();
            } else {
                loginView.showTechnicianView(user.getUserName());
            }
            loginView.cleanForm();
        }
    }

    @Override
    public void onError(ErrorBundle errorBundle) {
        if (getView() != null) {
            getView().hideLoading();
            String errorMessage = ErrorMessageFactory.createErrorMessage(errorBundle);
            getView().showError(errorMessage);
        }
    }
    //endregion


    //region VIEW INTERFACE
    public interface LoginView extends Presenter.View {

        void showAdminView();

        void showTechnicianView(String userName);

        void showError(String errorMessage);

        void cleanForm();
    }
    //endregion
}
