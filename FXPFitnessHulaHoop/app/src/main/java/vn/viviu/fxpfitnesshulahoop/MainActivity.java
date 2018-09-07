package vn.viviu.fxpfitnesshulahoop;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.viviu.fxpfitnesshulahoop.ui.exercises.ExercisesDetailsFragment;
import vn.viviu.fxpfitnesshulahoop.ui.exercises.ExercisesFragment;
import vn.viviu.fxpfitnesshulahoop.ui.main.ExercisesWorkoutFragment;
import vn.viviu.fxpfitnesshulahoop.ui.main.MainFragment;
import vn.viviu.fxpfitnesshulahoop.ui.nutrition.NutritionFragment;
import vn.viviu.fxpfitnesshulahoop.ui.setting.SettingFragment;

import vn.viviu.fxpfitnesshulahoop.ui.workoutcomplete.WorkOutCompleteFragment;
import vn.viviu.fxpfitnesshulahoop.util.AppKey;
import vn.viviu.fxpfitnesshulahoop.util.Commons;

public class MainActivity extends AppCompatActivity implements ExercisesWorkoutFragment.InteractionWithExercisesWorkout,
        ExercisesDetailsFragment.InteractionWithExercisesDetail{

    @BindView(R.id.bottom_navigation)
    BottomNavigationViewEx mBottomNav;
    FragmentTransaction transaction;
    private boolean isGoDetailFragment = false;
    private int idExercises;
    private boolean isMainBack = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mBottomNav.enableShiftingMode(false);
        mBottomNav.enableAnimation(false);
        Commons.replaceFragment(getSupportFragmentManager(),AppKey.KEY_HOME,R.id.frame_container);

        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_home:
                        if (isMainBack == true){
                            isMainBack = false;

                        }else {
                            Commons.replaceFragment(getSupportFragmentManager(),AppKey.KEY_HOME,R.id.frame_container);
                        }
                        break;
                    case R.id.item_exercises:
                        if (isGoDetailFragment) {
                            isGoDetailFragment = false;
                            ExercisesDetailsFragment detailsFragment1 = new ExercisesDetailsFragment();
                            detailsFragment1.setCheckSet(true);
                            FragmentManager manager = getSupportFragmentManager();
                            FragmentTransaction transaction = manager.beginTransaction();
                            Bundle bundle = new Bundle();
                            bundle.putInt(AppKey.KEY_EXERCISES_WORKOUT, idExercises -1);
                            detailsFragment1.setArguments(bundle);
                            transaction.replace(R.id.frame_container, detailsFragment1);
                            transaction.addToBackStack(null);
                            transaction.commit();

                        } else {
                            Commons.replaceFragment(getSupportFragmentManager(),AppKey.KEY_EXERCISES,R.id.frame_container);
                        }
                        break;
                    case R.id.item_nutrition:
                        Commons.replaceFragment(getSupportFragmentManager(),AppKey.KEY_NUTRITION,R.id.frame_container);
                        break;
                    case R.id.item_setting:
                        Commons.replaceFragment(getSupportFragmentManager(),AppKey.KEY_SETTING,R.id.frame_container);
                        break;
                }
                return true;
            }
        });


    }

    @Override
    public void onBackPressed() {

        if (mBottomNav.getCurrentItem() == 0) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            MainFragment fragment = (MainFragment) fragmentManager.findFragmentByTag(AppKey.KEY_HOME);
            Log.d("ABC", "onBackPressed: " + fragment.getTag());
            if (!fragment.isVisible()) {
                mBottomNav.setCurrentItem(0);
            } else {
                finish();
            }

        } else {
            mBottomNav.setCurrentItem(0);
        }


    }

    @Override
    public void setBottomBarExercise(int idExercises) {
        isGoDetailFragment = true;
        this.idExercises = idExercises;
        mBottomNav.setCurrentItem(1);

    }

    @Override
    public void setBottomBarMain() {
        isMainBack = true;
        mBottomNav.setCurrentItem(0);

    }
}
