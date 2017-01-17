package com.fatsquadent.beaverrace.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameEndActivity extends Activity {

    Button startButton;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_game_end);


        startButton = (Button)findViewById(R.id.btnGameRestart);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameEndActivity.this, GameLauncher.class);
                startActivity(intent);
            }
        });

        textView = (TextView)findViewById(R.id.tvWinnerMsg);

        Intent intent = getIntent();
        int winner = intent.getExtras().getInt("winner");
        textView.setText(String.format("Mest ölsug hade bäwer #%d och tilldelas därför sex väl kylda",winner));
        startButton.setText("Känn över't igen!");
        //android:theme="@android:style/Theme.Translucent.NoTitleBar"



        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
    }
}
