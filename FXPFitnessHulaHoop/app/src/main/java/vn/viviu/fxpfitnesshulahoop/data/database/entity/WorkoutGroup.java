package vn.viviu.fxpfitnesshulahoop.data.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 * Created by PHAMHOAN on 3/5/2018.
 */

@Entity
public class WorkoutGroup implements Serializable{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idWorkoutGroup")
    private int idWorkoutGroup;
    @ColumnInfo(name = "workoutgroupName")
    private String workoutgroupName;

    @Ignore
    public WorkoutGroup(String workoutgroupName) {
        this.workoutgroupName = workoutgroupName;
    }

    public WorkoutGroup(int idWorkoutGroup, String workoutgroupName) {
        this.idWorkoutGroup = idWorkoutGroup;
        this.workoutgroupName = workoutgroupName;
    }


    public int getIdWorkoutGroup() {
        return idWorkoutGroup;
    }

    public void setIdWorkoutGroup(int idWorkoutGroup) {
        this.idWorkoutGroup = idWorkoutGroup;
    }

    public String getWorkoutgroupName() {
        return workoutgroupName;
    }

    public void setWorkoutgroupName(String workoutgroupName) {
        this.workoutgroupName = workoutgroupName;
    }
}
