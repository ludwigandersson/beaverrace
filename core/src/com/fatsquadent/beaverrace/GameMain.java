package com.fatsquadent.beaverrace;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class GameMain extends Game  implements ApplicationListener {

	GameEnd gameEnd;
	BeaverRunner beaverRunner;
	GameController callback;

	public GameMain(GameController callback) {
		this.callback = callback;
	}

	public void setGameEnd(int i) {
		/*this.gameEnd = new GameEnd(this);
		gameEnd.setWinner(i);
		setScreen(gameEnd);*/
		callback.result(i);
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
