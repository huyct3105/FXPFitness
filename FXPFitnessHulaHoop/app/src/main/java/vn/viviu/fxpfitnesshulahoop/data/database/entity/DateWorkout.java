package vn.viviu.fxpfitnesshulahoop.data.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by PHAMHOAN on 3/5/2018.
 */
@Entity
public class DateWorkout {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idDate")
    private int idDate;
    @ColumnInfo(name = "idWorkoutGroup")
    private int idWorkoutGroup;
    @ColumnInfo(name = "idWorkout")
    private int idWorkout;
    @ColumnInfo(name = "dateNumber")
    private int dateNumber;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "completeDescription")
    private String completeDescription;

    public DateWorkout(int idWorkoutGroup, int idWorkout, int dateNumber, String description, String completeDescription) {
        this.idWorkoutGroup = idWorkoutGroup;
        this.idWorkout = idWorkout;
        this.dateNumber = dateNumber;
        this.description = description;
        this.completeDescription = completeDescription;
    }

    public DateWorkout() {
    }

    public int getIdDate() {
        return idDate;
    }

    public void setIdDate(int idDate) {
        this.idDate = idDate;
    }

    public int getIdWorkoutGroup() {
        return idWorkoutGroup;
    }

    public void setIdWorkoutGroup(int idWorkoutGroup) {
        this.idWorkoutGroup = idWorkoutGroup;
    }

    public int getIdWorkout() {
        return idWorkout;
    }

    public void setIdWorkout(int idWorkout) {
        this.idWorkout = idWorkout;
    }

    public int getDateNumber() {
        return dateNumber;
    }

    public void setDateNumber(int dateNumber) {
        this.dateNumber = dateNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompleteDescription() {
        return completeDescription;
    }

    public void setCompleteDescription(String completeDescription) {
        this.completeDescription = completeDescription;
    }
}
