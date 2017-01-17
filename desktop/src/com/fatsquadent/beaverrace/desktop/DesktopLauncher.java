package com.fatsquadent.beaverrace.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fatsquadent.beaverrace.BeaverActor;
import com.fatsquadent.beaverrace.GameController;
import com.fatsquadent.beaverrace.GameMain;

public class DesktopLauncher implements GameController {
	private static GameMain mGameMain;
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		// define the window's size
		int width = 1920, height = 1080;

		// whether to use OpenGL ES 2.0
		boolean useOpenGLES2 = true;

		DesktopLauncher desktopLauncher = new DesktopLauncher();
		mGameMain = new GameMain(desktopLauncher);

		// create the game
		new LwjglApplication( mGameMain, "RedBeavers", width, height );
	}

	@Override
	public void result(BeaverActor beaver) {
		System.out.println("We have a winner!");
		System.out.println(beaver.toString());
		System.out.println("---------------------------");
		mGameMain.showEmbeddedGameEnd(beaver.getId());
	}
}

