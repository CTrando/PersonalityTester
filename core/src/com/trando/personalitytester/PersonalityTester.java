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
	Stage stage;
	SpriteBatch batch;
	Viewport viewport;
	Camera camera;

	Table table;

	static Skin skin;
	static int value;

	LinkedList<Question> questions;
	Question currentQuestion;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		viewport = new ScreenViewport(camera);
		stage = new Stage(viewport, batch);

		skin = new Skin(Gdx.files.internal("resources.json"));

		//stage.setDebugAll(true);
		questions = new LinkedList<Question>();

		initQuestions();
		currentQuestion = questions.poll();
		//currentQuestion.getDialog().show(stage);

		table = new Table();
		table.setFillParent(true);
		stage.addActor(table);

		Dialogue dialogue = new Dialogue(skin);
		table.add(dialogue).expand().width(Gdx.graphics.getWidth()).bottom().left();

		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render () {

		if(currentQuestion != null && currentQuestion.isFinished()){
			nextQuestion();
		}

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act();
		stage.draw();
	}

	@Override
	public void dispose () {
		batch.dispose();
		stage.dispose();
	}

	@Override
	public void resize(int width, int height){
		viewport.update(width,height, true);
		camera.update();
		table.setWidth(width);
	}

	private void initQuestions(){
		JsonReader reader = new JsonReader();
		JsonValue values = reader.parse(Gdx.files.internal("questions.json")).get("questions");
		for(JsonValue value: values.iterator()){
			questions.add(new Question(value));
		}
	}

	private void nextQuestion(){
		if(!questions.isEmpty() && stage.getActors().size == 0){
			currentQuestion = questions.poll();
			currentQuestion.getDialog().show(stage);
		}
		else if(questions.isEmpty()){
			currentQuestion = null;
			getResult();
		}
	}

	private void getResult(){
		System.out.println("WORKING");
	}
}
