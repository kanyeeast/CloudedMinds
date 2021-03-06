package uk.ac.shef.oak.cloudedminds;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

import uk.ac.shef.oak.cloudedminds.Session.SessionManager;

/**
 * This class is for the main menu screen and displays buttons which navigate users to
 * specific screens on the app. The user gets access to this screen only when they are
 * logged in and can logout on this screen to end session.
 */
public class MainActivity extends AppCompatActivity {

    SessionManager sessionManager;

    private MediaPlayer mp;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);

        // Checks whether the user is logged in, if not, it opens the login screen instead.
        sessionManager = new SessionManager(getApplicationContext());
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetails();
        String username = user.get(SessionManager.USERNAME);

        // If the user is not logged in, it ends the main menu activity so the user cannot press the back button and access it.
        if(sessionManager.isLoggedIn() == false){
            finish();
        }

        TextView welcome = findViewById(R.id.txtWelcomeName);
        welcome.setText("Welcome " + username + "!");


        // Button to take the user to the mood entry session screens
        Button start = findViewById(R.id.btnStart);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buttontap);
                mp.start();
                vibe.vibrate(80);
                startActivity(new Intent(MainActivity.this, IntroScreen.class));;
            }
        });

        start.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    // When the user clicks the Button
                    case MotionEvent.ACTION_DOWN:
                        start.setTypeface(Typeface.DEFAULT_BOLD);
                        break;

                    // When the user releases the Button
                    case MotionEvent.ACTION_UP:
                        start.setTypeface(Typeface.DEFAULT);
                        break;
                }
                return false;
            }
        });

        // Button to take the user to the about screen
        Button about = findViewById(R.id.btnAbout);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buttontap);
                mp.start();
                vibe.vibrate(80);
                startActivity(new Intent(MainActivity.this, About.class));
            }
        });

        about.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    // When the user clicks the Button
                    case MotionEvent.ACTION_DOWN:
                        about.setTypeface(Typeface.DEFAULT_BOLD);
                        break;

                    // When the user releases the Button
                    case MotionEvent.ACTION_UP:
                        about.setTypeface(Typeface.DEFAULT);
                        break;
                }
                return false;
            }
        });

        // Button to take the user to the diary screen
        Button diary = findViewById(R.id.btnDiary);
        diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buttontap);
                mp.start();
                vibe.vibrate(80);
                startActivity(new Intent(MainActivity.this, Diary.class));
            }
        });

        diary.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    // When the user clicks the Button
                    case MotionEvent.ACTION_DOWN:
                        diary.setTypeface(Typeface.DEFAULT_BOLD);
                        break;

                    // When the user releases the Button
                    case MotionEvent.ACTION_UP:
                        diary.setTypeface(Typeface.DEFAULT);
                        break;
                }
                return false;
            }
        });

        // Button to logout user, ending the user session and returning the user to the login screen
        Button logout = findViewById(R.id.btnLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buttontap);
                mp.start();
                vibe.vibrate(80);
                sessionManager.logoutUser();
                finish();
            }
        });

        logout.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    // When the user clicks the Button
                    case MotionEvent.ACTION_DOWN:
                        logout.setTypeface(Typeface.DEFAULT_BOLD);
                        break;

                    // When the user releases the Button
                    case MotionEvent.ACTION_UP:
                        logout.setTypeface(Typeface.DEFAULT);
                        break;
                }
                return false;
            }
        });
    }
}