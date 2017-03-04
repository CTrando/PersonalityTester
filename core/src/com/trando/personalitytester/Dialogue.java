package com.trando.personalitytester;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.*;

/**
 * Created by Cameron on 3/3/2017.
 */
public class Dialogue extends Table {
    Question question;
    DialogueBox dialogueBox;
    OptionsBox optionsBox;

    public Dialogue(Skin skin, Question question){
        super(skin);
        this.question = question;
        this.setFillParent(true);

        Table table = new Table();

        dialogueBox = new DialogueBox(skin, question.getQuestion());
        optionsBox = new OptionsBox(skin, question.getAnswers());

        table.add(optionsBox)
            .expandX()
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


}
