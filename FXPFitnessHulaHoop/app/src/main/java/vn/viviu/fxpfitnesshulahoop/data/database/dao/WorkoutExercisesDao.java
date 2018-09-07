package vn.viviu.fxpfitnesshulahoop.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by PHAMHOAN on 3/5/2018.
 */
@Dao
public interface WorkoutExercisesDao {

    @Query("select exerciseName,focus,image,setsReps, idExcercises, link from WorkoutExercises join Exercises where " +
            "WorkoutExercises.idExercises = Exercises.idExcercises " +
            "and WorkoutExercises.idWorkout = :idWO")
    List<ExercisesW> getExercisesByWO(int idWO);


    static class ExercisesW {
        String exerciseName;
        String focus;
        String image;
        String link;
        String setsReps;
        int idExcercises;

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }



        public int getIdExcercises() {
            return idExcercises;
        }

        public void setIdExcercises(int idExcercises) {
            this.idExcercises = idExcercises;
        }

        public String getExerciseName() {
            return exerciseName;
        }

        public void setExerciseName(String exerciseName) {
            this.exerciseName = exerciseName;
        }

        public String getFocus() {
            return focus;
        }

        public void setFocus(String focus) {
            this.focus = focus;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getSetsReps() {
            return setsReps;
        }

        public void setSetsReps(String setsReps) {
            this.setsReps = setsReps;
        }

    }
}
