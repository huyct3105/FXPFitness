package vn.viviu.fxpfitnesshulahoop.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import vn.viviu.fxpfitnesshulahoop.R;
import vn.viviu.fxpfitnesshulahoop.data.database.AppDatabase;
import vn.viviu.fxpfitnesshulahoop.data.database.dao.WorkoutGroupDao;
import vn.viviu.fxpfitnesshulahoop.data.database.entity.WorkoutGroup;
import vn.viviu.fxpfitnesshulahoop.ui.main.listener.PassDataWorkoutListener;
import vn.viviu.fxpfitnesshulahoop.util.AppKey;

public class MainWorkoutsFragment extends Fragment implements PassDataWorkoutListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar_main_workouts;
    @BindView(R.id.list_workouts_main)
    RecyclerView listWorkoutsMain;
    Unbinder unbinder;

    WorkoutGroupDao workoutGroupDao;

    public MainWorkoutsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_workouts, container, false);
        unbinder = ButterKnife.bind(this, view);
        assert getActivity() != null;
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar_main_workouts);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Tập luyện");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        workoutGroupDao = AppDatabase.getInstance(getActivity()).WorkoutGroupDao();
        new WorkoutAsyncTask(getContext(), listWorkoutsMain, this).execute(1);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Log.d("Option Fragment", "Call");
            getFragmentManager().popBackStack();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDataPass(WorkoutGroup group) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        SelectWorkoutFragment fragment = new SelectWorkoutFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(AppKey.KEY_WORKOUT, group);
        fragment.setArguments(bundle);
        transaction.replace(R.id.frame_container, fragment, "select");
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
