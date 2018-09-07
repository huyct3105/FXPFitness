package vn.viviu.fxpfitnesshulahoop.ui.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import vn.viviu.fxpfitnesshulahoop.R;
import vn.viviu.fxpfitnesshulahoop.util.AppKey;
import vn.viviu.fxpfitnesshulahoop.util.Commons;

public class MainFragment extends Fragment {
    @BindView(R.id.main_btn)
    ImageView mainBtn;
    @BindView(R.id.premium_btn)
    ImageView premiumBtn;
    Unbinder unbinder;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);

        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Commons.replaceFragment(getActivity().getSupportFragmentManager(), AppKey.KEY_MAIN_PREMIUM, R.id.frame_container);
            }
        });

        premiumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Commons.replaceFragment(getActivity().getSupportFragmentManager(), AppKey.KEY_PREMIUM_WORKOUTS, R.id.frame_container);
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
