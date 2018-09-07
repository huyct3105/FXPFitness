package vn.viviu.fxpfitnesshulahoop.ui.setting;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import vn.viviu.fxpfitnesshulahoop.R;
import vn.viviu.fxpfitnesshulahoop.data.database.AppDatabase;
import vn.viviu.fxpfitnesshulahoop.data.database.entity.WorkoutGroup;
import vn.viviu.fxpfitnesshulahoop.ui.login.SharePreferencesManager;
import vn.viviu.fxpfitnesshulahoop.util.AppKey;

import static com.facebook.FacebookSdk.getApplicationContext;


public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.ViewHolder> {
    private List<WorkoutGroup> settings;
    private Context context;
    private int FLAG_SETTING;
    int idUser;

    public SettingAdapter(Context context, List<WorkoutGroup> settings, int FLAG_SETTING) {
        this.settings = settings;
        this.context = context;
        this.FLAG_SETTING = FLAG_SETTING;
        SharedPreferences sharedPreferences = SharePreferencesManager.getInstance(getApplicationContext());
        idUser = sharedPreferences.getInt(AppKey.KEY_USER_ID, 0);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_setting, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final WorkoutGroup setting = settings.get(position);
        holder.tvSetting.setText(setting.getWorkoutgroupName());

        if (FLAG_SETTING == 0) {
            holder.btnReset.setText("Đặt lại");
        } else {
            holder.btnReset.setText("Hủy bỏ");
        }
        holder.btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FLAG_SETTING == 0) {
                    AppDatabase db = AppDatabase.getInstance(context.getApplicationContext());
                    db.userWorkoutGroupDao().UpdateWorkoutGroupReset(idUser,setting.getIdWorkoutGroup());
                    settings.clear();
                    settings.addAll(db.WorkoutGroupDao().getAllWorkoutGroupsReset(idUser));
                    notifyDataSetChanged();
                } else {
                    AppDatabase db = AppDatabase.getInstance(context.getApplicationContext());
                    db.userWorkoutGroupDao().UpdateWorkoutGroupRestore(idUser,setting.getIdWorkoutGroup());
                    settings.clear();
                    settings.addAll(db.WorkoutGroupDao().getWorkoutGroupSelect(idUser));
                    notifyDataSetChanged();
                }//TODO
            }
        });
    }

    @Override
    public int getItemCount() {
        return settings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvSetting;
        private Button btnReset;

        public ViewHolder(View itemView) {
            super(itemView);
            tvSetting = itemView.findViewById(R.id.textview_setting_name);
            btnReset = itemView.findViewById(R.id.button_setting_reset);
        }
    }
}
