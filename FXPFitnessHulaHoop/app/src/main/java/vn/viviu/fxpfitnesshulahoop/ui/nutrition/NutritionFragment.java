package vn.viviu.fxpfitnesshulahoop.ui.nutrition;


import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import vn.viviu.fxpfitnesshulahoop.R;
import vn.viviu.fxpfitnesshulahoop.data.database.AppDatabase;
import vn.viviu.fxpfitnesshulahoop.data.database.entity.Nutrition;
import vn.viviu.fxpfitnesshulahoop.util.AppKey;


/**
 * A simple {@link Fragment} subclass.
 */
public class NutritionFragment extends Fragment implements AdapterNutritionCallBack {
    private List<Nutrition> nutritions;
    private NutritionAdapter adapter;
    private RecyclerView rcvNutrition;
    private LinearLayoutManager layoutManager;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nutrition, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvNutrition = view.findViewById(R.id.rcvNutrition);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        AppDatabase db = AppDatabase.getInstance(getActivity().getApplicationContext());
        nutritions = db.NutritionDao().getAllNutritions();
        adapter = new NutritionAdapter(getContext(), nutritions, this);
        rcvNutrition.setLayoutManager(layoutManager);
        rcvNutrition.setHasFixedSize(true);
        rcvNutrition.setAdapter(adapter);

    }

    @Override
    public void onItemClick(Nutrition nutrition) {
        NutritionDetailsFragment detailsFragment = new NutritionDetailsFragment();
        manager = getFragmentManager();
        transaction = manager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putSerializable("Nutrition", nutrition);
        detailsFragment.setArguments(bundle);
        transaction.replace(R.id.frame_container, detailsFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
