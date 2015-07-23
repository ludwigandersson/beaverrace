package com.fatsquadent.beaverrace.desktop;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fatsquadent.beaverrace.GameMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		// define the window's size
		int width = 1920, height = 1080;

		// whether to use OpenGL ES 2.0
		boolean useOpenGLES2 = false;

		ApplicationListener main = new GameMain();

		// create the game
		new LwjglApplication( main, "RedBeavers", width, height );
	}
}

