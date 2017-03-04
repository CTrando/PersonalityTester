package com.trando.personalitytester;

import com.badlogic.gdx.scenes.scene2d.ui.*;

/**
 * Created by Cameron on 3/4/2017.
 */
public class AnswerLabel extends Label {
    private int value;
    public AnswerLabel(Answer answer, Skin skin) {
        super(answer.getAnswer(), skin);
        this.value = answer.getValue();
    }

    public int getValue(){
        return value;
    }
}
