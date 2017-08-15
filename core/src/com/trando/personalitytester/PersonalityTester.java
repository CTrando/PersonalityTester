package com.trando.personalitytester;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.*;
import com.trando.personalitytester.dialogue.*;

public class PersonalityTester extends ApplicationAdapter {
	public DialogueSystem dialogueSystem;
	public SpriteBatch batch;
	public ScreenViewport viewport;
	public Camera camera;

	static Skin skin;

	private Background background;

	public static final int NUM_QUESTIONS = 2;
	public static boolean hasReceivedAnswer;

	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		viewport = new ScreenViewport(camera);
		skin = new Skin(Gdx.files.internal("resources.json"));
		background = new Background(skin);

		dialogueSystem = new DialogueSystem(this);
		dialogueSystem.setDebugAll(true);

		Gdx.input.setInputProcessor(dialogueSystem);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(1, 1, 1, 1f);
		background.render(batch);

		if(!dialogueSystem.isFinished) {
			dialogueSystem.act();
			dialogueSystem.draw();
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
		dialogueSystem.dispose();
	}

	@Override
	public void resize(int width, int height){
		dialogueSystem.invalidate();
		dialogueSystem.getViewport().update(width, height, false);
		viewport.update(width,height, true);
		camera.update();
	}

	private void getResult(){
		System.out.println("WORKING");
	}
}
