package com.fatsquadent.beaverrace;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * Created by ludde on 6/15/15.
 */
public class BeaverRunner implements Screen {

    private enum RunnerState { NOT_RUNNING, RUNNING, FINSIHED, PAUSED}

    private final GameMain gameMain;

    private float beaverTreatWidth = 0f;

    private RunnerState state = RunnerState.NOT_RUNNING;

    public BeaverRunner(GameMain gameMain)
    {
        this.gameMain = gameMain;
    }



    public class MyActor extends Actor {

        Texture texture = new Texture(Gdx.files.internal("beaver.jpg"));
        public boolean started = false;
        private int id;

        public MyActor(int id){
            this.id = id;
            setBounds(getX(),getY(),texture.getWidth(),texture.getHeight());
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
    }
    private Stage stage;
    private boolean running = true;
    private MyActor winner = null;
    private Table table2;

    private Actor createBeaver(int i, float y, float speed)
    {
        MyActor myActor = new MyActor(i);
        myActor.setPosition(0f, y);


        MoveToAction moveAction = new MoveToAction();
        moveAction.setPosition(Gdx.graphics.getWidth() - myActor.getWidth(), y);
        moveAction.setDuration(speed);
        myActor.addAction(moveAction);

        return myActor;
    }
    @Override
    public void show () {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);


        float height = Gdx.graphics.getHeight()/6f+5;


        for (int i=0; i <6; i++) {
            // int speed = Math.Random(40,55);
            stage.addActor(createBeaver(6-i, i * height, 4f+(float)Math.random()));
            BeaverTreat t = new BeaverTreat(Gdx.graphics.getWidth(), i * (int)height);
            beaverTreatWidth = t.getWidth();
            stage.addActor(t);
        }
        
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
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        switch (state) {

            case NOT_RUNNING:
                if (Gdx.input.isTouched()) {
                    state = RunnerState.RUNNING;
                }
                break;
            case RUNNING:

                stage.act(Gdx.graphics.getDeltaTime());

                
                for (Actor a : stage.getActors()) {
                    if (a instanceof MyActor) {
                        if (a.getRight() >= (Gdx.graphics.getWidth()-beaverTreatWidth)) {
                            winner = (MyActor) a;
                            gameMain.setGameEnd(winner.getId());
                            state = RunnerState.FINSIHED;

                            break;
                        }
                    }
                }
                break;
            case FINSIHED:

                stage.act(Gdx.graphics.getDeltaTime());
                //table2.setVisible(true);
                break;
            case PAUSED:

                state = RunnerState.NOT_RUNNING;
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
