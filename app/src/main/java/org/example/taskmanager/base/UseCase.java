package org.example.taskmanager.base;


/**
 * Created by josealbertomartinfalcon on 18/3/17.
 * Email: jalbertomartinfalcon@gmail.com
 */

public abstract class UseCase<Input, Output> implements Runnable {

    public abstract void execute(Input input, UseCaseCallback<Output> useCaseCallback);

    public interface UseCaseCallback<Output> {
        void onSuccess(Output output);

        void onError(ErrorBundle errorBundle);
    }

    public static UseCaseCallback<Void> NO_CALLBACK = new UseCaseCallback<Void>() {
        @Override
        public void onSuccess(Void aVoid) {

        }

        @Override
        public void onError(ErrorBundle errorBundle) {

        }
    };
}
