package org.example.taskmanager.common;

import org.example.taskmanager.entities.RealmInt;
import org.example.taskmanager.entities.Task;
import org.example.taskmanager.entities.TaskType;
import org.example.taskmanager.entities.User;
import org.example.taskmanager.entities.UserTypeDescriptor;
import org.example.taskmanager.login.repository.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import io.realm.RealmList;

/**
 * Created by josealbertomartinfalcon on 3/2/18.
 * Email: jalbertomartinfalcon@gmail.com
 */

public class LocalDbPopulator {

    private final UserRepository userRepository;

    public LocalDbPopulator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void fillDbWithFakeUsers() {
        List<User> bunchOfUsers = new ArrayList<>();

        User admin = new User.Builder()
                .userName("admin")
                .password("1234")
                .name("Antonio López")
                .userType(UserTypeDescriptor.UserTypeDef.ADMIN)
                .abilities(new RealmList<RealmInt>())
                .build();

        bunchOfUsers.add(admin);





        RealmList<RealmInt> abilities_1 = new RealmList<>();
        abilities_1.add(new RealmInt(TaskType.getCashierTaskType().getId()));
        abilities_1.add(new RealmInt(TaskType.getStockClerkTaskTyoe().getId()));

        RealmList<Task> tasks_1 = new RealmList<>();
        Task task1 = new Task.Builder()
                .id(UUID.randomUUID().toString())
                .completed(false)
                .taskType(TaskType.getCashierTaskType().getId())
                .description("Otra prueba")
                .date(new Date())
                .durationInMinutes(25)
                .build();

        tasks_1.add(task1);

        User technician_1 = new User.Builder()
                .userName("tech1")
                .password("1234")
                .name("Rafael Fernández")
                .userType(UserTypeDescriptor.UserTypeDef.TECHNICIAN)
                .abilities(abilities_1)
                .assignedTasks(tasks_1)
                .build();

        bunchOfUsers.add(technician_1);





        RealmList<RealmInt> abilities_2 = new RealmList<>();
        abilities_2.add(new RealmInt(TaskType.getCashierTaskType().getId()));
        abilities_2.add(new RealmInt(TaskType.getOtherTaskType().getId()));


        RealmList<Task> tasks_2 = new RealmList<>();
        Task task2 = new Task.Builder()
                .id(UUID.randomUUID().toString())
                .completed(false)
                .taskType(TaskType.getCashierTaskType().getId())
                .description("probando")
                .date(new Date())
                .durationInMinutes(30)
                .build();
        Task task2B = new Task.Builder()
                .id(UUID.randomUUID().toString())
                .completed(false)
                .taskType(TaskType.getCashierTaskType().getId())
                .description("probando 2")
                .date(new Date())
                .durationInMinutes(80)
                .build();

        tasks_2.add(task2);
        tasks_2.add(task2B);
        User technician_2 = new User.Builder()
                .userName("tech2")
                .password("1234")
                .name("Pepe Martín")
                .userType(UserTypeDescriptor.UserTypeDef.TECHNICIAN)
                .abilities(abilities_2)
                .assignedTasks(tasks_2)
                .build();

        bunchOfUsers.add(technician_2);






        RealmList<RealmInt> abilities_3 = new RealmList<>();
        abilities_3.add(new RealmInt(TaskType.getWrapperTaskType().getId()));

        User technician_3 = new User.Builder()
                .userName("tech3")
                .password("1234")
                .name("Manolo Ochoa")
                .userType(UserTypeDescriptor.UserTypeDef.TECHNICIAN)
                .abilities(abilities_3)
                .build();

        bunchOfUsers.add(technician_3);






        RealmList<RealmInt> abilities_4 = new RealmList<>();
        abilities_4.add(new RealmInt(TaskType.getCashierTaskType().getId()));

        RealmList<Task> tasks_4 = new RealmList<>();
        Task task_4 = new Task.Builder()
                .id(UUID.randomUUID().toString())
                .completed(false)
                .taskType(TaskType.getCashierTaskType().getId())
                .description("probando")
                .date(new Date())
                .durationInMinutes(10)
                .build();

        tasks_4.add(task_4);

        User technician_4 = new User.Builder()
                .userName("tech4")
                .password("1234")
                .name("Manolo MArtín")
                .userType(UserTypeDescriptor.UserTypeDef.TECHNICIAN)
                .abilities(abilities_4)
                .assignedTasks(tasks_4)
                .build();

        bunchOfUsers.add(technician_4);

        userRepository.addUsers(bunchOfUsers);
    }

    public boolean hasBeenDbPopulatedWithDataPreviously() {
        List<User> bunchOfUsers = userRepository.getAllUsers();
        return !bunchOfUsers.isEmpty();
    }
}
