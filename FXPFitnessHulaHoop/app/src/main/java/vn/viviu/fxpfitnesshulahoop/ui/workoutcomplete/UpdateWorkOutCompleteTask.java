package vn.viviu.fxpfitnesshulahoop.ui.workoutcomplete;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import java.lang.ref.WeakReference;

import vn.viviu.fxpfitnesshulahoop.data.database.AppDatabase;
import vn.viviu.fxpfitnesshulahoop.ui.login.SharePreferencesManager;
import vn.viviu.fxpfitnesshulahoop.util.AppKey;

import static com.facebook.FacebookSdk.getApplicationContext;


public class UpdateWorkOutCompleteTask extends AsyncTask<Integer, Void, String> {
    private WeakReference<WorkOutCompleteFragment> fragmentRef;


    public UpdateWorkOutCompleteTask(WorkOutCompleteFragment workOutCompleteFragment) {
        fragmentRef = new WeakReference<WorkOutCompleteFragment>(workOutCompleteFragment);
    }

    @Override
    protected String doInBackground(Integer... integers) {
        int dayComplete = integers[0];
        int workOutId = integers[1];
        int workOutGroupId = integers[2];
        SharedPreferences sharedPreferences = SharePreferencesManager.getInstance(getApplicationContext());
        int idUser = sharedPreferences.getInt(AppKey.KEY_USER_ID, 0);
        AppDatabase appDatabase = AppDatabase.getInstance(fragmentRef.get().getActivity().getApplicationContext());

        if (appDatabase != null) {

            int result = appDatabase.userWorkoutGroupDao().updateWorkoutGroupCurrentDate(idUser,workOutGroupId,dayComplete);
            if (result != 0) {

                 String complelteDescription = appDatabase.DateWorkoutDao().getWorkOutCompleteDescription(workOutId);
                return complelteDescription;
            }//TODO
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        if (s != null) {
            fragmentRef.get().setTextCompleteDescription(s);
        }
    }
}
