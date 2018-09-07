package vn.viviu.fxpfitnesshulahoop.ui.main.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import vn.viviu.fxpfitnesshulahoop.R;
import vn.viviu.fxpfitnesshulahoop.data.database.entity.WorkoutGroup;
import vn.viviu.fxpfitnesshulahoop.ui.main.listener.PassDataWorkoutListener;


public class WorkoutGroupAdapter extends RecyclerView.Adapter<WorkoutGroupAdapter.WorkoutGroupHolder> {
    private Context context;
    private List<WorkoutGroup> groups;
    private PassDataWorkoutListener listener;

    public WorkoutGroupAdapter(Context context, List<WorkoutGroup> groups, PassDataWorkoutListener listener) {
        this.context = context;
        this.groups = groups;
        this.listener = listener;
    }

    @Override
    public WorkoutGroupHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_workouts, parent, false);
        return new WorkoutGroupHolder(view);
    }

    @Override
    public void onBindViewHolder(WorkoutGroupHolder holder, int position) {
        final WorkoutGroup group = groups.get(position);
        holder.workoutTitleName.setText(group.getWorkoutgroupName());

        holder.workoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDataPass(group);
            }
        });
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    public void updateAdapter(List<WorkoutGroup> groups) {
        this.groups = groups;
        notifyDataSetChanged();
    }

    class WorkoutGroupHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.avt_workouts)
        CircleImageView avtWorkouts;
        @BindView(R.id.workout_title_name)
        TextView workoutTitleName;
        @BindView(R.id.workout_item)
        ConstraintLayout workoutItem;

        WorkoutGroupHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
