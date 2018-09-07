package vn.viviu.fxpfitnesshulahoop.ui.exercises;

import vn.viviu.fxpfitnesshulahoop.data.database.entity.Exercises;
import vn.viviu.fxpfitnesshulahoop.data.database.entity.Nutrition;


public interface AdapterExercisesCallBack {
    void onItemClick(Exercises exercises, int position);
}
