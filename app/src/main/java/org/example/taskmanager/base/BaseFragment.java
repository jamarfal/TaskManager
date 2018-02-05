package org.example.taskmanager.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by jamarfal on 4/2/18.
 */

public abstract class BaseFragment extends Fragment implements Presenter.View {

    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);

        bindViews(rootView);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        this.unbinder.unbind();
        super.onDestroyView();
    }

    protected abstract int getLayoutId();

    private void bindViews(View rootView) {
        this.unbinder = ButterKnife.bind(this, rootView);
    }
}
