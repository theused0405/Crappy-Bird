package com.jay.crappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.jay.crappybird.states.GameStateManager;
import com.jay.crappybird.states.MenuState;

public class CrappyBird extends ApplicationAdapter {
	//Global variable for window size and title
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final String TITLE = "Crappy Bird";

	private GameStateManager gsm;
	private SpriteBatch batch;
	Texture gameover;

	@Override
	public void create() {
		batch = new SpriteBatch();
		gameover = new Texture("gameover.png");
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));
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