package com.fatsquadent.beaverrace.android;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.fatsquadent.beaverrace.GameController;
import com.fatsquadent.beaverrace.GameMain;

public class GameLauncher extends AndroidApplication implements GameController {

    Button startButton;
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        initialize(new GameMain(GameLauncher.this), config);
	}

    @Override
    public void result(int winner) {
        Intent intent = new Intent(this, GameStartActivity.class);
        intent.putExtra("winner", winner);
        startActivity(intent);
    }
}
