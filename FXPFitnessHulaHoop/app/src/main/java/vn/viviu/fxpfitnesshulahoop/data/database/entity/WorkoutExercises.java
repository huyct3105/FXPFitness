package vn.viviu.fxpfitnesshulahoop.data.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by PHAMHOAN on 3/5/2018.
 */
@Entity
public class WorkoutExercises {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idWorkoutExercises")
    private int idWorkoutExercises;
    @ColumnInfo(name = "idWorkout")
    private int idWorkout;
    @ColumnInfo(name = "idExercises")
    private int idExercises;
    @ColumnInfo(name = "setsReps")
    private String setReps;
    @ColumnInfo(name = "exercisesNote")
    private String exercisesNote;
    @ColumnInfo(name = "time")
    private int time;

    public WorkoutExercises(int idWorkout, int idExercises, String setReps, String exercisesNote, int time) {
        this.idWorkout = idWorkout;
        this.idExercises = idExercises;
        this.setReps = setReps;
        this.exercisesNote = exercisesNote;
        this.time = time;
    }

    public int getIdWorkoutExercises() {
        return idWorkoutExercises;
    }

    public void setIdWorkoutExercises(int idWorkoutExercises) {
        this.idWorkoutExercises = idWorkoutExercises;
    }

    public int getIdWorkout() {
        return idWorkout;
    }

    public void setIdWorkout(int idWorkout) {
        this.idWorkout = idWorkout;
    }

    public int getIdExercises() {
        return idExercises;
    }

    public void setIdExercises(int idExercises) {
        this.idExercises = idExercises;
    }

    public String getSetReps() {
        return setReps;
    }

    public void setSetReps(String setReps) {
        this.setReps = setReps;
    }

    public String getExercisesNote() {
        return exercisesNote;
    }

    public void setExercisesNote(String exercisesNote) {
        this.exercisesNote = exercisesNote;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
