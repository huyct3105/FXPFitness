package vn.viviu.fxpfitnesshulahoop.ui.setting;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import vn.viviu.fxpfitnesshulahoop.R;
import vn.viviu.fxpfitnesshulahoop.data.database.AppDatabase;
import vn.viviu.fxpfitnesshulahoop.data.database.entity.WorkoutGroup;
import vn.viviu.fxpfitnesshulahoop.ui.login.SharePreferencesManager;
import vn.viviu.fxpfitnesshulahoop.util.AppKey;

import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {
    @BindView(R.id.img_log_out)
    ImageView imgLogOut;
    Unbinder unbinder;
    private RecyclerView rcvSetting;
    private List<WorkoutGroup> settings;
    private SettingAdapter adapter;
    private RadioGroup radioGroup;
    private View line;
    int idUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvSetting = view.findViewById(R.id.rcvSetting);
        radioGroup = view.findViewById(R.id.radioGroupSetting);
        line = view.findViewById(R.id.line_setting);
        SharedPreferences sharedPreferences = SharePreferencesManager.getInstance(getApplicationContext());
        idUser = sharedPreferences.getInt(AppKey.KEY_USER_ID, 0);
        settings = new ArrayList<>();
        AppDatabase db = AppDatabase.getInstance(getActivity().getApplicationContext());
        settings = db.WorkoutGroupDao().getAllWorkoutGroupsReset(idUser);
        adapter = new SettingAdapter(getContext(), settings, 0);
        rcvSetting.setHasFixedSize(true);
        rcvSetting.setLayoutManager(new LinearLayoutManager(getContext()));
        rcvSetting.setAdapter(adapter);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rdReset:
                        settings = new ArrayList<>();
                        AppDatabase db = AppDatabase.getInstance(getActivity().getApplicationContext());
                        settings = db.WorkoutGroupDao().getAllWorkoutGroupsReset(idUser);
                        adapter = new SettingAdapter(getContext(), settings, 0);
                        rcvSetting.setHasFixedSize(true);
                        rcvSetting.setLayoutManager(new LinearLayoutManager(getContext()));
                        rcvSetting.setAdapter(adapter);
                        break;
                    case R.id.rdRestore:
                        settings = new ArrayList<>();
                        AppDatabase db1 = AppDatabase.getInstance(getActivity().getApplicationContext());
                        settings = db1.WorkoutGroupDao().getWorkoutGroupSelect(idUser);
                        adapter = new SettingAdapter(getContext(), settings, 1);
                        rcvSetting.setHasFixedSize(true);
                        rcvSetting.setLayoutManager(new LinearLayoutManager(getContext()));
                        rcvSetting.setAdapter(adapter);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.img_log_out)
    public void onViewClicked() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(AppKey.PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(AppKey.USER_NAME, "");
        editor.putString(AppKey.USER_PASS, "");
        editor.apply();
        getActivity().finish();
    }
}
