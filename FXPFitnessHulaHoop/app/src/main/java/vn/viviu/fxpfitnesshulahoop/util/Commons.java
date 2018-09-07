package vn.viviu.fxpfitnesshulahoop.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import vn.viviu.fxpfitnesshulahoop.R;
import vn.viviu.fxpfitnesshulahoop.ui.exercises.ExercisesDetailsFragment;
import vn.viviu.fxpfitnesshulahoop.ui.exercises.ExercisesFragment;
import vn.viviu.fxpfitnesshulahoop.ui.main.ExercisesWorkoutFragment;
import vn.viviu.fxpfitnesshulahoop.ui.main.MainFragment;
import vn.viviu.fxpfitnesshulahoop.ui.main.MainWorkoutsFragment;
import vn.viviu.fxpfitnesshulahoop.ui.main.PremiumWorkoutFragment;
import vn.viviu.fxpfitnesshulahoop.ui.main.PremiumWorkoutsFragment;
import vn.viviu.fxpfitnesshulahoop.ui.main.SelectWorkoutFragment;
import vn.viviu.fxpfitnesshulahoop.ui.nutrition.NutritionDetailsFragment;
import vn.viviu.fxpfitnesshulahoop.ui.nutrition.NutritionFragment;
import vn.viviu.fxpfitnesshulahoop.ui.setting.SettingFragment;
import vn.viviu.fxpfitnesshulahoop.ui.workoutcomplete.WorkOutCompleteFragment;



public final class Commons {

    private Commons() {

    }

    public static void replaceFragment(FragmentManager fragmentManager, String tag, int id) {
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment == null) {
            fragmentManager.beginTransaction().replace(id,createFragmentByTag(tag),tag).addToBackStack(null).commit();
        } else {
            fragmentManager.beginTransaction().replace(id, fragment,tag).addToBackStack(null).commit();
        }

        fragmentManager.executePendingTransactions();
    }

    public static Fragment createFragmentByTag(String tag) {
        switch (tag) {
            case AppKey.KEY_HOME:
                return new MainFragment();
            case AppKey.KEY_NUTRITION:
                return new NutritionFragment();
            case AppKey.KEY_NUTRITION_DETAIL:
                return new NutritionDetailsFragment();
            case AppKey.KEY_EXERCISES:
                return new ExercisesFragment();
            case AppKey.KEY_EXERCISES_DETAIL:
                return new ExercisesDetailsFragment();
            case AppKey.KEY_SETTING:
                return new SettingFragment();
            case AppKey.KEY_PREMIUM_WORKOUT:
                return new PremiumWorkoutFragment();
            case AppKey.KEY_PREMIUM_WORKOUTS:
                return new PremiumWorkoutsFragment();
            case AppKey.KEY_MAIN_PREMIUM:
                return new MainWorkoutsFragment();
            case AppKey.KEY_SELECT_WORKOUT:
                return new SelectWorkoutFragment();
            case AppKey.KEY_SELECT_WORKOUT_EXERCISES:
                return new ExercisesWorkoutFragment();
            default:
                break;
        }
        return null;
    }

    public static boolean checkInternetConnection(Context context) {
        final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connMgr.getActiveNetworkInfo();

        if (activeNetworkInfo != null) { // connected to the internet
            Toast.makeText(context, activeNetworkInfo.getTypeName(), Toast.LENGTH_SHORT).show();

            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                return true;
            } else if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to the mobile provider's data plan
                return true;
            }
        }
        return false;
    }

    private static boolean isPackageExisted(String targetPackage, Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(targetPackage, PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
        return true;
    }

    public static Intent getTwitterIntent(Context context, String shareText) {
        Intent shareIntent;

        if (isPackageExisted("com.twitter.android", context)) {
            shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setClassName("com.twitter.android",
                    "com.twitter.android.PostActivity");
            shareIntent.setType("text/*");
            shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareText);
            return shareIntent;
        } else {
            String tweetUrl = "https://twitter.com/intent/tweet?text=" + shareText;
            Uri uri = Uri.parse(tweetUrl);
            shareIntent = new Intent(Intent.ACTION_VIEW, uri);
            return shareIntent;
        }
    }
}
