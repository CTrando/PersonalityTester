package com.trando.personalitytester;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.*;

/**
 * Created by Cameron on 3/3/2017.
 */
public class Dialogue extends Table {
    private Question question;
    private DialogueBox dialogueBox;
    private OptionsBox optionsBox;

    public Dialogue(Skin skin, Question question){
        super(skin);
        this.question = question;
        this.setFillParent(true);

        Table table = new Table();

        dialogueBox = new DialogueBox(skin, question.getQuestion());
        optionsBox = new OptionsBox(skin, question.getAnswers());

        table.add(optionsBox)
            .expandX()
            .fillY()
            .right()
            .padBottom(20f);
        table.row();

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


}
