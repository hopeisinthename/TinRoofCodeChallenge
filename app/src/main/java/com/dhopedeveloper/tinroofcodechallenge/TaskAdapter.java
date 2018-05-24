package com.dhopedeveloper.tinroofcodechallenge;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;

public class TaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public class TaskViewHolder extends RecyclerView.ViewHolder {

        public TaskViewHolder(View itemView) {
            super(itemView);


        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }



}
