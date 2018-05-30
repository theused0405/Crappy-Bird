package com.jay.crappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jay.crappybird.CrappyBird;

import static java.awt.Color.black;

public class GameOver extends State {

    private Texture background;
    private Texture gameover;
    private BitmapFont font;
    private int score;
    private int highscore;
    private int prevHighScore;
    private static Preferences prefs;
    private Texture playBtn;



    public GameOver(GameStateManager gsm, int score) {
        super(gsm);
        this.score = score;
        cam.setToOrtho(false, CrappyBird.WIDTH / 2, CrappyBird.HEIGHT / 2);
        background = new Texture("bg.png");
        gameover = new Texture("gameover.png");
        playBtn = new Texture("playbtn.png");
        font = new BitmapFont();
        prefs = Gdx.app.getPreferences("CrappyBird");

        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }

        prevHighScore = getHighScore();

        if (prevHighScore < this.score) {
            setHighScore(this.score);
            highscore = this.score;
        } else {
            highscore = prevHighScore;
        }

    }



    public static void setHighScore(int val) {
        prefs.putInteger("highScore", val);
        prefs.flush();
    }

    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }

    }

    @Override
    public void update(float dt) {
        handleInput();

    }


    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(playBtn ,40, 200);
        sb.draw(gameover, 40, 300);
        font.setColor(Color.GREEN);
        font.draw(sb, "Player Score: " + score, 40, 170);
        font.setColor(Color.CYAN);
        font.draw(sb, "Player HighScore: " + highscore, 40, 150);
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        gameover.dispose();
        playBtn.dispose();

    }
}