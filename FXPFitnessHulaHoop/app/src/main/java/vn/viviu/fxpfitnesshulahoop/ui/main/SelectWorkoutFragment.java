package vn.viviu.fxpfitnesshulahoop.ui.main;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.shawnlin.numberpicker.NumberPicker;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import vn.viviu.fxpfitnesshulahoop.R;
import vn.viviu.fxpfitnesshulahoop.data.database.AppDatabase;
import vn.viviu.fxpfitnesshulahoop.data.database.dao.DateWorkoutDao;
import vn.viviu.fxpfitnesshulahoop.data.database.entity.WorkoutGroup;
import vn.viviu.fxpfitnesshulahoop.ui.login.SharePreferencesManager;
import vn.viviu.fxpfitnesshulahoop.util.AppKey;

import static com.facebook.FacebookSdk.getApplicationContext;

public class SelectWorkoutFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.txt_select_workout)
    TextView txtSelectWorkout;
    @BindView(R.id.txt_one)
    TextView txtOne;
    @BindView(R.id.txt_two)
    TextView txtTwo;
    @BindView(R.id.txt_three)
    TextView txtThree;
    @BindView(R.id.imv_back)
    ImageView imvBack;
    @BindView(R.id.lnl_seekbar)
    LinearLayout lnlSeekbar;
    private int maxDay, currentDay, idWorkoutGroup, currentMustWork;
    private AppDatabase db;
    @BindView(R.id.seekbar1)
    SeekBar seekbar;
    @BindView(R.id.number_picker)
    NumberPicker numberPicker;
    @BindView(R.id.title_select_workout)
    TextView titleSelectWorkout;
    @BindView(R.id.subtitle_select_workout)
    TextView subtitleSelectWorkout;
    @BindView(R.id.ll_title_select_workout)
    LinearLayout llTitleSelectWorkout;
    @BindView(R.id.btn_start_workout)
    Button btnStartWorkout;
    Unbinder unbinder;

    List<DateWorkoutDao.DateWorkoutTitle> listWorkout;
    int idUser;

    public SelectWorkoutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_workout, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnStartWorkout.setOnClickListener(this);
        imvBack.setOnClickListener(this);
        Bundle bundle = getArguments();
        WorkoutGroup workoutGroup = (WorkoutGroup) bundle.getSerializable(AppKey.KEY_WORKOUT);


        idWorkoutGroup = workoutGroup.getIdWorkoutGroup();
        SharedPreferences sharedPreferences = SharePreferencesManager.getInstance(getApplicationContext());
        idUser = sharedPreferences.getInt(AppKey.KEY_USER_ID, 0);

        listWorkout = new ArrayList<>();
        updateData(idWorkoutGroup);
        initialize();
    }

    private void initialize() {
        int temp = maxDay / 3;
        if (temp > 1) {
            txtOne.setText("1-" + maxDay / 3);
            txtTwo.setText(((maxDay / 3) + 1) + "-" + 2 * maxDay / 3);
            txtThree.setText((2 * maxDay / 3) + 1 + "-" + maxDay);
        } else if (temp == 1) {
            txtOne.setText((maxDay / 3) + "");
            txtTwo.setText(((maxDay / 3) + 1) + "");
            txtThree.setText(maxDay + "");
        } else {
            txtOne.setText(maxDay + "");
            txtTwo.setText(maxDay + "");
            txtThree.setText(maxDay + "");
        }
        seekbar.setMax(maxDay - 1);
        seekbar.setProgress(currentDay - 1);
        numberPicker.setMaxValue(maxDay);
        numberPicker.setMinValue(1);
        numberPicker.setValue(currentDay);
        numberPicker.setWrapSelectorWheel(false);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                numberPicker.setValue(progress + 1);
                currentDay = progress + 1;
                titleSelectWorkout.setText(listWorkout.get(progress).getWorkoutTitle().toString());
                subtitleSelectWorkout.setText(listWorkout.get(progress).getDescription().toString());

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                seekbar.setProgress(numberPicker.getValue() - 1);
                currentDay = numberPicker.getValue();
                titleSelectWorkout.setText(listWorkout.get(numberPicker.getValue() - 1).getWorkoutTitle().toString());
                subtitleSelectWorkout.setText(listWorkout.get(numberPicker.getValue() - 1).getDescription().toString());


            }
        });


    }

    private void updateData(int idWG) {
        AppDatabase db = AppDatabase.getInstance(getActivity().getApplicationContext());
        listWorkout = db.DateWorkoutDao().getListWorkoutByDate(idWG);

        maxDay = db.DateWorkoutDao().getCountListWorkoutByDate(idWG);
        WorkoutGroup workoutGroup = db.WorkoutGroupDao().getWorkoutGroupById(idWG);
        txtSelectWorkout.setText(workoutGroup.getWorkoutgroupName().toString());
        //TODO
        int curr = db.userWorkoutGroupDao().getCurrentDate(idUser, workoutGroup.getIdWorkoutGroup());
        if (curr < maxDay) {
            currentDay = 1 + curr;
        } else {
            currentDay = maxDay;
        }
        currentMustWork = currentDay;
        titleSelectWorkout.setText(listWorkout.get(currentDay - 1).getWorkoutTitle().toString());
        subtitleSelectWorkout.setText(listWorkout.get(currentDay - 1).getDescription().toString());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_workout:
                if (currentDay > currentMustWork) {
                    Toast.makeText(getActivity(), "Bạn chưa hoàn thành bài tập ngày " + currentMustWork, Toast.LENGTH_SHORT).show();
                    numberPicker.setValue(currentMustWork);
                    seekbar.setProgress(currentMustWork -1);
                    currentDay = currentMustWork;
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putInt(AppKey.KEY_WORKOUT_GROUP, idWorkoutGroup);
                    bundle.putInt(AppKey.KEY_WORKOUTS_CURENTDAY, currentDay);
                    bundle.putInt(AppKey.KEY_ID_WORKOUT, listWorkout.get(currentDay - 1).getIdWorkout());
                    ExercisesWorkoutFragment exercisesWorkoutFragment = new ExercisesWorkoutFragment();
                    exercisesWorkoutFragment.setArguments(bundle);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_container, exercisesWorkoutFragment).addToBackStack(null);
                    transaction.commit();

                }
                break;
            case R.id.imv_back:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            default:
                break;
        }
    }
}
