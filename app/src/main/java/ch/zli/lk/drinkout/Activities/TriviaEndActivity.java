package ch.zli.lk.drinkout.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.TextView;

import java.util.Arrays;

import ch.zli.lk.drinkout.R;
import ch.zli.lk.drinkout.Services.TriviaService;

public class TriviaEndActivity extends AppCompatActivity {

    TriviaService myService;
    String a1, a2, a3, a4;
    String[] answers = new String [] {
            "4",
            "vier",
            "paris",
            "ja",
            "gibt es nicht"
    };
    int finalPoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_end);
        Bundle bundle = getIntent().getExtras();
        a1 = (String) bundle.get("a1").toString().toLowerCase();
        a2 = (String) bundle.get("a2").toString().toLowerCase();
        a3 = (String) bundle.get("a3").toString().toLowerCase();
        a4 = (String) bundle.get("a4").toString().toLowerCase();

        if (Arrays.asList(answers).contains(a1)) {
            finalPoints++;
        }
        if (Arrays.asList(answers).contains(a2)) {
            finalPoints++;
        }
        if (Arrays.asList(answers).contains(a3)) {
            finalPoints++;
        }
        if (Arrays.asList(answers).contains(a4)) {
            finalPoints++;
        }

        TextView finalScore = findViewById(R.id.points);
        finalScore.setText("" + finalPoints);
    }

    private final ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            TriviaService.LocalBinder binder = (TriviaService.LocalBinder) service;
            myService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.out.println("Not Connected...");
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, TriviaService.class);
        bindService(intent, connection, this.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);

    }
}