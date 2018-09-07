package vn.viviu.fxpfitnesshulahoop.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import vn.viviu.fxpfitnesshulahoop.data.database.entity.Exercises;

/**
 * Created by PHAMHOAN on 3/5/2018.
 */
@Dao
public interface ExercisesDao {
    @Query("SELECT * FROM Exercises")
    List<Exercises> getExercisesTitle();
}
