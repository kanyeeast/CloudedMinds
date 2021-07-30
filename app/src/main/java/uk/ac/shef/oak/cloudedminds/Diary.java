package uk.ac.shef.oak.cloudedminds;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.ac.shef.oak.cloudedminds.Retrofit.IMyService;

public class Diary extends AppCompatActivity {

    private MediaPlayer mp;
    private TextView listEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        final Vibrator vibe = (Vibrator) Diary.this.getSystemService(Context.VIBRATOR_SERVICE);

        ImageView home = findViewById(R.id.btnHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buttontap);
                mp.start();
                vibe.vibrate(80);
                startActivity(new Intent(Diary.this, MainActivity.class));

            }
        });

        listEntries = findViewById(R.id.listEntries);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://192.168.0.18:3000/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        IMyService iMyService = retrofit.create(IMyService.class);
        Call<List<Entry>> call = iMyService.getEntries();

        call.enqueue(new Callback<List<Entry>>() {
            @Override
            public void onResponse(Call<List<Entry>> call, Response<List<Entry>> response) {
                List<Entry> entries = response.body();




                for (Entry entry : entries) {
                    String content = "";
                    content += "Event: " + entry.getEvent() + "\n";
                    content += "Date: " + entry.getDate() + "\n";
                    content += "Mood: " + entry.getMood() + "\n";
                    content += "Mood Rating: " + entry.getMood_rating() + "\n";
                    content += "Catastrophised: " + entry.getCatastrophise() + "\n";
                    content += "Generalised: " + entry.getGeneralise() + "\n";
                    content += "Ignored the Positive: " + entry.getIgnoring() + "\n";
                    content += "Self-Critical: " + entry.getSelf_critical() + "\n";
                    content += "Mind Read: " + entry.getMind_reading() + "\n";
                    content += "Changed Mood: " + entry.getChanged_mood() + "\n";
                    content += "Changed Rating: " + entry.getChanged_rating() + "\n\n\n";

                    listEntries.append(content);

                }
            }

            @Override
            public void onFailure(Call<List<Entry>> call, Throwable t) {
                listEntries.setText(t.getMessage());
            }
        });


        /*Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.18:3000/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        IMyService iMyService = retrofit.create(IMyService.class);

        Call<List<Entry>> call = iMyService.getEntries();
        call.enqueue(new Callback<List<Entry>>() {
            @Override
            public void onResponse(Call<List<Entry>> call, Response<List<Entry>> response) {
                if (!response.isSuccessful()){
                    listEntries.setText("Code: " + response.code());
                    return;
                }


                List<Entry> entries = response.body();

                for (Entry entry : entries) {
                    String content ="";
                    content += "Event: " + entry.getEvent() + "\n";
                    content += "Date: " + entry.getDate() + "\n";
                    content += "Mood: " + entry.getMood() + "\n";
                    content += "Mood Rating: " + entry.getMood_rating() + "\n";
                    content += "Catastrophised: " + entry.getCatastrophise() + "\n";
                    content += "Generalised: " + entry.getGeneralise() + "\n";
                    content += "Ignored the Positive: " + entry.getIgnoring() + "\n";
                    content += "Self-Critical: " + entry.getSelf_critical() + "\n";
                    content += "Mind Read: " + entry.getMind_reading() + "\n";
                    content += "Changed Mood: " + entry.getChanged_mood() + "\n";
                    content += "Changed Rating: " + entry.getChanged_rating() + "\n";

                    listEntries.append(content);

                }
            }

            @Override
            public void onFailure(Call<List<Entry>> call, Throwable t) {
                listEntries.setText(t.getMessage());
            }
        });*/
    }
}