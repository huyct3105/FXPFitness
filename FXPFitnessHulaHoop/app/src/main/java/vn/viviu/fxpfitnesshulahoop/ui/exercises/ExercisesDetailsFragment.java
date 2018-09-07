package vn.viviu.fxpfitnesshulahoop.ui.exercises;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import vn.viviu.fxpfitnesshulahoop.R;
import vn.viviu.fxpfitnesshulahoop.data.database.AppDatabase;
import vn.viviu.fxpfitnesshulahoop.data.database.entity.Exercises;
import vn.viviu.fxpfitnesshulahoop.util.AppKey;



public class ExercisesDetailsFragment extends Fragment {
    private TextView txt_title_exercises_details, txt_des_exerises_detail, txt_mus_exercises_detail, txt_note_exercises_detail, txt_forcus_exercises_detail, txt_title_details;
    private ImageView img_exercises_detail, img_back;
    private Exercises exercises;
    private Button btn_right, btn_left, btn_video;
    private List<Exercises> exercisesList;
    private int position, idExercises = -1;
    private Bundle bundle;
    private InteractionWithExercisesDetail interaction;
    private boolean checkSet = false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        interaction = (InteractionWithExercisesDetail) context;
    }

    public void setCheckSet(boolean checkSet) {
        this.checkSet = checkSet;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_exercises_details, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        exercisesList = new ArrayList<>();
        bundle = getArguments();
        position = bundle.getInt(AppKey.KEY_EXERCISES_WORKOUT);
        idExercises = bundle.getInt(AppKey.KEY_EXERCISES_WORKOUT);
        AppDatabase db = AppDatabase.getInstance(getActivity().getApplicationContext());
        exercisesList = db.ExercisesDao().getExercisesTitle();
        exercises = exercisesList.get(position);
        txt_title_exercises_details = view.findViewById(R.id.txt_title_exercise_details);
        txt_des_exerises_detail = view.findViewById(R.id.txt_description_exercise_detail);
        txt_mus_exercises_detail = view.findViewById(R.id.txt_muscle_exercise_detail);
        txt_note_exercises_detail = view.findViewById(R.id.txt_notes_exercise_detail);
        txt_forcus_exercises_detail = view.findViewById(R.id.txt_focus_exercise_detail);
        txt_title_details = view.findViewById(R.id.txt_title_details);
        img_exercises_detail = view.findViewById(R.id.img_exercise_detail);
        img_back = view.findViewById(R.id.img_back_exercisesdetails);
        btn_right = view.findViewById(R.id.btn_right);
        btn_left = view.findViewById(R.id.btn_left);
        btn_video = view.findViewById(R.id.btn_video_exercise);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (idExercises != -1 && checkSet) {
                    interaction.setBottomBarMain();
                    getActivity().getSupportFragmentManager().popBackStack();
                } else {
                    getActivity().getSupportFragmentManager().popBackStack();
                }


            }
        });

        setData();
        nextData();
        btn_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String video = exercises.getLink();
                Intent intent = new Intent(getContext(), ExerciseVideo.class);
                intent.putExtra("link", video);
                startActivity(intent);
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

    private void nextData() {
        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position++;
                if (position < exercisesList.size()) {
                    if (btn_left.getVisibility() == View.INVISIBLE) {
                        btn_left.setVisibility(View.VISIBLE);
                    }
                    exercises = exercisesList.get(position);
                    setData();
                } else {
                    btn_right.setVisibility(View.INVISIBLE);
                    position = exercisesList.size() - 1;
                }
            }
        });
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position--;
                if (0 <= position) {
                    if (btn_right.getVisibility() == View.INVISIBLE) {
                        btn_right.setVisibility(View.VISIBLE);
                    }
                    exercises = exercisesList.get(position);
                    setData();
                } else {
                    btn_left.setVisibility(View.INVISIBLE);
                    position = 0;
                }
            }
        });
    }

    private void setData() {
        txt_title_details.setText(exercises.getExerciseName());
        txt_title_exercises_details.setText(exercises.getExerciseName());
        txt_des_exerises_detail.setText("Mô tả: \n" + exercises.getExerciseDescription());
        txt_note_exercises_detail.setText("Chú ý: \n" + exercises.getExerciseNotes());
        txt_mus_exercises_detail.setText("Nơi tác động: \n" + exercises.getMuscle());
        txt_forcus_exercises_detail.setText("Lời nhắc:  \n" + exercises.getFocus());
        String uri = "thumb" + exercises.getIdExcercises();
        int resID = getContext().getResources().getIdentifier(uri, "drawable", getContext().getPackageName());
        Drawable image = getContext().getResources().getDrawable(resID);
        img_exercises_detail.setImageDrawable(image);
    }

    public interface  InteractionWithExercisesDetail{

        void setBottomBarMain();
    }
}
