package vn.viviu.fxpfitnesshulahoop.ui.main;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import vn.viviu.fxpfitnesshulahoop.R;
import vn.viviu.fxpfitnesshulahoop.data.database.AppDatabase;
import vn.viviu.fxpfitnesshulahoop.data.database.dao.WorkoutGroupDao;
import vn.viviu.fxpfitnesshulahoop.data.database.entity.UserWorkoutGroup;
import vn.viviu.fxpfitnesshulahoop.data.database.entity.WorkoutGroup;
import vn.viviu.fxpfitnesshulahoop.ui.login.SharePreferencesManager;
import vn.viviu.fxpfitnesshulahoop.util.AppKey;

import static com.facebook.FacebookSdk.getApplicationContext;


public class PremiumWorkoutFragment extends Fragment {
    @BindView(R.id.premium_workout_bar)
    Toolbar toolbar;
    @BindView(R.id.avt_premium_workout)
    CircleImageView avtPremiumWorkout;
    @BindView(R.id.title_premium_workout)
    TextView titlePremiumWorkout;
    @BindView(R.id.description_premium_workout)
    TextView descriptionPremiumWorkout;
    @BindView(R.id.purchase_it_btn)
    Button purchaseItBtn;
    Unbinder unbinder;
    @BindView(R.id.title_toolbar)
    TextView titleToolbar;

    WorkoutGroupDao groupDao;

    public PremiumWorkoutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_premium_workout, container, false);
        unbinder = ButterKnife.bind(this, view);

        assert getActivity() != null;
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        titleToolbar.setText(R.string.premium_workout);

        groupDao = AppDatabase.getInstance(getContext()).WorkoutGroupDao();
        SharedPreferences sharedPreferences = SharePreferencesManager.getInstance(getApplicationContext());
        final int idUser = sharedPreferences.getInt(AppKey.KEY_USER_ID, 0);

        if (getArguments() != null) {
            final WorkoutGroup premiumWorkout = (WorkoutGroup) getArguments().getSerializable(AppKey.KEY_WORKOUT);
            assert premiumWorkout != null;
            String premiumTitle = premiumWorkout.getWorkoutgroupName();
            String premiumDescription = getString(R.string.description).replaceAll("90 Day Challenge", premiumWorkout.getWorkoutgroupName());

            titlePremiumWorkout.setText(premiumTitle);
            descriptionPremiumWorkout.setText(premiumDescription);

            purchaseItBtn.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("StaticFieldLeak")
                @Override
                public void onClick(View view) {
                    final ProgressDialog progressDialog = new ProgressDialog(getContext());
                    progressDialog.setMessage("Purchasing...");
                    progressDialog.show();
                    final AppDatabase db = AppDatabase.getInstance(getActivity().getApplicationContext());

                    final List<WorkoutGroup> groupList = new ArrayList<>();
                    groupList.add(premiumWorkout);

                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... voids) {
                            db.userWorkoutGroupDao().insertUserWorkoutGroup(new UserWorkoutGroup(idUser,premiumWorkout.getIdWorkoutGroup(),0));
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            progressDialog.cancel();
                            Toast.makeText(getActivity(), "Purchase success!!!", Toast.LENGTH_SHORT).show();
                            getFragmentManager().popBackStack();
                        }
                    }.execute();
                }
            });
        }
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
}
