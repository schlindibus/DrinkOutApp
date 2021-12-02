package ch.zli.lk.drinkout.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.util.LogPrinter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.concurrent.ThreadLocalRandom;

import ch.zli.lk.drinkout.R;
import ch.zli.lk.drinkout.Services.BombService;

public class BombActivity extends AppCompatActivity {
    BombService myService;
    long start;
    long end;
    boolean firstClick = false;
    int myCounter = 0;
    SharedPreferences prefs;
    private static final String COUNT_STATE = "";
    String bombState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bomb);
        Bundle bundle = getIntent().getExtras();
        prefs = getSharedPreferences(COUNT_STATE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
    }

    private final ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BombService.LocalBinder binder = (BombService.LocalBinder) service;
            myService = binder.getService();
            ImageButton defuseClicker = findViewById(R.id.defuseClicker);

            defuseClicker.setOnClickListener(e -> {
                if (firstClick == false) {
                    firstClick = true;
                    start = System.currentTimeMillis();
                    int min = 10;
                    int max = 15;
                    int rndmTime = ThreadLocalRandom.current().nextInt(min, max + 1);
                    end = start + (rndmTime * 1000);
                }
                myCounter++;
                if (System.currentTimeMillis() > end) {
                    bombState = myService.defuseBomb(myCounter);

                    if (bombState == BombService.bombState.Defused.name()) {
                        Intent defusedActivity = new Intent(getApplicationContext(), DefusedAcivity.class);
                        startActivity(defusedActivity);
                    } else {
                        Intent explodedAcitvity = new Intent(getApplicationContext(), ExploededActivity.class);
                        startActivity(explodedAcitvity);
                        myService.vibrate(getApplicationContext(), 2000);
                    }
                }
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
        Intent intent = new Intent(this, BombService.class);
        bindService(intent, connection, this.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);
    }
}