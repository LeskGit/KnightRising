package io.github.KnightRising;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import static com.badlogic.gdx.Gdx.gl;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class GameScreen implements Screen {

    private KnightRising game;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private FitViewport viewport;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;
    float worldWidth;
    float worldHeight;
    final float unitScale = 1f / 32f;
    private MapLayer layerCollision;



    public GameScreen(KnightRising game) {
        this.game = game;
        this.batch = new SpriteBatch();

        this.map = new TmxMapLoader().load("Map/gameMap.tmx");
        this.mapRenderer = new OrthogonalTiledMapRenderer(map);
        this.layerCollision = map.getLayers().get("collision");

        worldHeight = map.getProperties().get("height", Integer.class) * 32f;
        worldWidth = map.getProperties().get("width", Integer.class) * 32f;
        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(worldWidth, worldHeight, camera);

    }

    @Override
    public void show() {
        this.game.getPlayer().setScale(1.3f);
        this.game.getPlayer().setPosition(worldWidth/2, worldHeight/2);
    }

    @Override
    public void render(float v) {
        game.getPlayer().update(Gdx.graphics.getDeltaTime());
        logic();
        draw();

    }


    public void logic() {
        updateCamera();
        cameraLimit();
        playerLimit();
    }

    private void updateCamera() {
        float x = game.getPlayer().getX();
        float y = game.getPlayer().getY();

        camera.position.set(x, y, 0);
        camera.zoom = 0.8f;
        camera.update();


    }

    private void cameraLimit() {
        float cameraX = camera.position.x;
        float cameraY = camera.position.y;

        if (cameraX < 0) {
            cameraX = 0;
        }
        else if (cameraX > worldWidth) {
            cameraX = worldWidth;
        }

        else if (cameraY < 0) {
            cameraY = 0;
        }

        else if (cameraY > worldHeight) {
            cameraY = worldHeight;
        }

        camera.position.set(cameraX, cameraY, 0);
        camera.update();

    }

    private void playerLimit() {
        float playerX = game.getPlayer().getX();
        float playerY = game.getPlayer().getY();

        if (playerX < 0) {
            playerX = 0;
        }
        else if (playerX > worldWidth) {
            playerX = worldWidth;
        }

        else if (playerY < 0) {
            playerY = 0;
        }

        else if (playerY > worldHeight) {
            playerY = worldHeight;
        }

        game.getPlayer().setPosition(playerX, playerY);

    }

    public void draw() {
        ScreenUtils.clear(0, 0, 0, 1);
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        mapRenderer.setView(camera);
        mapRenderer.render();
        batch.begin();

        game.getPlayer().draw(batch);

        batch.end();
    }
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
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
        mapRenderer.dispose();
        map.dispose();
    }
}
