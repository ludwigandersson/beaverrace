package com.fatsquadent.beaverrace.android;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.fatsquadent.beaverrace.BeaverActor;
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
    public void result(BeaverActor beaver) {
        Intent intent = new Intent(this, GameEndActivity.class);
        intent.putExtra("winner", beaver.getId());
        startActivity(intent);
    }

    @Override
    protected void onDestroy () {
        super.onDestroy();
    }
}
