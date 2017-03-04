package com.trando.personalitytester;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.utils.*;

/**
 * Created by Cameron on 2/27/2017.
 */
public class Question {
    String question;
    Array<Answer> answers;

    Dialog dialog;
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
        dialog = new Dialog(question, PersonalityTester.skin){
            @Override
            protected void result(Object object) {
                Answer answer = (Answer) object;
                PersonalityTester.value += answer.pValue;
                isFinished = true;
            }
        };

        for(Answer answer: answers){
            dialog.button(answer.getAnswer(), answer);
        }
        dialog.padTop(60f);
    }

    public boolean isFinished(){
        return isFinished;
    }

    public Dialog getDialog(){
        return dialog;
    }
}
