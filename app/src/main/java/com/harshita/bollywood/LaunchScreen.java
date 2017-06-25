package com.harshita.bollywood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.harshita.bollywood.R;

public class LaunchScreen extends AppCompatActivity {

    private ImageView play_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_screen);
        play_button = (ImageView)findViewById(R.id.arrow);
        play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getApplicationContext(), GuessMovie.class);
                startActivity(intent);
            }
        });
    }
}
