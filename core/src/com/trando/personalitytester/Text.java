package com.trando.personalitytester;

import com.badlogic.gdx.utils.*;

/**
 * Created by Cameron on 2/27/2017.
 */
public class Text {
    String text;
    Array<Option> options;

    Dialogue dialogue;

    public Text(JsonValue value){
        options = new Array<Option>();
        text = value.getString("text");

        if(value.has("options")) {
            this.initOptions(value);
        }
        this.initDialog();
    }

    private void initOptions(JsonValue value){
        for(JsonValue val: value.get("options").iterator()){
            options.add(new Option(val));
        }
    }

    private void initDialog(){
        dialogue = new Dialogue(PersonalityTester.skin, this);
    }

    public Dialogue getDialogue(){
        return dialogue;
    }

    public String getText(){
        return text;
    }

    public Array<Option> getOptions(){
        return options;
    }

    public boolean hasOptions() {
        return options.size > 0;
    }
}
