package org.example.taskmanager.login.domain;

import android.os.Handler;
import android.os.Looper;

import org.example.taskmanager.base.Error;
import org.example.taskmanager.base.UseCase;
import org.example.taskmanager.entities.User;
import org.example.taskmanager.login.repository.UserRepository;

import javax.inject.Inject;

/**
 * Created by josealbertomartinfalcon on 4/2/18.
 * Email: jalbertomartinfalcon@gmail.com
 */

public class DoLogin extends UseCase<LoginParams, User> {

    private final UserRepository userRepository;
    private LoginParams loginParams;
    private UseCaseCallback<User> useCaseCallback;

    @Inject
    public DoLogin(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void execute(LoginParams loginParams, UseCaseCallback<User> useCaseCallback) {
        this.loginParams = loginParams;
        this.useCaseCallback = useCaseCallback;
        new Thread(this).start();
    }

    @Override
    public void run() {
        final User user = userRepository.getUser(loginParams.getUserName());

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (userIsLogged(user)) {
                    useCaseCallback.onSuccess(user);

                } else {
                    useCaseCallback.onError(new ErrorWrapper(Error.Type.LOGIN_ERROR));
                }
            }
        }, 2000);
    }

    private boolean userIsLogged(User user) {
        return user != User.EMPTY_USER && user.getPassword().equalsIgnoreCase(loginParams.getPassword());
    }


}


