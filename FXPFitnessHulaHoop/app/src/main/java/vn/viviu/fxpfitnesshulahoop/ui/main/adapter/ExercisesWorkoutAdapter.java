package vn.viviu.fxpfitnesshulahoop.ui.main.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vn.viviu.fxpfitnesshulahoop.R;
import vn.viviu.fxpfitnesshulahoop.data.database.dao.WorkoutExercisesDao;
import vn.viviu.fxpfitnesshulahoop.data.database.entity.Exercises;
import vn.viviu.fxpfitnesshulahoop.ui.main.listener.IPushExercises;

/**
 * Created by PHAMHOAN on 3/6/2018.
 */

public class ExercisesWorkoutAdapter extends RecyclerView.Adapter<ExercisesWorkoutAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<WorkoutExercisesDao.ExercisesW> exercisesList;
    IPushExercises iPushExercises;

    public ExercisesWorkoutAdapter(Context context, List<WorkoutExercisesDao.ExercisesW> exercisesList, IPushExercises iPushExercises) {
        this.context = context;
        this.exercisesList = exercisesList;
        layoutInflater = LayoutInflater.from(context);
        this.iPushExercises = iPushExercises;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_exercises_workout,parent,false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final WorkoutExercisesDao.ExercisesW exercises = exercisesList.get(position);
        holder.txtTitle.setText(exercises.getExerciseName());
        holder.txtContent.setText(exercises.getFocus());
        holder.txtRep.setText(exercises.getSetsReps());
        String uri = "icon"+(exercises.getIdExcercises());
        int resID = context.getResources().getIdentifier(uri,"drawable",context.getPackageName());
        Drawable image = context.getResources().getDrawable(resID);
        holder.imgExercises.setImageDrawable(image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPushExercises.PushIDExercises(exercises.getIdExcercises());
            }
        });
        holder.imgVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPushExercises.PushLink(exercises.getLink());
            }
        });

    }

    @Override
    public int getItemCount() {
        return exercisesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgExercises, imgVideo;
        private TextView txtTitle,txtContent,txtRep;

        public ViewHolder(View itemView) {
            super(itemView);
            imgExercises = itemView.findViewById(R.id.img_exercise);
            txtTitle  = itemView.findViewById(R.id.txt_title_exercise);
            txtContent = itemView.findViewById(R.id.txt_content_exercise);
            txtRep = itemView.findViewById(R.id.txt_rep);
            imgVideo = itemView.findViewById(R.id.img_video);
        }
    }
}
