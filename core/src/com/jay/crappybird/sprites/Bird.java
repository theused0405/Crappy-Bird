package com.jay.crappybird.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird {
    private static final int GRAVITY = -5;
    public static final int MOVEMENT = 100;
    private Vector3 position;
    private Vector3 velocity;
    private Texture bird;
    private Rectangle bounds;
    public boolean colliding;
    public int x, y;
    public double width;


    public Bird(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0 ,0);
        bird = new Texture("bird.png");
        bounds = new Rectangle(x, y, bird.getWidth(), bird.getHeight());
        this.width = bird.getWidth();
    }


    public void update(float dt) {
        if(position.y > 0) {
            velocity.add(0, GRAVITY, 0);
        }
        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y, 0);
        position.add(0, velocity.y, 0);

        if(position.y < 80) {
            position.y = 80;
        }

        velocity.scl(1 / dt);

        bounds.setPosition(position.x, position.y);
    }

    public void jump() {
        velocity.y = 75;
    }

    public void dispose() {
        bird.dispose();
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return bird;
    }


    public boolean onGround() {
        if (position.y < 83)
            return true;
        return false;
    }
}
