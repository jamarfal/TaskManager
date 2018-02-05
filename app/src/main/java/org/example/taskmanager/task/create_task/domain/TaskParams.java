package org.example.taskmanager.task.create_task.domain;

/**
 * Created by josealbertomartinfalcon on 4/2/18.
 * Email: jalbertomartinfalcon@gmail.com
 */

public class TaskParams {

    private final int durationInMinutes;
    private final String description;
    private final int taskType;

    public TaskParams(int durationInMinutes, String description, int taskType) {
        this.durationInMinutes = durationInMinutes;
        this.description = description;
        this.taskType = taskType;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public String getDescription() {
        return description;
    }

    public int getTaskType() {
        return taskType;
    }


    public boolean paramsAreValid(){
        return durationInMinutes > 0 && !description.isEmpty() && taskType != -1;
    }
}
