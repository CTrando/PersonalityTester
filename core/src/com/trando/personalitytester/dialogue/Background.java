package com.trando.personalitytester.dialogue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Background {

    private TextureRegion background;
    private Texture normal;
    private Texture distortion;
    private ShaderProgram shader;
    private Skin skin;

    private float moveFactor = 0;
    private float speed = .04f;

    private final FileHandle vertexShader = Gdx.files.internal("vertex.glsl");
    private final FileHandle fragmentShader = Gdx.files.internal("fragment.glsl");

    public Background(Skin skin) {
        this.skin = skin;

        this.normal = new Texture(Gdx.files.internal("normalMap.png"));
        normal.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        this.distortion = new Texture(Gdx.files.internal("distortionMap.jpg"));
        distortion.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        /*Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);
        pixmap.setColor(1,0,0,1);
        pixmap.fill();
        this.background = new TextureRegion(new Texture(pixmap));
        pixmap.dispose();*/
        this.background = skin.getRegion("background");

        this.shader = new ShaderProgram(vertexShader, fragmentShader);
        if(shader.isCompiled()) System.out.println("COMPILED CORRECTLY ");
        ShaderProgram.pedantic = false;
    }

    public void render(SpriteBatch batch) {
        batch.begin();
        batch.setShader(shader);

        moveFactor += speed*Gdx.graphics.getDeltaTime();
        moveFactor%=10;
        shader.setUniformf("u_time", moveFactor);
        shader.setUniformi("u_distortion_map", 2);
        shader.setUniformi("u_normal_map", 3);

        distortion.bind(2);
        normal.bind(3);
        batch.draw(background, 0, 0);
        batch.setShader(null);
        Gdx.gl20.glActiveTexture(GL20.GL_TEXTURE0);
        batch.end();
    }
}
