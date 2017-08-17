package com.trando.personalitytester.dialogue;

import com.badlogic.gdx.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;

/**
 * Created by Cameron on 3/3/2017.
 */
public class DialogueBox extends Table {
    private ActionState state;
    private String text;
    private String copy;
    private StringBuilder currentlyShownText;
    private Label textLabel;

    private float timePerAnimation = .07f;
    private float animationTimer = 0.0f;

    public DialogueBox(Skin skin, String text) {
        super(skin);
        this.text = text;

        copy = text;
        state = ActionState.IDLE;

        currentlyShownText = new StringBuilder();
        this.setBackground("dialog.9");

        textLabel = new Label(currentlyShownText, skin);
        textLabel.setWrap(true);
        this.add(textLabel)
            .expandY()
            .growX()
            .top()
            .left()
            .padLeft(25f)
            .padRight(25f)
            .padBottom(10f);
    }

    public void animateText() {
        state = ActionState.ANIMATING;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        animationTimer += delta;

        if (state == ActionState.ANIMATING) {
            if (animationTimer > timePerAnimation && copy.length() > 0) {
                currentlyShownText.append(copy.substring(0, 1));
                copy = copy.substring(1);
                setText(currentlyShownText.toString());
                animationTimer = 0.0f;
            } else if (copy.length() == 0) {
                state = ActionState.IDLE;
            }
        }
    }

    public void handleInput(int keyCode) {
        switch (keyCode) {
            case Input.Keys.ENTER:
        }
    }

    public boolean isFinished() {
        return state == ActionState.IDLE;
    }

    public void setText(String string) {
        textLabel.setText(string);
    }

    public String getText() {
        return text;
    }

    public void forceFinishAnimation() {
        copy = "";
        currentlyShownText.setLength(text.length());
        currentlyShownText.replace(0, currentlyShownText.length(), text);
        setText(currentlyShownText.toString());
        state = ActionState.IDLE;
    }
}

enum ActionState {
    ANIMATING, IDLE;
}
