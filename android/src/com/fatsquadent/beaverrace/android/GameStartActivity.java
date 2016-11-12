package com.fatsquadent.beaverrace.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameStartActivity extends Activity {

    Button startButton;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_game_start);


        startButton = (Button)findViewById(R.id.btnGameStart);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameStartActivity.this, GameLauncher.class);
                startActivity(intent);
            }
        });

        textView = (TextView)findViewById(R.id.textView);

        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            int winner = intent.getExtras().getInt("winner");
            textView.setText(String.format("Störst ölsug hade bäwer #%d",winner));
            startButton.setText("Känn över't igen!");
            //android:theme="@android:style/Theme.Translucent.NoTitleBar"
            super.setTheme(android.R.style.Theme_Translucent_NoTitleBar);
        } else {
            startButton.setText("Race!");
        }

        super.onCreate(savedInstanceState);
    }

    @Override
    public void setTheme(int resid) {
        boolean changeTheme = true;
        super.setTheme(changeTheme ? android.R.style.Theme_Translucent_NoTitleBar : resid);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_end, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
