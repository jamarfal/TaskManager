package org.example.taskmanager.task.task_list.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import org.example.taskmanager.R;
import org.example.taskmanager.application.TaskManagerApplication;
import org.example.taskmanager.common.view.BaseActivity;
import org.example.taskmanager.common.domain.entities.Task;
import org.example.taskmanager.fruits.view.FruitsActivity;
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

    //region ACTIVITY METHODS
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeDagger();

        setupTaskListView();

        String userName = receiveUserName();

        taskListPresenter.setView(this);
        taskListPresenter.initialize(userName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.logout_action) {
            taskListPresenter.doLogout();
        } else if (id == R.id.show_fruits_action) {
            FruitsActivity.start(this);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initializeToolbar() {
        super.initializeToolbar();
        if (toolbarTitle != null) {
            toolbarTitle.setText(getString(R.string.title_task_list_activity));
        }
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
        taskAdapter.updateTask(task);
        // taskAdapter.notifyDataSetChanged();
    }

    @Override
    public void close() {
        finish();
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
