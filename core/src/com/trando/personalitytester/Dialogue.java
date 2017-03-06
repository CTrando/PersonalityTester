package com.trando.personalitytester;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.*;

/**
 * Created by Cameron on 3/3/2017.
 */
public class Dialogue extends Table {
    private Text text;
    private DialogueBox dialogueBox;
    private OptionsBox optionsBox;

    public Dialogue(Skin skin, Text text){
        super(skin);
        this.text = text;
        this.setFillParent(true);

        Table table = new Table();

        dialogueBox = new DialogueBox(skin, text.getText());
        if(text.hasOptions()) {
            optionsBox = new OptionsBox(skin, dialogueBox, text.getOptions());

            table.add(optionsBox)
                 .expandX()
                 .fillY()
                 .right()
                 .padBottom(20f);
            table.row();
        }

        table.add(dialogueBox)
            .expandX()
            .width(Gdx.graphics.getWidth())
            .left()
            .padBottom(10f);
        dialogueBox.animateText();

        this.add(table).expand().bottom();
    }

    public OptionsBox getOptionsBox(){
        return optionsBox;
    }

    public DialogueBox getDialogueBox(){
        return dialogueBox;
    }

    public boolean hasOptionsBox(){
        return optionsBox != null;
    }
}
