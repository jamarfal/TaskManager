package org.example.taskmanager.entities;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by josealbertomartinfalcon on 3/2/18.
 */

public class Task extends RealmObject {

    public static Task EMPTY_TASK = new Task.Builder().build();

    @PrimaryKey
    private String id;

    private int taskType;

    private int durationInMinutes;

    private String description;

    private boolean completed;

    private Date date;

    public Task() {
    }

    public Task(String id, int taskType, int durationInMinutes, String description,
                boolean completed, Date date) {
        this.id = id;
        this.taskType = taskType;
        this.durationInMinutes = durationInMinutes;
        this.description = description;
        this.completed = completed;
        this.date = date;
    }


    //region GETTER AND SETTER
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    //endregion

    //region BUILDER
    public static final class Builder {
        private String id;
        private int taskType;
        private int durationInMinutes;
        private String description;
        private boolean completed;
        private Date date = new Date();

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder taskType(int val) {
            taskType = val;
            return this;
        }

        public Builder durationInMinutes(int val) {
            durationInMinutes = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Builder completed(boolean val) {
            completed = val;
            return this;
        }

        public Builder date(Date val) {
            date = val;
            return this;
        }

        public Task build() {
            return new Task(id, taskType, durationInMinutes, description, completed, date);
        }
    }
    //endregion


}
