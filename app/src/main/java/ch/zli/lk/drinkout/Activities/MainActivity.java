package ch.zli.lk.drinkout.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import ch.zli.lk.drinkout.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bombButton = findViewById(R.id.bomb_game);
        Button triviaButton = findViewById(R.id.trivia_game);

        bombButton.setOnClickListener(e -> {
            Intent bombIntent = new Intent(getApplicationContext(), BombActivity.class);
            startActivity(bombIntent);
        });

        triviaButton.setOnClickListener(e -> {
            Intent triviaIntent = new Intent(getApplicationContext(), TriviaActivity.class);
            startActivity(triviaIntent);
        });
        
    }
}