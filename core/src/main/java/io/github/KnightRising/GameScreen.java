package io.github.KnightRising;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.ScreenUtils;

import static com.badlogic.gdx.Gdx.gl;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class GameScreen implements Screen {

    private KnightRising game;
    private Texture background;
    final float LIMI_DOWN = 0f;
    final float LIMI_UP = 200f;
    final float LIMI_RIGHT = 200f;
    final float LIMI_LEFT = 0f;

    public GameScreen(KnightRising game) {
        this.game = game;
        this.background = new Texture(Utils.getInternalPath("background.png"));
    }





    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        input();
        logic();
        draw();

    }

    private void input() {
        float speed = 50f;
        float delta = Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.game.getPlayer().translateX(speed * delta);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.game.getPlayer().translateX(-speed * delta);
        }

        else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            this.game.getPlayer().translateY(speed * delta);
        }

        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
        this.game.getPlayer().translateY(-speed * delta);}
    }

    public void logic() {
        updateCamera();
        cameraLimit();
        playerLimit();
    }

    private void updateCamera() {
        float x = game.getPlayer().getX();
        float y = game.getPlayer().getY();

        game.getCamera().position.set(x, y, 0);
        game.getCamera().zoom = 0.5f;
        game.getCamera().update();


    }

    private void cameraLimit() {
        float cameraX = game.getCamera().position.x;
        float cameraY = game.getCamera().position.y;

        if (cameraX < LIMI_LEFT) {
            cameraX = LIMI_LEFT;
        }
        else if (cameraX > LIMI_RIGHT) {
            cameraX = LIMI_RIGHT;
        }

        else if (cameraY < LIMI_DOWN) {
            cameraY = LIMI_DOWN;
        }

        else if (cameraY > LIMI_UP) {
            cameraY = LIMI_UP;
        }

        game.getCamera().position.set(cameraX, cameraY, 0);
        game.getCamera().update();



    }

    private void playerLimit() {
        float playerX = game.getPlayer().getX();
        float playerY = game.getPlayer().getY();

        if (playerX < LIMI_LEFT) {
            playerX = LIMI_LEFT;
        }
        else if (playerX > LIMI_RIGHT) {
            playerX = LIMI_RIGHT;
        }

        else if (playerY < LIMI_DOWN) {
            playerY = LIMI_DOWN;
        }

        else if (playerY > LIMI_UP) {
            playerY = LIMI_UP;
        }

        game.getPlayer().setPosition(playerX, playerY);

    }

    public void draw() {
        ScreenUtils.clear(0, 0, 0, 1);
        game.getViewport().apply();
        game.getBatch().setProjectionMatrix(game.getViewport().getCamera().combined);
        game.getBatch().begin();
        game.getBatch().draw(background, 0, 0, game.getViewport().getWorldWidth(), game.getViewport().getWorldHeight());
        game.getPlayer().draw(game.getBatch());
        game.getBatch().end();
    }
    @Override
    public void resize(int width, int height) {
        game.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        game.dispose();
        background.dispose();
    }
}
