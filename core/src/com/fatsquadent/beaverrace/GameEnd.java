package com.fatsquadent.beaverrace;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Created by ludde on 6/15/15.
 */
public class GameEnd implements Screen {
    String scoreTxt = "Störst ölsug hade bäwer #";
    String scoreTxt2 = " och tilldelas därför sex välkylda";
    Stage stage;
    SpriteBatch batch;
    BitmapFont font;
    GameMain game;

    int id;

    public GameEnd(GameMain game)
    {
        this.game = game;
        this.id = -1;
    }

    public void setWinner(int id)
    {
        this.id = id;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        FileHandle fontFile = Gdx.files.internal("OpenSans-CondLight.ttf");
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);

        FreeTypeFontGenerator.FreeTypeFontParameter p = new FreeTypeFontGenerator.FreeTypeFontParameter();
        p.size = 72;

        font = generator.generateFont(p);
        font.setColor(1f, 0f, 0f, 1f);
        stage = new Stage();


        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        font.setColor(0.0f, 0.0f, 1.0f, 1.0f); // tint font blue

        BeaverTreat fstTreat = new BeaverTreat(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);

        for (int i=0; i < 6;i++) {
            stage.addActor(new BeaverTreat((int)(fstTreat.getX()+i*fstTreat.getWidth()+5), (int)fstTreat.getY()));
        }

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        font.setColor(0.0f, 0.0f, 1.0f, 1.0f); // tint font blue
        batch.begin();
        font.draw(batch, scoreTxt+this.id+scoreTxt2, Gdx.graphics.getWidth()/6  , Gdx.graphics.getHeight()/2+250);
        batch.end();

        if (Gdx.input.isTouched()) {
           game.setBeaverRunner();
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
