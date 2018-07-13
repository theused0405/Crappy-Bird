package com.jay.crappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;




public class MenuState extends State {
    private Texture background;
    private Texture playBtn;



    public static final int WIDTH = 1080;
    public static final int HEIGHT = 1920;

    BitmapFont font = new BitmapFont();


    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("bg1.png");
        playBtn = new Texture("playbtn.png");
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
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
        sb.draw(background, 0, 0, WIDTH, HEIGHT);
        //Draw the play button
        sb.draw(playBtn, 500, 700);
        //Finish off rendering
        font.setColor(Color.FIREBRICK);
        font.getData().setScale(5,5);
        font.draw(sb, "Press Play to Start Game ", 130, 900);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        System.out.println("Menu State Disposed");
    }
}
