package com.trando.personalitytester;

import com.badlogic.gdx.scenes.scene2d.ui.*;

/**
 * Created by Cameron on 3/4/2017.
 */
public class OptionLabel extends Label {
    private int value;
    private Trait trait;

    public OptionLabel(Option option, Skin skin) {
        super(option.getOption(), skin);
        this.value = option.getValue();
        this.trait = option.getTrait();
    }

    public int getValue(){
        return value;
    }

    public Trait getTrait() {
        return trait;
    }
}
