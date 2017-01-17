package com.fatsquadent.beaverrace;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

/**
 * Created by ludde on 2016-11-12.
 */
public class BeaverActor extends Actor {

    private final float speed;
    private final int id;
    private final Texture texture;
    private boolean isSlowStarting;

    public BeaverActor(int id, float x, float y, float speed){
        this.isSlowStarting = false;
        this.speed = speed;
        this.id = id;
        this.texture = new Texture(Gdx.files.internal("beaver.jpg"));
        setX(x);
        setY(y);
        setBounds(getX(),getY(),texture.getWidth(),texture.getHeight());
        setPosition(getX(), getY());
        MoveToAction moveAction = new MoveToAction();
        moveAction.setPosition(Gdx.graphics.getWidth() - getWidth(), y);
        moveAction.setDuration(speed);
        addAction(moveAction);
    }

    @Override
    public void draw(Batch batch, float alpha){
        batch.draw(texture, this.getX(), getY());
    }

    public String getName()
    {
        return "Bäwer #"+id;
    }

    public int getId() {
        return id;
    }

    public float getSpeed() {
        return speed;
    }

    public void boost() {
        if (isSlowStarting) {
            if (getActions().size > 0) {
                Action action = getActions().get(0);
                if (action instanceof MoveToAction) {
                    MoveToAction mta = (MoveToAction) action;
                    mta.setDuration(mta.getDuration()-mta.getDuration()*0.002f);
                }
            }
        }
    }

    @Override
    public String toString() {
        return String.format("Bäver #%d: x: %.4f, y: %.4f, speed: %.4f",getId(),getX(), getY(), getSpeed());
    }

    public void setSlowStarting(boolean value) {
        isSlowStarting = value;
    }

    public boolean isSlowStarting() {
        return isSlowStarting;
    }
}