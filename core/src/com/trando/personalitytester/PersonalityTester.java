package com.trando.personalitytester;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.utils.viewport.*;

import java.util.LinkedList;

public class PersonalityTester extends ApplicationAdapter {
	DialogueSystem dialogueSystem;
	SpriteBatch batch;
	Viewport viewport;
	Camera camera;

	Table table;

	static Skin skin;
	static int value;

	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		viewport = new ScreenViewport(camera);
		skin = new Skin(Gdx.files.internal("resources.json"));

		dialogueSystem = new DialogueSystem(this);
		//dialogueSystem.setDebugAll(true);

		Gdx.input.setInputProcessor(dialogueSystem);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		dialogueSystem.act();
		dialogueSystem.draw();
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
/*

	private void nextQuestion(){
		if(!questions.isEmpty() && dialogueSystem.getActors().size == 0){
			currentQuestion = questions.poll();
			currentQuestion.getDialog().show(dialogueSystem);
		}
		else if(questions.isEmpty()){
			currentQuestion = null;
			getResult();
		}
	}
*/

	private void getResult(){
		System.out.println("WORKING");
	}
}
