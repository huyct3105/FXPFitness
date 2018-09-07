package vn.viviu.fxpfitnesshulahoop.ui.exercises;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import vn.viviu.fxpfitnesshulahoop.R;
import vn.viviu.fxpfitnesshulahoop.data.database.AppDatabase;
import vn.viviu.fxpfitnesshulahoop.data.database.entity.Exercises;
import vn.viviu.fxpfitnesshulahoop.data.database.entity.Nutrition;
import vn.viviu.fxpfitnesshulahoop.ui.nutrition.NutritionDetailsFragment;
import vn.viviu.fxpfitnesshulahoop.util.AppKey;

public class ExercisesFragment extends Fragment implements AdapterExercisesCallBack {
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private ExercisesAdapter adapter;
    private List<Exercises> exercisesList = new ArrayList<>();
    private AppDatabase db;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_exercises, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycle_view_exercises);
        layoutManager = new LinearLayoutManager(this.getActivity());
        db = AppDatabase.getInstance(getActivity().getApplicationContext());
        exercisesList = db.ExercisesDao().getExercisesTitle();
        adapter = new ExercisesAdapter(exercisesList, getContext(), this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onItemClick(Exercises exercises, int position) {
        ExercisesDetailsFragment detailsFragment = new ExercisesDetailsFragment();
        manager = getFragmentManager();
        transaction = manager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putInt(AppKey.KEY_EXERCISES_WORKOUT, position);
        detailsFragment.setArguments(bundle);
        transaction.replace(R.id.frame_container, detailsFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

