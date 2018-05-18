package com.jay.crappybird.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.jay.crappybird.CrappyBird;
import com.jay.crappybird.sprites.Tube;
import com.jay.crappybird.sprites.Bird;
import static com.jay.crappybird.sprites.Tube.TUBE_WIDTH;

public class PlayState extends State {
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -50;

    private Texture background;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;
    private Bird bird;
    private Texture gameoverImg;
    public int score;

    private boolean gameover;
    private Array<Tube> tubes;



    protected PlayState(GameStateManager gsm) {
        super(gsm);
        score = 0;
        ground = new Texture("ground.png");
        background = new Texture("bg.png");
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);
        bird = new Bird(50, 200);


        tubes = new Array<Tube>();
        gameoverImg = new Texture("gameover.png");

        for(int i = 0; i < TUBE_COUNT; i++) {
            tubes.add(new Tube(i * (TUBE_SPACING + TUBE_WIDTH)));

            gameover = false;
        }
        cam.setToOrtho(false, CrappyBird.WIDTH / 2, CrappyBird.HEIGHT / 2);
    }


    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()) {
            if(gameover)
                gsm.set(new PlayState(gsm));
            else
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80;

        for(int i = 0; i < tubes.size; i ++) {
            Tube tube = tubes.get(i);
            if(cam.position.x - (cam.viewportWidth / 2) > tube.getPosBotTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPosTopTube().x + ((TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }

            {
                if (tube.collides(bird.getBounds()) || bird.onGround())
                    gsm.set(new GameOver(gsm, score));
                if (bird.getPosition().x > (score + 1) * (com.jay.crappybird.sprites.Tube.TUBE_WIDTH + TUBE_SPACING + bird.getBounds().width / 2)) {
                    score++;

                }
            }
        }

        if(bird.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET) {
            gameover = true;
            bird.colliding = true;
        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, cam.position.x - (cam.viewportWidth / 2), 0);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        for (Tube tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }

        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        if(gameover)
            sb.draw(gameoverImg, cam.position.x - gameoverImg.getWidth() / 2, cam.position.y);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        ground.dispose();
        bird.dispose();
        for(Tube tube : tubes) {
            tube.dispose();
        }
        System.out.println("Play State Disposed");
    }

    private void updateGround() {
        if(cam.position.x - (cam.viewportWidth / 2) > groundPos1.x + ground.getWidth()) {
            groundPos1.add(ground.getWidth() * 2, 0);
        }
        if(cam.position.x - (cam.viewportWidth / 2) > groundPos2.x + ground.getWidth()) {
            groundPos2.add(ground.getWidth() * 2, 0);
        }
    }
}
