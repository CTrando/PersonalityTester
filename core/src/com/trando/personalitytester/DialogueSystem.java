package com.trando.personalitytester;

import com.badlogic.gdx.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.*;

import java.util.LinkedList;

/**
 * Created by Cameron on 3/3/2017.
 */
public class DialogueSystem extends Stage {
    LinkedList<Question> questions;
    Dialogue currentDialogue;
    boolean isFinished;

    public DialogueSystem(PersonalityTester game) {
        super(game.viewport, game.batch);

        questions = new LinkedList<Question>();
        initQuestions();
        currentDialogue = questions.poll().getDialogue();
        addDialogue(currentDialogue);
    }

    @Override
    public boolean keyDown(int keyCode) {
        handleInput(keyCode);
        currentDialogue.getOptionsBox().handleInput(keyCode);
        currentDialogue.getDialogueBox().handleInput(keyCode);

        return true;
    }

    private void handleInput(int keyCode) {
        switch (keyCode) {
            case Input.Keys.ENTER:
                if(isFinished) return;
                if (currentDialogue.getDialogueBox().isFinished()) {
                    AnswerLabel answerLabel = (AnswerLabel) currentDialogue.getOptionsBox()
                                                                           .getSelectedCell()
                                                                           .getActor();
                    PersonalityTester.value += answerLabel.getValue();
                    nextQuestion();
                } else if (!currentDialogue.getDialogueBox().isFinished()) {
                    currentDialogue.getDialogueBox().forceFinishAnimation();
                }
                break;
        }
    }

    private void nextQuestion() {
        this.clear();
        Question currentQuestion = questions.poll();
        if (currentQuestion != null) {
            currentDialogue = currentQuestion.getDialogue();
            addDialogue(currentDialogue);
        } else {
            isFinished = true;
            System.out.println(PersonalityTester.value);
        }
    }

    private void clearDialogue() {
        currentDialogue.clear();
    }

    private void addDialogue(Dialogue dialogue) {
        this.addActor(dialogue);
    }

    private void initQuestions() {
        JsonReader reader = new JsonReader();
        JsonValue values = reader.parse(Gdx.files.internal("questions.json")).get("questions");
        for (JsonValue value : values.iterator()) {
            questions.add(new Question(value));
        }
    }
}
