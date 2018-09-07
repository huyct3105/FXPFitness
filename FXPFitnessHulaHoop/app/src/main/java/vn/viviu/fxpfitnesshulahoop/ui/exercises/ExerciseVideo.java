package vn.viviu.fxpfitnesshulahoop.ui.exercises;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import vn.viviu.fxpfitnesshulahoop.R;

import static android.os.Build.VERSION_CODES.O;


public class ExerciseVideo extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private String API_KEY = "AIzaSyBwZmPpy0Ok57IoZXXUJsQE5045Y9frqSM";
    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayer mYoutubePlayer;
    private String uri_video;
    private int currentTimeYoutube = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_exercise);
        Intent intent = getIntent();
        String link = intent.getStringExtra("link");
        if (!link.equals("")) {
            int a = link.lastIndexOf("/");
            uri_video = link.substring(a + 1);
        }

        if (savedInstanceState != null) {
            currentTimeYoutube = savedInstanceState.getInt("currentTimeYoutube");
        }


        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.my_youtube);
        youTubePlayerView.initialize(API_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (uri_video != "") {
            Log.d("abc", "current: " + currentTimeYoutube);
            youTubePlayer.loadVideo(uri_video, currentTimeYoutube);
            mYoutubePlayer = youTubePlayer;
        } else {
            Toast.makeText(ExerciseVideo.this, "Không có video", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        if (mYoutubePlayer != null) {
            bundle.putInt("currentTimeYoutube", mYoutubePlayer.getCurrentTimeMillis());
        }
        super.onSaveInstanceState(bundle);
    }


    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}
