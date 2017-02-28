package com.trando.personalitytester;

import com.badlogic.gdx.utils.JsonValue;

/**
 * Created by Cameron on 2/27/2017.
 */
public class Answer {
    String answer;
    int pValue;

    public Answer(JsonValue value){
        this.initAnswer(value);
    }

    private void initAnswer(JsonValue value){
        this.answer = value.getString("answer");
        this.pValue = value.getInt("value");
    }

    public String getAnswer(){
        return answer;
    }
}
