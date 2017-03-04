package com.trando.personalitytester;

import com.badlogic.gdx.utils.JsonValue;

/**
 * Created by Cameron on 2/27/2017.
 */
public class Answer {
    private String answer;
    private int value;

    public Answer(JsonValue value){
        this.initAnswer(value);
    }

    private void initAnswer(JsonValue value){
        this.answer = value.getString("answer");
        this.value = value.getInt("value");
    }

    public int getValue(){
        return value;
    }

    public String getAnswer(){
        return answer;
    }
}
