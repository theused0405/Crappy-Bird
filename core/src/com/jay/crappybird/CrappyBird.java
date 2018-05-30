package com.jay.crappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.jay.crappybird.states.GameStateManager;
import com.jay.crappybird.states.MenuState;

import javax.swing.JFrame;

public class CrappyBird extends ApplicationAdapter {
	//Global variable for window size and title
	public static final int WIDTH = 510;
	public static final int HEIGHT = 900;
	public static final String TITLE = "Crappy Bird";


	private GameStateManager gsm;
	private SpriteBatch batch;
	private Texture gameover;
    private Music music;

	@Override
	public void create() {
		batch = new SpriteBatch();
		gameover = new Texture("gameover.png");
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));
        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}

	@Override
	public void dispose () {
		batch.dispose();

	}
}