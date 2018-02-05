package org.example.taskmanager.common.view;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.example.taskmanager.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity implements Presenter.View {

    @Nullable
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Nullable
    @BindView(R.id.progress_bar)
    View loadingView;
    @Nullable
    @BindView(R.id.toolbar_title)
    protected TextView toolbarTitle;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initializeButterKnife();
        initializeToolbar();
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    public abstract int getLayoutId();


    @Override
    public void showLoading() {
        if (loadingView != null) {
            loadingView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideLoading() {
        if (loadingView != null) {
            loadingView.setVisibility(View.GONE);
        }
    }

    private void initializeButterKnife() {
        this.unbinder = ButterKnife.bind(this);
    }


    protected void initializeToolbar() {
        if (toolbar != null) {
            if (toolbarTitle != null) {
                toolbarTitle.setText("");
                toolbar.setTitle("");
            }

            setSupportActionBar(toolbar);

            final ActionBar actionBar = getSupportActionBar();

            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(false);
            }
        }
    }


}
