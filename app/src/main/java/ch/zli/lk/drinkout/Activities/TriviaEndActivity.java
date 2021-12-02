package ch.zli.lk.drinkout.Activities;

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

import androidx.appcompat.app.AppCompatActivity;

import ch.zli.lk.drinkout.R;
import ch.zli.lk.drinkout.Services.TriviaService;

public class TriviaEndActivity extends AppCompatActivity {
    TriviaService myService;
    SharedPreferences prefs;
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
