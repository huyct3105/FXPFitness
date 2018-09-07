package vn.viviu.fxpfitnesshulahoop.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import vn.viviu.fxpfitnesshulahoop.data.database.entity.WorkoutGroup;


/**
 * Created by PHAMHOAN on 3/5/2018.
 */
@Dao
public interface WorkoutGroupDao {

    @Query("Select * from WorkoutGroup where idWorkoutGroup = :idWO")
    WorkoutGroup getWorkoutGroupById(int idWO);


    @Query("SELECT * FROM WorkoutGroup")
    List<WorkoutGroup> getAllWorkoutGroup();

    @Query("Select * from WorkoutGroup where WorkoutGroup.idWorkoutGroup In (Select UserWorkoutGroup.idWorkoutGroup from UserWorkoutGroup where UserWorkoutGroup.idUser = :idUser)")
    List<WorkoutGroup> getWorkoutGroupSelect(int idUser);

    @Query("Select * from WorkoutGroup where WorkoutGroup.idWorkoutGroup Not In (Select UserWorkoutGroup.idWorkoutGroup from UserWorkoutGroup where UserWorkoutGroup.idUser = :idUser)")
    List<WorkoutGroup> getWorkoutGroupUnSelect(int idUser);

    @Query("Select * from WorkoutGroup where WorkoutGroup.idWorkoutGroup In (Select UserWorkoutGroup.idWorkoutGroup from UserWorkoutGroup where UserWorkoutGroup.idUser = :idUser and UserWorkoutGroup.currentDate <> 0)")
    List<WorkoutGroup> getAllWorkoutGroupsReset(int idUser);


    @Update(onConflict = OnConflictStrategy.IGNORE)
    void updateWorkoutGroup(List<WorkoutGroup> groups);

}
