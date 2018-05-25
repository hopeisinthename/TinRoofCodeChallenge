package com.dhopedeveloper.tinroofcodechallenge;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Task> taskList;

    private TextView taskId;
    private TextView taskIncomplete;
    private WeakReference<Context> contextWeakReference;

    public TaskAdapter(Context context) {
        contextWeakReference = new WeakReference<>(context);
        taskList = new ArrayList<>(TaskSingleton.getInstance(context.getApplicationContext()).getTaskList());
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {

        public TaskViewHolder(View itemView) {
            super(itemView);

            taskId = itemView.findViewById(R.id.task_item_id_tv);
            taskIncomplete = itemView.findViewById(R.id.task_item_incomplete_tasks_tv);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item_layout, parent, false);

        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Task task = taskList.get(position);

        if (!task.getCompleted()) {
            taskId.setText(task.getId());
            taskIncomplete.setText(task.getCompleted().toString());
        }
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }


    public void updateTasks(List<Task> tasks) {
        taskList = tasks;
        notifyDataSetChanged();
    }

}
