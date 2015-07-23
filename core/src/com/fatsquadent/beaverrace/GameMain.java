package com.fatsquadent.beaverrace;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

public class GameMain extends Game  implements ApplicationListener {

	GameEnd gameEnd;
	BeaverRunner beaverRunner;

	public void setGameEnd(int i) {
		this.gameEnd = new GameEnd(this);
		gameEnd.setWinner(i);
		setScreen(gameEnd);
	}

	public void setBeaverRunner()
	{
		this.beaverRunner = new BeaverRunner(this);
		setScreen(beaverRunner);
	}


	@Override
	public void create() {
		setBeaverRunner();

	}

	@Override
	public void dispose() {

		super.dispose();
	}

	@Override
	public void render() {

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		super.render();
	}

	@Override
	public void resize(int width, int height) {

		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
