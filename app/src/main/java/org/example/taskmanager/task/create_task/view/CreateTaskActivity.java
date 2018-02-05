package org.example.taskmanager.task.create_task.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.example.taskmanager.R;
import org.example.taskmanager.TaskManagerApplication;
import org.example.taskmanager.base.BaseActivity;
import org.example.taskmanager.entities.TaskType;
import org.example.taskmanager.fruits.FruitsActivity;
import org.example.taskmanager.task.create_task.presenter.CreateTaskPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.OnClick;

public class CreateTaskActivity extends BaseActivity implements CreateTaskPresenter.CreateTaskView {

    public static void start(Context context) {
        Intent starter = new Intent(context, CreateTaskActivity.class);
        context.startActivity(starter);
    }

    //region WIDGETS
    @BindView(R.id.root_layout)
    CoordinatorLayout rootLayout;
    @BindView(R.id.create_task_description_input)
    EditText mInputDescription;
    @BindView(R.id.create_task_duration_input)
    EditText mInputDuration;
    @BindView(R.id.create_task_task_type_selector)
    Spinner taskTypeSelector;

    @BindColor(R.color.green)
    int greenColor;
    @BindColor(android.R.color.white)
    int whiteColor;
    //endregion

    @Inject
    CreateTaskPresenter createTaskPresenter;

    //region LIFE CYCLE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeDagger();

        createTaskPresenter.setView(this);
        createTaskPresenter.initialize();
    }

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.logout_action) {
            createTaskPresenter.doLogout();
        } else if (id == R.id.show_fruits_action) {
            FruitsActivity.start(this);
        }
        return super.onOptionsItemSelected(item);
    }
    //endregion

    //region ABSTRACT IMPLEMENTATION
    @Override
    public int getLayoutId() {
        return R.layout.activity_create_task;
    }
    //endregion


    @Override
    protected void initializeToolbar() {
        super.initializeToolbar();
        if (toolbarTitle != null) {
            toolbarTitle.setText(getString(R.string.title_create_task_activity));
        }
    }

    @Override
    public void fillTaskTypeSelector(List<TaskType> bunchOfTaskTypes) {
        ArrayAdapter<TaskType> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bunchOfTaskTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        taskTypeSelector.setAdapter(adapter);
    }

    //region VIEW IMPLEMENTATION
    @Override
    public void showError(String errorMessage) {
        Snackbar.make(rootLayout, errorMessage, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessMessage() {
        Snackbar snackbar = Snackbar.make(
                rootLayout,
                getString(R.string.successfull_created_task),
                Snackbar.LENGTH_SHORT);
        snackbar.getView().setBackgroundColor(greenColor);
        TextView textView = snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(whiteColor);
        snackbar.show();
    }

    @Override
    public void cleanForm() {
        mInputDescription.setText("");
        mInputDuration.setText("");
    }

    @Override
    public void close() {
        finish();
    }
    //endregion


    @OnClick(R.id.create_task_button)
    public void onCreateTaskButtonWasClicked() {
        createTaskPresenter.createTask(getDescription(), getDuration(), getTaskType());
    }


    private void initializeDagger() {
        TaskManagerApplication app = (TaskManagerApplication) getApplication();
        app.getMainComponent().inject(this);
    }

    private String getDescription() {
        return mInputDescription.getText().toString();
    }

    private String getDuration() {
        return mInputDuration.getText().toString();
    }

    private int getTaskType() {
        TaskType selectedTaskType = (TaskType) taskTypeSelector.getSelectedItem();
        return selectedTaskType.getId();
    }

}