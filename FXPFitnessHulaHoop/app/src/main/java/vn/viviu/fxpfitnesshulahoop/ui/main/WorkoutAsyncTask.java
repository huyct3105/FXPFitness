package vn.viviu.fxpfitnesshulahoop.ui.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import vn.viviu.fxpfitnesshulahoop.data.database.AppDatabase;
import vn.viviu.fxpfitnesshulahoop.data.database.dao.WorkoutGroupDao;
import vn.viviu.fxpfitnesshulahoop.data.database.entity.WorkoutGroup;
import vn.viviu.fxpfitnesshulahoop.ui.login.SharePreferencesManager;
import vn.viviu.fxpfitnesshulahoop.ui.main.adapter.WorkoutGroupAdapter;
import vn.viviu.fxpfitnesshulahoop.ui.main.listener.PassDataWorkoutListener;
import vn.viviu.fxpfitnesshulahoop.util.AppKey;

import static com.facebook.FacebookSdk.getApplicationContext;


public class WorkoutAsyncTask extends AsyncTask<Integer, Void, Void> {
    private WorkoutGroupAdapter adapter;
    private List<WorkoutGroup> workoutGroups;
    private WorkoutGroupDao groupDao;
    private PassDataWorkoutListener listener;
    private Context context;
    private RecyclerView rcv;

    WorkoutAsyncTask(Context context, RecyclerView rcv, PassDataWorkoutListener listener) {
        this.context = context;
        this.rcv = rcv;
        this.listener = listener;
        groupDao = AppDatabase.getInstance(context).WorkoutGroupDao();
    }

    @Override
    protected Void doInBackground(Integer... integers) {
//        workoutGroups = groupDao.findWorkoutGroupByPurcharse(integers[0]);TODO
        SharedPreferences sharedPreferences = SharePreferencesManager.getInstance(getApplicationContext());
        int idUser = sharedPreferences.getInt(AppKey.KEY_USER_ID, 0);
        if(integers[0] == 1){
            workoutGroups = groupDao.getWorkoutGroupSelect(idUser);
        }else {
            workoutGroups = groupDao.getWorkoutGroupUnSelect(idUser);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        adapter = new WorkoutGroupAdapter(context, workoutGroups, listener);
        rcv.setAdapter(adapter);
        rcv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        super.onPostExecute(aVoid);
    }
}
