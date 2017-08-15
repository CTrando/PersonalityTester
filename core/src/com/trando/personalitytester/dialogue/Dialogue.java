package com.trando.personalitytester.dialogue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.trando.personalitytester.*;
import com.trando.personalitytester.options.OptionsBox;

/**
 * Created by Cameron on 3/3/2017.
 */
public class Dialogue extends Table {
    private Text text;
    private Skin skin;
    private DialogueBox dialogueBox;
    private OptionsBox optionsBox;

    public Dialogue(Skin skin, Text text) {
        super(skin);
        this.skin = skin;
        this.text = text;
        this.setFillParent(true);
        dialogueBox = new DialogueBox(skin, text.getText());
    }

    public void init() {
        Table table = new Table();

        if (text.hasOptions()) {
            optionsBox = new OptionsBox(skin, dialogueBox, text.getOptions());

            this.add(optionsBox)
                .expand()
                .bottom()
                .right()
                .padBottom(20f);
            this.row();

            this.add(dialogueBox)
                .fillX()
                .height(100)
                .bottom()
                .padBottom(10f);
        } else {
            this.add(dialogueBox)
                .fillX()
                .expand()
                .height(100)
                .bottom()
                .padBottom(10f);
        }
        //this.add(table).expand().fill();

        dialogueBox.animateText();
    }

    public OptionsBox getOptionsBox() {
        return optionsBox;
    }

    public DialogueBox getDialogueBox() {
        return dialogueBox;
    }

    public boolean hasOptionsBox() {
        return optionsBox != null;
    }
}
