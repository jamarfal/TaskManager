package org.example.taskmanager.common.view;

public class Presenter<T extends Presenter.View> {

  private T view;

  public void setView(T view) {
    this.view = view;
  }

  public T getView() {
    return view;
  }

  public void initialize() {

  }

  public void update() {

  }

  public void destroy(){
    this.view = null;
  }

  public interface View {

    void showLoading();

    void hideLoading();

  }
}
