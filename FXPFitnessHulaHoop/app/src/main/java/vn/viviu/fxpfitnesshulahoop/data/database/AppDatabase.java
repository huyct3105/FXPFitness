package vn.viviu.fxpfitnesshulahoop.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.fstyle.library.helper.AssetSQLiteOpenHelperFactory;

import vn.viviu.fxpfitnesshulahoop.data.database.dao.DateWorkoutDao;
import vn.viviu.fxpfitnesshulahoop.data.database.dao.ExercisesDao;
import vn.viviu.fxpfitnesshulahoop.data.database.dao.NutritionDao;
import vn.viviu.fxpfitnesshulahoop.data.database.dao.UserDao;
import vn.viviu.fxpfitnesshulahoop.data.database.dao.UserWorkoutGroupDao;
import vn.viviu.fxpfitnesshulahoop.data.database.dao.WorkoutDao;
import vn.viviu.fxpfitnesshulahoop.data.database.dao.WorkoutExercisesDao;
import vn.viviu.fxpfitnesshulahoop.data.database.dao.WorkoutGroupDao;
import vn.viviu.fxpfitnesshulahoop.data.database.entity.DateWorkout;

import vn.viviu.fxpfitnesshulahoop.data.database.entity.Exercises;
import vn.viviu.fxpfitnesshulahoop.data.database.entity.Nutrition;
import vn.viviu.fxpfitnesshulahoop.data.database.entity.User;
import vn.viviu.fxpfitnesshulahoop.data.database.entity.UserWorkoutGroup;
import vn.viviu.fxpfitnesshulahoop.data.database.entity.Workout;
import vn.viviu.fxpfitnesshulahoop.data.database.entity.WorkoutExercises;
import vn.viviu.fxpfitnesshulahoop.data.database.entity.WorkoutGroup;
import vn.viviu.fxpfitnesshulahoop.util.AppKey;

/**
 * Created by PHAMHOAN on 3/5/2018.
 */
@Database(entities = {User.class, UserWorkoutGroup.class, DateWorkout.class, Exercises.class, Nutrition.class, Workout.class
        , WorkoutExercises.class, WorkoutGroup.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserWorkoutGroupDao userWorkoutGroupDao();

    public abstract UserDao userDao();

    public abstract DateWorkoutDao DateWorkoutDao();

    public abstract ExercisesDao ExercisesDao();

    public abstract NutritionDao NutritionDao();

    public abstract WorkoutDao WorkoutDao();

    public abstract WorkoutExercisesDao WorkoutExercisesDao();

    public abstract WorkoutGroupDao WorkoutGroupDao();


    private static AppDatabase appDatabase = null;

    public static AppDatabase getInstance(Context applicationContext) {

        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(applicationContext, AppDatabase.class, AppKey.DATABASE_NAME)
                    .openHelperFactory(new AssetSQLiteOpenHelperFactory())
                    .allowMainThreadQueries()
                    .build();
            appDatabase.getOpenHelper().getWritableDatabase();
        }

        return appDatabase;
    }
}
