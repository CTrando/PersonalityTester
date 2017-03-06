package com.trando.personalitytester.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.trando.personalitytester.PersonalityTester;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Personality Tester";
/*		config.vSyncEnabled = false;
		config.backgroundFPS = 0;
		config.foregroundFPS = 0;*/

		new LwjglApplication(new PersonalityTester(), config);
	}
}
