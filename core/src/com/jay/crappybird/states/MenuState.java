package com.jay.crappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.jay.crappybird.CrappyBird;




public class MenuState extends State {
    private Texture background;
    private Texture playBtn;

    public static final int WIDTH = 1080;
    public static final int HEIGHT = 1920;



    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("bg.png");
        playBtn = new Texture("playbtn.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        //Draw the background
        sb.draw(background ,0, 0, WIDTH, HEIGHT);
        //Draw the play button
        sb.draw(playBtn ,300, 700);
        //Finish off rendering
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        System.out.println("Menu State Disposed");
    }
}
