package org.example.taskmanager.login.view;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.widget.EditText;

import org.example.taskmanager.R;
import org.example.taskmanager.TaskManagerApplication;
import org.example.taskmanager.base.BaseActivity;
import org.example.taskmanager.login.presenter.LoginPresenter;
import org.example.taskmanager.task.create_task.view.CreateTaskActivity;
import org.example.taskmanager.task.task_list.view.TaskListActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;


public class LoginActivity extends BaseActivity implements LoginPresenter.LoginView {

    //region WIDGETS
    @BindView(R.id.login_input_email)
    EditText mInputEmail;
    @BindView(R.id.login_input_password)
    EditText mInputPassword;
    @BindView(R.id.root_layout)
    CoordinatorLayout rootLayout;
    //endregion

    @Inject
    LoginPresenter loginPresenter;

    //region ACTIVITY LIFE CYCLE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeDagger();
        loginPresenter.setView(this);
        loginPresenter.initialize();
    }
    //endregion

    //region INHERITANCE METHODS
    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }
    //endregion

    //region ONCLICK
    @OnClick(R.id.login_button)
    public void onLoginButtonClicked() {
        loginPresenter.doLogin(getUserName(), getPassword());
    }
    //endregion

    //region VIEW IMPLEMENTATION
    @Override
    public void showAdminView() {
        CreateTaskActivity.start(this);
    }

    @Override
    public void showTechnicianView(String userName) {
        TaskListActivity.showTaskListGivenAuserId(this, userName);
    }

    @Override
    public void showError(String errorMessage) {
        Snackbar.make(rootLayout, errorMessage, Snackbar.LENGTH_SHORT).show();
    }
    //endregion

    private void initializeDagger() {
        TaskManagerApplication app = (TaskManagerApplication) getApplication();
        app.getMainComponent().inject(this);
    }

    private String getUserName() {
        return mInputEmail.getText().toString();
    }

    private String getPassword() {
        return mInputPassword.getText().toString();
    }
}

