package vn.viviu.fxpfitnesshulahoop.data.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by PHAMHOAN on 3/5/2018.
 */
@Entity
public class Workout {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idWorkout")
    private int idWorkout;
    @ColumnInfo(name = "workoutTitle")
    private String workoutTitle;

    public Workout(String workoutTitle) {
        this.workoutTitle = workoutTitle;
    }

    public int getIdWorkout() {
        return idWorkout;
    }

    public void setIdWorkout(int idWorkout) {
        this.idWorkout = idWorkout;
    }

    public String getWorkoutTitle() {
        return workoutTitle;
    }

    public void setWorkoutTitle(String workoutTitle) {
        this.workoutTitle = workoutTitle;
    }
}
