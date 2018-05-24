package com.dhopedeveloper.tinroofcodechallenge;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "task_table")
public class Task {


    @SerializedName("userId")
    @Expose
    private Integer userId;

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private int id;


    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("completed")
    @Expose
    private Boolean completed;

    public Task(Integer userId, int id, String title, Boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Task task = (Task) o;

        if (id != task.id)
            return false;
        if (! userId.equals(task.userId))
            return false;
        if (! title.equals(task.title))
            return false;
        return completed.equals(task.completed);
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + id;
        result = 31 * result + title.hashCode();
        result = 31 * result + completed.hashCode();
        return result;
    }
}
