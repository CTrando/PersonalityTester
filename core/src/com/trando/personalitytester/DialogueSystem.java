package com.trando.personalitytester;

import com.badlogic.gdx.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.*;

import java.util.LinkedList;

/**
 * Created by Cameron on 3/3/2017.
 */
public class DialogueSystem extends Stage {
    LinkedList<Question> questions;
    Dialogue currentDialogue;

    public DialogueSystem(PersonalityTester game){
        super(game.viewport, game.batch);

        questions = new LinkedList<Question>();
        initQuestions();
        currentDialogue = questions.poll().getDialogue();
        addDialogue(currentDialogue);
    }

    @Override
    public boolean keyDown(int keyCode) {
        switch(keyCode){
            case Input.Keys.X:
                if(currentDialogue.dialogueBox.isFinished()){
                    currentDialogue.clear();
                    currentDialogue = questions.poll().getDialogue();
                    addDialogue(currentDialogue);
                } else currentDialogue.dialogueBox.forceFinishAnimation();
        }
        return true;
    }

    private void clearDialogue(){
        currentDialogue.clear();
    }

    private void addDialogue(Dialogue dialogue){
        this.addActor(dialogue);
    }

    private void initQuestions(){
        JsonReader reader = new JsonReader();
        JsonValue values = reader.parse(Gdx.files.internal("questions.json")).get("questions");
        for(JsonValue value: values.iterator()){
            questions.add(new Question(value));
        }
    }
}
