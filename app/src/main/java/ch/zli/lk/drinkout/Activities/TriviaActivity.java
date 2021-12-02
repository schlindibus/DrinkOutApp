package ch.zli.lk.drinkout.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import ch.zli.lk.drinkout.R;
import ch.zli.lk.drinkout.Services.TriviaService;

public class TriviaActivity extends AppCompatActivity {
    TriviaService myService;
    SharedPreferences prefs;
    Long finalTime;
    Long start;
    private static final String COUNT_STATE = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);
        Bundle bundle = getIntent().getExtras();
        prefs = getSharedPreferences(COUNT_STATE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();


    }

    private final ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            TriviaService.LocalBinder binder = (TriviaService.LocalBinder) service;
            myService = binder.getService();

            Button startBtn = findViewById(R.id.triviaStart);
            LinearLayout infos = findViewById(R.id.infos);
            LinearLayout questions = findViewById(R.id.questions);
            LinearLayout save = findViewById(R.id.saveQuestions);
            Button saveqs = findViewById(R.id.save);

            TextView q1 = findViewById(R.id.q1);
            TextView q2 = findViewById(R.id.q2);
            TextView q3 = findViewById(R.id.q3);
            TextView q4 = findViewById(R.id.q4);

            questions.setVisibility(View.GONE);
            save.setVisibility(View.GONE);

            startBtn.setOnClickListener(e -> {
                infos.setVisibility(View.GONE);
                questions.setVisibility(View.VISIBLE);
                save.setVisibility(View.VISIBLE);

                q1.setText(myService.setQuestion(0));
                q2.setText(myService.setQuestion(1));
                q3.setText(myService.setQuestion(2));
                q4.setText(myService.setQuestion(3));
            });

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