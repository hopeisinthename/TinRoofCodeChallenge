package com.dhopedeveloper.tinroofcodechallenge;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    private static final String TAG = TaskViewModel.class.getSimpleName();

    private static List<Task> taskList = new ArrayList<>();


    public LiveData<List<Task>> taskListLiveData;

    public LiveData<List<Task>> getAllIncompleteTasksListLiveData() {
        return taskListLiveData;
    }

    private MutableLiveData<List<Task>> taskListMutableLiveData = new MutableLiveData<>();

    private TaskDao taskDao;

    public TaskViewModel(@NonNull Application application) {
        super(application);

        initData(application);
    }

    private void initData(Application application) {

      taskList =  new ArrayList<>(TaskSingleton.getInstance(application).getTaskList());

      TaskDatabase taskDatabase = TaskDatabase.getTaskDatabaseInstance(application);

        taskDao = taskDatabase.taskDao();


        if (taskDao.getAllIncompleteTasks() != null) {
            taskListLiveData = getTaskListLiveData();
        }
    }



    private LiveData<List<Task>> getTaskListLiveData() {

        if (taskList.size() > 0) {
            taskListMutableLiveData.setValue(taskList);
        } else {
            Log.v(TAG, "Task List is empty in View Model! ");
        }

        return taskListMutableLiveData;
    }

}
