package com.trando.personalitytester;

import com.badlogic.gdx.utils.*;

/**
 * Created by Cameron on 2/27/2017.
 */
public class Question {
    String question;
    Array<Answer> answers;

    Dialogue dialogue;
    boolean isFinished;

    public Question(JsonValue value){
        answers = new Array<Answer>();

        this.initQuestion(value);
        this.initDialog();
    }

    private void initQuestion(JsonValue value){
        question = value.getString("question");

        for(JsonValue val: value.get("answers").iterator()){
            answers.add(new Answer(val));
        }
    }

    private void initDialog(){
        dialogue = new Dialogue(PersonalityTester.skin, this);
    }

    public boolean isFinished(){
        return isFinished;
    }

    public Dialogue getDialogue(){
        return dialogue;
    }

    public String getQuestion(){
        return question;
    }

    public Array<Answer> getAnswers(){
        return answers;
    }
}
