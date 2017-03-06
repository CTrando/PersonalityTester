package com.trando.personalitytester;

import com.badlogic.gdx.utils.JsonValue;

/**
 * Created by Cameron on 2/27/2017.
 */
public class Option {
    private Trait trait;
    private String option;
    private int value;

    public Option(JsonValue value){
        this.initOption(value);
    }

    private void initOption(JsonValue value){
        this.option = value.getString("option");
        this.value = value.getInt("value");
        if(value.has("trait")) {
            this.trait = Enum.valueOf(Trait.class, value.getString("trait"));
        } else this.trait = Trait.neutral;
    }

    public int getValue(){
        return value;
    }

    public String getOption(){
        return option;
    }

    public Trait getTrait() {
        return trait;
    }
}
