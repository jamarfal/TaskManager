package org.example.taskmanager.task.task_list.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.example.taskmanager.R;
import org.example.taskmanager.TaskManagerApplication;
import org.example.taskmanager.base.BaseActivity;
import org.example.taskmanager.entities.Task;
import org.example.taskmanager.task.task_list.presenter.TaskListPresenter;
import org.example.taskmanager.task.task_list.view.adapter.TaskAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class TaskListActivity extends BaseActivity implements TaskListPresenter.TaskListView,
        TaskAdapter.OnMarkTaskAsCompletedListener {

    public static final String USERNAME_EXTRA_KEY = "username";

    public static void showTaskListGivenAuserId(Context context, String username) {
        Intent intent = new Intent(context, TaskListActivity.class);
        intent.putExtra(USERNAME_EXTRA_KEY, username);
        context.startActivity(intent);
    }

    @BindView(R.id.task_list)
    RecyclerView mTaskListView;

    private TaskAdapter taskAdapter;
    private String userName = "";

    @Inject
    TaskListPresenter taskListPresenter;

    //region LIFE CYCLE METHODS
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeDagger();

        setupTaskListView();

        String userName = receiveUserName();

        taskListPresenter.setView(this);
        taskListPresenter.initialize(userName);
    }
    //endregion

    //region BASEACTIVITY ABSTRACT METHODS IMPLEMENTATION
    @Override
    public int getLayoutId() {
        return R.layout.activity_task_list;
    }
    //endregion

    //region VIEW IMPLEMENTATION
    @Override
    public void loadTask(List<Task> bunchOfTasks) {
        taskAdapter.updateTasks(bunchOfTasks);
        taskAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateTask(Task task) {
        taskAdapter.notifyDataSetChanged();
    }
    //endregion


    //region  TaskAdapter.OnMarkTaskAsCompletedListener CALLBACK
    @Override
    public void onMarkTaskAsCompleted(Task task) {
        taskListPresenter.markTaskAsCompleted(task);
    }
    //endregion

    //region PRIVATE METHODS
    private void initializeDagger() {
        TaskManagerApplication app = (TaskManagerApplication) getApplication();
        app.getMainComponent().inject(this);
    }

    private void setupTaskListView() {
        taskAdapter = new TaskAdapter(this);
        taskAdapter.setOnMarkTaskAsCompletedListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        mTaskListView.setLayoutManager(linearLayoutManager);
        mTaskListView.setHasFixedSize(true);
        mTaskListView.setAdapter(taskAdapter);
    }

    private String receiveUserName() {
        String userName = "";
        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            userName = arguments.getString(USERNAME_EXTRA_KEY, "");
        }
        return userName;
    }
    //endregion
}
