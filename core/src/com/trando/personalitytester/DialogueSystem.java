package com.trando.personalitytester;

import com.badlogic.gdx.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.*;

import java.util.LinkedList;

/**
 * Created by Cameron on 3/3/2017.
 */
public class DialogueSystem extends Stage {
    LinkedList<Text> dialogues;
    LinkedList<Text> questions;

    Dialogue currentDialogue;
    boolean isFinished;

    public DialogueSystem(PersonalityTester game) {
        super(game.viewport, game.batch);

        dialogues = new LinkedList<Text>();
        questions = new LinkedList<Text>();

        initPreamble();
        initQuestions();

        currentDialogue = dialogues.poll().getDialogue();
        addDialogue(currentDialogue);
    }

    @Override
    public boolean keyDown(int keyCode) {
        handleInput(keyCode);
        if(currentDialogue.hasOptionsBox()) {
            currentDialogue.getOptionsBox().handleInput(keyCode);
        }
        currentDialogue.getDialogueBox().handleInput(keyCode);

        return true;
    }

    private void handleInput(int keyCode) {
        switch (keyCode) {
            case Input.Keys.ENTER:
                if(isFinished) return;
                if (currentDialogue.getDialogueBox().isFinished()) {
                    nextQuestion();
                } else if (!currentDialogue.getDialogueBox().isFinished()) {
                    currentDialogue.getDialogueBox().forceFinishAnimation();
                }
                break;
        }
    }

    private void nextQuestion() {
        this.clear();
        Text currentQuestion = dialogues.poll();
        if (currentQuestion != null) {
            currentDialogue = currentQuestion.getDialogue();
            addDialogue(currentDialogue);
        } else if (!PersonalityTester.hasReceivedAnswer){
            //it will add dialogue for the resulting trait to the linked list
            initAnswerText(TraitHolder.getBestTrait());
            initClosingText();

            //will initialize the current question again so the current question becomes not null
            currentQuestion = dialogues.poll();
            currentDialogue = currentQuestion.getDialogue();
            addDialogue(currentDialogue);

            //make sure that the next time it will run, it will exit instead
            PersonalityTester.hasReceivedAnswer = true;
        } else {
            Gdx.app.exit();
        }
    }

    private void initPreamble(){
        JsonReader reader = new JsonReader();
        JsonValue values = reader.parse(Gdx.files.internal("preamble.json"));
        for (JsonValue value : values.iterator()){
            dialogues.add(new Text(value));
        }
    }

    //do some work with PersonalityTester.NUM_QUESTIONS here
    private void initQuestions() {
        JsonReader reader = new JsonReader();
        JsonValue values = reader.parse(Gdx.files.internal("questions.json"));
        for (JsonValue value : values.iterator()) {
            dialogues.add(new Text(value));
            questions.add(new Text(value));
        }
    }

    private void initAnswerText(Trait trait){
        JsonReader reader = new JsonReader();
        JsonValue values = reader.parse(Gdx.files.internal("traits/"+ trait.toString() +".json"));
        for (JsonValue value : values.iterator()){
            dialogues.addLast(new Text(value));
        }
    }

    private void initClosingText(){
        JsonReader reader = new JsonReader();
        JsonValue values = reader.parse(Gdx.files.internal("conclusion.json"));
        for (JsonValue value : values.iterator()){
            dialogues.addLast(new Text(value));
        }
    }

    private void addDialogue(Dialogue dialogue) {
        this.addActor(dialogue);
    }
}
