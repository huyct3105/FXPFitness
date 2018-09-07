package vn.viviu.fxpfitnesshulahoop.data.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "UserWorkoutGroup")
public class UserWorkoutGroup {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idUserWorkoutGroup")
    private int idUserWorkoutGroup;
    @ColumnInfo(name = "idUser")
    private int idUser;
    @ColumnInfo(name = "idWorkoutGroup")
    private int idWorkoutGroup;
    @ColumnInfo(name = "currentDate")
    private int currentDate;

    public UserWorkoutGroup(int idUser, int idWorkoutGroup, int currentDate) {
        this.idUser = idUser;
        this.idWorkoutGroup = idWorkoutGroup;
        this.currentDate = currentDate;
    }

    public int getIdUserWorkoutGroup() {
        return idUserWorkoutGroup;
    }

    public void setIdUserWorkoutGroup(int idUserWorkoutGroup) {
        this.idUserWorkoutGroup = idUserWorkoutGroup;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdWorkoutGroup() {
        return idWorkoutGroup;
    }

    public void setIdWorkoutGroup(int idWorkoutGroup) {
        this.idWorkoutGroup = idWorkoutGroup;
    }

    public int getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(int currentDate) {
        this.currentDate = currentDate;
    }
}
