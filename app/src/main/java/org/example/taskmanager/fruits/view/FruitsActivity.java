package org.example.taskmanager.fruits.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import org.example.taskmanager.R;
import org.example.taskmanager.application.TaskManagerApplication;
import org.example.taskmanager.common.repository.dto.ApiItemDto;
import org.example.taskmanager.common.view.BaseActivity;
import org.example.taskmanager.fruits.presenter.FruitsPresenter;
import org.example.taskmanager.fruits.view.adapter.FruitsAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class FruitsActivity extends BaseActivity implements FruitsPresenter.FruitsView {

    public static void start(Context context) {
        Intent intent = new Intent(context, FruitsActivity.class);
        context.startActivity(intent);
    }

    @BindView(R.id.fruit_list)
    RecyclerView mFruitRecyclerView;

    @Inject
    FruitsPresenter fruitsPresenter;

    private FruitsAdapter fruitsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeDagger();

        setupFruitsListView();

        fruitsPresenter.setView(this);
        fruitsPresenter.initialize();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initializeToolbar() {
        super.initializeToolbar();
        if (toolbarTitle != null) {
            toolbarTitle.setText(getString(R.string.title_fruit_list_activity));
        }

        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_fruits;
    }

    @Override
    public void loadFruits(List<ApiItemDto> bunchOfApiItems) {
        fruitsAdapter.updateFruits(bunchOfApiItems);
        fruitsAdapter.notifyDataSetChanged();
    }

    private void setupFruitsListView() {
        fruitsAdapter = new FruitsAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        mFruitRecyclerView.setLayoutManager(linearLayoutManager);
        mFruitRecyclerView.setHasFixedSize(true);
        mFruitRecyclerView.setAdapter(fruitsAdapter);
    }

    private void initializeDagger() {
        TaskManagerApplication app = (TaskManagerApplication) getApplication();
        app.getMainComponent().inject(this);
    }


}
