package com.dhopedeveloper.tinroofcodechallenge;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TaskAdapter taskAdapter;
    private RecyclerView taskRecyclerView;

    private TaskViewModel taskViewModel;

    private ProgressBar progressBar;
    private List<Task> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.task_progress_bar);

        taskRecyclerView = findViewById(R.id.task_recycler_view);
        taskAdapter = new TaskAdapter(this);

        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskRecyclerView.setAdapter(taskAdapter);

        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);

    }

    @Override
    protected void onStart() {
        super.onStart();

        taskViewModel.getAllIncompleteTasksListLiveData().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable List<Task> tasks) {

                taskAdapter.updateTasks(tasks);
                taskList = tasks;

                if (taskList.size() > 12) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
