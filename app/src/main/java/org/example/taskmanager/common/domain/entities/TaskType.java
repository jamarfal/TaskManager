package org.example.taskmanager.common.domain.entities;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by josealbertomartinfalcon on 3/2/18.
 */

public class TaskType {

    //region TASK TYPES ID DESCRIPTORS
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({STOCK_CLERK_ID, CASHIER_ID, WRAPPER_ID, OTHER_ID})
    private @interface TaskTypeIdDef {
    }

    private static final int STOCK_CLERK_ID = 0;
    private static final int CASHIER_ID = 1;
    private static final int WRAPPER_ID = 2;
    private static final int OTHER_ID = 3;
    //endregion

    //region TASK TYPES NAMES DESCRIPTORS
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({STOCK_CLERK_NAME, CASHIER_NAME, WRAPPER_NAME, OTHER_NAME})
    private @interface TaskTypeNameDef {
    }

    private static final String STOCK_CLERK_NAME = "Reponedor";
    private static final String CASHIER_NAME = "Cajero";
    private static final String WRAPPER_NAME = "Envolver";
    private static final String OTHER_NAME = "Otros";
    //endregion

    private @TaskTypeIdDef
    int id;
    private @TaskTypeNameDef
    String name;

    private TaskType(@TaskTypeIdDef int id, @TaskTypeNameDef String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    //region FACTORY
    public static TaskType getStockClerkTaskTyoe() {
        return new TaskType(STOCK_CLERK_ID, STOCK_CLERK_NAME);
    }

    public static TaskType getCashierTaskType() {
        return new TaskType(CASHIER_ID, CASHIER_NAME);
    }

    public static TaskType getWrapperTaskType() {
        return new TaskType(WRAPPER_ID, WRAPPER_NAME);
    }

    public static TaskType getOtherTaskType() {
        return new TaskType(OTHER_ID, OTHER_NAME);
    }

    public static List<TaskType> getAllTaskTypes() {
        List<TaskType> bunchOfTaskTypes = new ArrayList<>();
        bunchOfTaskTypes.add(getStockClerkTaskTyoe());
        bunchOfTaskTypes.add(getCashierTaskType());
        bunchOfTaskTypes.add(getWrapperTaskType());
        bunchOfTaskTypes.add(getOtherTaskType());
        return bunchOfTaskTypes;
    }
    //endregion


}
