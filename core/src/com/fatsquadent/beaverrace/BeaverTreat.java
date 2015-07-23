package com.fatsquadent.beaverrace;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by ludde on 6/17/15.
 */
public class BeaverTreat extends Actor {
    Texture texture = new Texture(Gdx.files.internal("millennium.jpg"));
    public BeaverTreat(int x, int y)
    {
        setBounds(x-texture.getWidth(), y, texture.getWidth(),texture.getHeight());
    }

    @Override
    public void draw(Batch batch, float alpha){
        batch.draw(texture, this.getX(), getY());
    }

}
