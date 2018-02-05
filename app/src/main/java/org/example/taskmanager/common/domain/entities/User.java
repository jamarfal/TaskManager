package org.example.taskmanager.common.domain.entities;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by josealbertomartinfalcon on 3/2/18.
 * Email: jalbertomartinfalcon@gmail.com
 */

public class User extends RealmObject {

    public static User EMPTY_USER = new User.Builder().build();

    @PrimaryKey
    private String userName;

    private String password;

    private String name;

    private @UserTypeDescriptor.UserTypeDef
    int userType;

    private RealmList<RealmInt> abilities = new RealmList<>();

    private RealmList<Task> assignedTasks = new RealmList<>();

    public User() {

    }

    private User(String userName, String password, String name, @UserTypeDescriptor.UserTypeDef int userType,
                 RealmList<RealmInt> abilities, RealmList<Task> assignedTasks) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.userType = userType;
        this.abilities = abilities;
        this.assignedTasks = assignedTasks;
    }

    //region GETTER AND SETTERS
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public RealmList<RealmInt> getAbilities() {
        return abilities;
    }

    public void setAbilities(RealmList<RealmInt> abilities) {
        this.abilities = abilities;
    }

    public RealmList<Task> getAssignedTasks() {
        return assignedTasks;
    }

    public void setAssignedTasks(RealmList<Task> assignedTasks) {
        this.assignedTasks = assignedTasks;
    }
    //endregion

    //region BUILDER
    public static final class Builder {
        private String userName;
        private String password;
        private String name;
        private @UserTypeDescriptor.UserTypeDef
        int userType;
        private RealmList<RealmInt> abilities;
        private RealmList<Task> assignedTasks;

        public Builder() {
        }

        public Builder userName(String val) {
            userName = val;
            return this;
        }

        public Builder password(String val) {
            password = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder userType(int val) {
            userType = val;
            return this;
        }

        public Builder abilities(RealmList<RealmInt> val) {
            abilities = val;
            return this;
        }

        public Builder assignedTasks(RealmList<Task> val) {
            assignedTasks = val;
            return this;
        }

        public User build() {
            return new User(userName, password, name, userType, abilities, assignedTasks);
        }
    }
    //endregion


}
