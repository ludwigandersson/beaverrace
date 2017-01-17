package com.fatsquadent.beaverrace;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * Created by ludde on 6/15/15.
 */
public class BeaverRunner implements Screen {

    private float beaverTreatX = 0f;

    private enum RunnerState { NOT_RUNNING, RUNNING, FINSIHED, PAUSED}

    private final GameMain gameMain;

    private float beaverTreatWidth = 0f;

    private RunnerState state = RunnerState.NOT_RUNNING;

    public BeaverRunner(GameMain gameMain)
    {
        this.gameMain = gameMain;
    }




    private Stage stage;
    private boolean running = true;
    private BeaverActor winner = null;
    private Table table2;

    private BeaverActor createBeaver(int i, float x, float y, float speed)
    {
        System.out.println(String.format("Created beaver: %d, speed: %f", i, speed));
        BeaverActor myActor = new BeaverActor(i, x, y, speed);


        return myActor;
    }

    public int calcWinner() {
        int slowestBeaver = -1;
        float topspeed = 40f;
        for (int i=1; i <7; i++) {
            float speed = 4f + (float) Math.random();
            if (speed < topspeed) {
                topspeed = speed;
                slowestBeaver = i;
            }
        }

        return slowestBeaver;
    }

    @Override
    public void show () {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        float height = Gdx.graphics.getHeight()/6f;

        BeaverActor slowestBeaver = null;
        for (int i=0; i <6; i++) {
            // int speed = Math.Random(40,55);
            BeaverActor ba = createBeaver(6-i, Gdx.graphics.getWidth()*0.05f, i * height, 4f+(float)Math.random());
            //if (slowestBeaver == null || ba.getSpeed() > slowestBeaver.getSpeed())
            //    slowestBeaver = ba;

            stage.addActor(ba);



            BeaverTreat t = new BeaverTreat((int)(Gdx.graphics.getWidth()*0.97), i * (int)height);
            beaverTreatWidth = t.getWidth();
            beaverTreatX = t.getX();
            stage.addActor(t);
        }

        if (slowestBeaver != null)
            slowestBeaver.setSlowStarting(true);
        
        table2 = new Table();
        table2.setVisible(false);
        stage.addActor(table2);
        table2.setFillParent(true);
        table2.bottom();
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

        TextButton button2 = new TextButton("Button 2", skin);
        button2.addListener(new ChangeListener() {
                public void changed (ChangeEvent event, Actor actor) {
                        System.out.println("2!");
                }
        });
        button2.addListener(new InputListener() {
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                        System.out.println("touchDown 2");
                        return false;
                }
        });
        table2.add(button2);
    }


    @Override
    public void dispose() {
    }

    @Override
    public void render(float delta) {

        switch (state) {

            case NOT_RUNNING:
                //if (Gdx.input.isTouched())
                {
                    state = RunnerState.RUNNING;
                }
                break;
            case RUNNING:
                for (Actor a : stage.getActors()) {
                    if (a instanceof BeaverActor) {
                        BeaverActor b = (BeaverActor)a;
                        if ((a.getRight()+2*b.getSpeed())>= beaverTreatX) {
                            winner = (BeaverActor) a;
                            state = RunnerState.FINSIHED;
                            break;
                        }
                        if (b.isSlowStarting() && b.getRight() > beaverTreatX*0.5) {
                            b.boost();
                        }
                    }
                }


                // Draw next position
                Gdx.gl.glClearColor(1, 1, 1, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                stage.act(Gdx.graphics.getDeltaTime());
                break;
            case FINSIHED:

                gameMain.setGameEnd(winner);
               // stage.act(Gdx.graphics.getDeltaTime());
                //table2.setVisible(true);
                break;
            case PAUSED:

                //state = RunnerState.NOT_RUNNING;
                if (Gdx.input.isTouched())
                {
                    state = RunnerState.RUNNING;
                }
                break;
        }
        stage.draw();


    }



    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
        if (state == RunnerState.RUNNING) {
            state = RunnerState.PAUSED;
        }
    }

    @Override
    public void resume() {
        if (state == RunnerState.RUNNING) {
            state = RunnerState.PAUSED;
        }
    }

    @Override
    public void hide() {
        if (state == RunnerState.RUNNING) {
            state = RunnerState.PAUSED;
        }
    }
}
