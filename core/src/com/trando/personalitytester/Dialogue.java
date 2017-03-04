package com.trando.personalitytester;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.*;

/**
 * Created by Cameron on 3/3/2017.
 */
public class Dialogue extends Table {
    private String text;
    private String copy;
    private StringBuilder currentlyShownText;
    private Label textLabel;

    private float timePerAnimation = .1f;
    private float animationTimer = 0.0f;

    public Dialogue(Skin skin){
        super(skin);
        text = "Witness this! This is the ability of a programmer who cannot draw at all! And this is also the result of an embarrassing amount of time that could have been spent doing something more productive!";
        copy = text;

        currentlyShownText = new StringBuilder();

        this.setBackground("dialog.9");
        textLabel = new Label(currentlyShownText, skin);
        textLabel.setWrap(true);
        this.add(textLabel).expandY().growX().top().left().padLeft(25f).padRight(25f);
    }

    @Override
    public void act(float delta){
        super.act(delta);
        animationTimer += delta;

        if(animationTimer > timePerAnimation && copy.length() > 0){
            currentlyShownText.append(copy.substring(0,1));
            copy = copy.substring(1);
            setText(currentlyShownText.toString());
            animationTimer = 0.0f;
            textLabel.pack();
        }
    }

    public void setText(String string){
        textLabel.setText(string);
    }
}
