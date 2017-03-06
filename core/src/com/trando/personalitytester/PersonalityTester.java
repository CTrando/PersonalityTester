package com.trando.personalitytester;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.*;

public class PersonalityTester extends ApplicationAdapter {
	DialogueSystem dialogueSystem;
	SpriteBatch batch;
	Viewport viewport;
	Camera camera;

	static Skin skin;


	public static boolean hasReceivedAnswer;

	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		viewport = new ScreenViewport(camera);
		skin = new Skin(Gdx.files.internal("resources.json"));

		dialogueSystem = new DialogueSystem(this);
		dialogueSystem.setDebugAll(true);

		Gdx.input.setInputProcessor(dialogueSystem);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0, .5f);

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
		viewport.update(width,height, true);
		camera.update();
	}

	private void getResult(){
		System.out.println("WORKING");
	}
}
