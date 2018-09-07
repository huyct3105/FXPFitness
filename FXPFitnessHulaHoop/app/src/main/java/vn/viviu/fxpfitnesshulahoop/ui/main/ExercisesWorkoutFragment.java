package vn.viviu.fxpfitnesshulahoop.ui.main;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import vn.viviu.fxpfitnesshulahoop.MainActivity;
import vn.viviu.fxpfitnesshulahoop.R;
import vn.viviu.fxpfitnesshulahoop.data.database.AppDatabase;
import vn.viviu.fxpfitnesshulahoop.data.database.dao.WorkoutExercisesDao;
import vn.viviu.fxpfitnesshulahoop.data.database.entity.Exercises;
import vn.viviu.fxpfitnesshulahoop.ui.exercises.ExerciseVideo;
import vn.viviu.fxpfitnesshulahoop.ui.exercises.ExercisesDetailsFragment;
import vn.viviu.fxpfitnesshulahoop.ui.main.adapter.ExercisesWorkoutAdapter;
import vn.viviu.fxpfitnesshulahoop.ui.main.listener.IPushExercises;
import vn.viviu.fxpfitnesshulahoop.ui.workoutcomplete.WorkOutCompleteFragment;
import vn.viviu.fxpfitnesshulahoop.util.AppKey;

/**
 * Created by PHAMHOAN on 3/6/2018.
 */

public class ExercisesWorkoutFragment extends Fragment implements View.OnClickListener, IPushExercises {
    @BindView(R.id.txt_select_workout)
    TextView txtSelectWorkout;
    @BindView(R.id.rcv_workout)
    RecyclerView rcvWorkout;
    Unbinder unbinder;
    @BindView(R.id.imv_back)
    ImageView imvBack;
    @BindView(R.id.btn_complete)
    Button btnComplete;
    private int idWorkout;
    private List<WorkoutExercisesDao.ExercisesW> exercisesWList;
    private LinearLayoutManager llm;
    private ExercisesWorkoutAdapter exercisesAdapter;
    int idWorkoutGroup;
    int currentDay;
    private InteractionWithExercisesWorkout interaction;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        interaction = (InteractionWithExercisesWorkout) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercises_workout, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {

            idWorkout = bundle.getInt(AppKey.KEY_ID_WORKOUT);

            idWorkoutGroup = bundle.getInt(AppKey.KEY_WORKOUT_GROUP);
            currentDay = bundle.getInt(AppKey.KEY_WORKOUTS_CURENTDAY);
        }
    }

    private void initialize() {


        btnComplete.setOnClickListener(this);
        imvBack.setOnClickListener(this);
        exercisesWList = new ArrayList<>();
        AppDatabase db = AppDatabase.getInstance(getActivity().getApplicationContext());
        exercisesWList = db.WorkoutExercisesDao().getExercisesByWO(idWorkout);
        txtSelectWorkout.setText(db.WorkoutDao().getWorkoutNameByID(idWorkout).toString());
        llm = new LinearLayoutManager(getContext());
        rcvWorkout.setLayoutManager(llm);
        exercisesAdapter = new ExercisesWorkoutAdapter(getContext(), exercisesWList, this);
        rcvWorkout.setAdapter(exercisesAdapter);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imv_back:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.btn_complete:
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container, WorkOutCompleteFragment.newInstance(currentDay, idWorkout, idWorkoutGroup));
                transaction.addToBackStack(null);
                transaction.commit();
            default:
                break;
        }
    }

    @Override
    public void PushIDExercises(int idExercises) {
        interaction.setBottomBarExercise(idExercises);


    }

    @Override
    public void PushLink(String link) {
        Intent intent = new Intent(getContext(), ExerciseVideo.class);
        intent.putExtra("link", link);
        startActivity(intent);
    }

    public interface InteractionWithExercisesWorkout{

        void setBottomBarExercise(int idExercises);
    }
}
