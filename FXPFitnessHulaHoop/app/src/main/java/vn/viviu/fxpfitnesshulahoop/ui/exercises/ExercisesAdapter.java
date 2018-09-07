package vn.viviu.fxpfitnesshulahoop.ui.exercises;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.viviu.fxpfitnesshulahoop.R;
import vn.viviu.fxpfitnesshulahoop.data.database.entity.Exercises;



public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.RecycleViewHolder> {
    private List<Exercises> exercisesList;
    private Context context;
    private AdapterExercisesCallBack adapterExercisesCallBack;

    public ExercisesAdapter(List<Exercises> exercisesList, Context context, AdapterExercisesCallBack adapterExercisesCallBack) {
        this.exercisesList = exercisesList;
        this.context = context;
        this.adapterExercisesCallBack = adapterExercisesCallBack;
    }

    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_exercises, parent, false);
        return new RecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecycleViewHolder holder, final int position) {
        final Exercises exercises = exercisesList.get(position);
        holder.txt_title.setText(exercises.getExerciseName());
        holder.txt_content.setText(exercises.getExerciseDescription());
        String uri = "icon" + exercises.getIdExcercises();
        int resID = context.getResources().getIdentifier(uri, "drawable", context.getPackageName());
        Drawable image = context.getResources().getDrawable(resID);
        holder.img_exercises.setImageDrawable(image);
        holder.ln_item_exercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapterExercisesCallBack != null) {
                    adapterExercisesCallBack.onItemClick(exercises, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return exercisesList.size();
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_title, txt_content;
        public ImageView img_exercises;
        public LinearLayout ln_item_exercises;

        public RecycleViewHolder(View itemView) {
            super(itemView);
            txt_title = itemView.findViewById(R.id.txt_title_exercise);
            txt_content = itemView.findViewById(R.id.txt_content_exercise);
            img_exercises = itemView.findViewById(R.id.img_exercise);
            ln_item_exercises = itemView.findViewById(R.id.ln_item_exercises);
        }
    }
}
