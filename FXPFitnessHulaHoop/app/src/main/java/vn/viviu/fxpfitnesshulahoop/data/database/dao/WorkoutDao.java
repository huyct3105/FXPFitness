package vn.viviu.fxpfitnesshulahoop.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

/**
 * Created by PHAMHOAN on 3/5/2018.
 */
@Dao
public interface WorkoutDao {
    @Query("Select workoutTitle from Workout where idWorkout = :idW")
    String getWorkoutNameByID(int idW);
}
