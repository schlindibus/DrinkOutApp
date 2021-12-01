package ch.zli.lk.drinkout.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import ch.zli.lk.drinkout.R;

public class ExploededActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exploeded);

        Button backBtn = findViewById(R.id.backToOverviewEx);
        backBtn.setOnClickListener(c-> {
            Intent backIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(backIntent);
        });
    }
}