package com.trando.personalitytester;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Cameron on 3/3/2017.
 */
public class OptionsBox extends Table {
    Array<Answer> answers;

    public OptionsBox(Skin skin, Array<Answer> answers){
        super(skin);
        this.setBackground("dialog.9");

        this.answers = answers;
        initAnswerLabels();
    }

    private void initAnswerLabels(){
        for(Answer answer: answers){
            Label label = new Label(answer.getAnswer(), getSkin());
            this.add(label)
                .expand()
                .left()
                .padLeft(25f)
                .padRight(25f);
            this.row();
            this.pack();
        }
    }


}
