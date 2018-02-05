package org.example.taskmanager.task.task_list.view.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import org.example.taskmanager.R;
import org.example.taskmanager.common.domain.entities.Task;

import java.util.Collections;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jamarfal on 4/2/18.
 */

public class TaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public interface OnMarkTaskAsCompletedListener {
        void onMarkTaskAsCompleted(Task task);
    }

    private final LayoutInflater layoutInflater;
    private List<Task> bunchOfTask;
    private OnMarkTaskAsCompletedListener onMarkTaskAsCompletedListener;

    public void setOnMarkTaskAsCompletedListener(
            OnMarkTaskAsCompletedListener onMarkTaskAsCompletedListener) {
        this.onMarkTaskAsCompletedListener = onMarkTaskAsCompletedListener;
    }

    public TaskAdapter(Context context) {
        this.bunchOfTask = Collections.emptyList();
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = layoutInflater.inflate(R.layout.item_task, parent, false);
        final TaskViewHolder taskViewHolder = new TaskViewHolder(view);
        taskViewHolder.markAsCompletedCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onMarkTaskAsCompletedListener != null) {
                    Task taskToComplete = bunchOfTask.get(taskViewHolder.getAdapterPosition());
                    onMarkTaskAsCompletedListener.onMarkTaskAsCompleted(taskToComplete);
                }
            }
        });
        return taskViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Task task = bunchOfTask.get(position);
        ((TaskViewHolder) holder).bindTo(task);
    }

    @Override
    public int getItemCount() {
        return this.bunchOfTask != null ? this.bunchOfTask.size() : 0;
    }


    public void updateTasks(List<Task> bunchOfTask) {
        this.bunchOfTask = bunchOfTask;
    }

    public void updateTask(Task task) {
        int searchedTaskPosition = bunchOfTask.indexOf(task);
        if (searchedTaskPosition != -1) {
            notifyItemChanged(searchedTaskPosition);
        }
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_task_description_label)
        TextView mDescriptionLabel;
        @BindView(R.id.item_task_card_container)
        CardView mCardViewContainer;
        @BindView(R.id.item_task_completed_checkbox)
        CheckBox markAsCompletedCheckBox;

        @BindColor(R.color.green)
        int greenColor;
        @BindColor(R.color.red)
        int redColor;

        public TaskViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindTo(Task task) {
            markAsCompletedCheckBox.setChecked(task.isCompleted());

            if (task.isCompleted()) {
                mCardViewContainer.setCardBackgroundColor(greenColor);
            } else {
                mCardViewContainer.setCardBackgroundColor(redColor);
            }

            mDescriptionLabel.setText(task.getDescription());
        }

    }
}
