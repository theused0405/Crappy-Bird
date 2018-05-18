package com.jay.crappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jay.crappybird.CrappyBird;

public class GameOver extends State {

    private Texture background;
    private Texture gameover;
    private BitmapFont font;
    private int score;
    private int highscore;
    private int prevHighScore;
    private static Preferences prefs;

    public GameOver(GameStateManager gsm, int score) {
        super(gsm);
        this.score = score;
        cam.setToOrtho(false, CrappyBird.WIDTH / 2, CrappyBird.HEIGHT / 2);
        background = new Texture("bg.png");
        gameover = new Texture("gameover.png");
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
        sb.draw(gameover, cam.position.x - gameover.getWidth() / 2, cam.position.y);
        font.draw(sb, "Player Score: " + score, cam.position.x, 170);
        font.draw(sb, "Player HighScore: " + highscore, cam.position.x, 150);
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        gameover.dispose();

    }
}