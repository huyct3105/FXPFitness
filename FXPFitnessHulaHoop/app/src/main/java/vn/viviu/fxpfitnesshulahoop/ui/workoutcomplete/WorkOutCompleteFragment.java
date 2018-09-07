package vn.viviu.fxpfitnesshulahoop.ui.workoutcomplete;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import vn.viviu.fxpfitnesshulahoop.R;
import vn.viviu.fxpfitnesshulahoop.util.Commons;


public class WorkOutCompleteFragment extends Fragment implements View.OnClickListener {


    @BindView(R.id.button_back)
    ImageButton buttonBack;
    @BindView(R.id.text_day_completed)
    TextView textDayCompleted;
    @BindView(R.id.text_description)
    TextView textDescription;
    @BindView(R.id.button_share_facebook)
    Button buttonShareFacebook;
    @BindView(R.id.button_share_twitter)
    Button buttonShareTwitter;
    Unbinder unbinder;
    private int dayComplete;
    private int workOutId;
    private int workOutGroupId;

    private ShareDialog shareDialog;
    private CallbackManager callbackManager;


    public WorkOutCompleteFragment() {
        // Required empty public constructor
    }


    public static WorkOutCompleteFragment newInstance(int dayComplete, int workOutId, int workOutGroupId) {
        WorkOutCompleteFragment fragment = new WorkOutCompleteFragment();
        Bundle args = new Bundle();
        args.putInt("DayComplete", dayComplete);
        args.putInt("WorkoutId", workOutId);
        args.putInt("WorkoutGroupId",workOutGroupId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dayComplete = getArguments().getInt("DayComplete");
            workOutId = getArguments().getInt("WorkoutId");
            workOutGroupId = getArguments().getInt("WorkoutGroupId");

            //
            callbackManager = CallbackManager.Factory.create();
            shareDialog = new ShareDialog(this);
            shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
                @Override
                public void onSuccess(Sharer.Result result) {
                    LoginManager.getInstance().logOut();
                }

                @Override
                public void onCancel() {

                }

                @Override
                public void onError(FacebookException error) {

                }
            });
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_work_out_complete, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTextDayCompleted(dayComplete);
        setupUiEvents();
        //
        UpdateWorkOutCompleteTask updateWorkOutCompleteTask = new UpdateWorkOutCompleteTask(this);
        updateWorkOutCompleteTask.execute(dayComplete,workOutId,workOutGroupId);
    }

    private void setupUiEvents() {
        buttonBack.setOnClickListener(this);
        buttonShareFacebook.setOnClickListener(this);
        buttonShareTwitter.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_back:
                onClickButtonBack();
                break;
            case R.id.button_share_facebook:
                onClickButtonShareFacebook("HulaHoop - Make your device into a portable gym");
                break;
            case R.id.button_share_twitter:
                onClickButtonShareTwitter("HulaHoop - Make your device into a portable gym");
                break;
        }
    }

    private void onClickButtonBack() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        }
    }

    private void onClickButtonShareTwitter(String s) {
        if (!Commons.checkInternetConnection(getActivity())) {
            Toast.makeText(getActivity(), "Internet connection off, please check Internet connection!", Toast.LENGTH_SHORT).show();
        } else {
            shareContentOnTwitter(s);
        }
    }

    private void onClickButtonShareFacebook(String s) {
       // Toast.makeText(getActivity(), "FAcebook", Toast.LENGTH_SHORT).show();
       if (!Commons.checkInternetConnection(getActivity())) {
           Toast.makeText(getActivity(), "Internet connection off, please check Internet connection!", Toast.LENGTH_SHORT).show();
       } else {
           shareContentOnFacebook(s);
       }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    private void shareContentOnTwitter(String content) {
        startActivity(Commons.getTwitterIntent(getActivity(),content));
    }

    private void shareContentOnFacebook(String content) {
        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse("http://humg.edu.vn/Pages/home.aspx"))
                    .setQuote(content)
                    .build();
            shareDialog.show(linkContent,ShareDialog.Mode.NATIVE);
        }
    }

    public void setTextCompleteDescription(String description) {
        textDescription.setText(description);
    }

    private void setTextDayCompleted(int dayComplete) {
        textDayCompleted.setText(new StringBuilder().append("Ngày ").append(dayComplete).append("\n đã hoàn thành").toString());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
