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
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import vn.viviu.fxpfitnesshulahoop.R;
import vn.viviu.fxpfitnesshulahoop.data.database.entity.WorkoutGroup;
import vn.viviu.fxpfitnesshulahoop.ui.main.listener.PassDataWorkoutListener;
import vn.viviu.fxpfitnesshulahoop.util.AppKey;

public class PremiumWorkoutsFragment extends Fragment implements PassDataWorkoutListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.list_workouts_premium)
    RecyclerView listWorkoutsPremium;
    @BindView(R.id.title_toolbar)
    TextView titleToolbar;

    Unbinder unbinder;

    public PremiumWorkoutsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_premium_workouts, container, false);
        unbinder = ButterKnife.bind(this, view);
        assert getActivity() != null;
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String title = "Thư viện";
        titleToolbar.setText(title);

        new WorkoutAsyncTask(getContext(), listWorkoutsPremium, this).execute(0);
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
        PremiumWorkoutFragment fragment = new PremiumWorkoutFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(AppKey.KEY_WORKOUT, group);
        fragment.setArguments(bundle);
        transaction.replace(R.id.frame_container, fragment, AppKey.KEY_PREMIUM_WORKOUT);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
