package org.example.taskmanager.fruits;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.example.taskmanager.R;
import org.example.taskmanager.TaskManagerApplication;
import org.example.taskmanager.common.ApiItemDto;
import org.example.taskmanager.common.BaseActivity;

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
