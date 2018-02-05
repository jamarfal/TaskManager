/*
 * Copyright (C) 2015 Karumi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.example.taskmanager.di;

import org.example.taskmanager.fruits.FruitsActivity;
import org.example.taskmanager.login.view.LoginActivity;
import org.example.taskmanager.task.create_task.view.CreateTaskActivity;
import org.example.taskmanager.task.task_list.view.TaskListActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton @Component(modules = MainModule.class) public interface MainComponent {

  void inject(LoginActivity activity);

  void inject(CreateTaskActivity activity);

  void inject(TaskListActivity activity);

  void inject(FruitsActivity activity);
}
