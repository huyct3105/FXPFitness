package vn.viviu.fxpfitnesshulahoop.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;


import java.util.List;



@Dao
public interface DateWorkoutDao {
    @Query("Select workoutTitle,description,Workout.idWorkout from DateWorkout join Workout where DateWorkout.idWorkoutGroup = :idWO " +
            "and DateWorkout.idWorkout = Workout.idWorkout")
    List<DateWorkoutTitle> getListWorkoutByDate(int idWO);

    @Query("Select count(*) from DateWorkout join Workout where DateWorkout.idWorkoutGroup = :idWO " +
            "and DateWorkout.idWorkout = Workout.idWorkout")
    int getCountListWorkoutByDate(int idWO);

     static class DateWorkoutTitle{
        public String workoutTitle;
        public String description;
         public int idWorkout;
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

         public String getDescription() {
             return description;
         }

         public void setDescription(String description) {
             this.description = description;
         }
     }


    @Query("SELECT completeDescription FROM dateworkout WHERE idWorkout = :workOutId")
    String getWorkOutCompleteDescription(int workOutId);


}
