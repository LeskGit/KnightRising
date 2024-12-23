package io.github.KnightRising;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import static com.badlogic.gdx.Gdx.gl;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class KnightRising extends Game {

    private SpriteBatch batch;
    private Sprite player;
    private TextureAtlas atlas;
    private OrthographicCamera camera;
    private FitViewport viewport;
    final float VIEWPORT_WIDTH = 500f;
    final float VIEWPORT_HEIGHT = 500f;

    @Override
    public void create() {
        batch = new SpriteBatch();
        atlas = new TextureAtlas(Utils.getInternalPath("atlas/player_atlas.atlas"));
        player = new Sprite(atlas.findRegion("player_idle_1"));
        camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        viewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, camera);
        setScreen(new GameScreen(this));
    }

    public void render() {
        super.render();
    }

    public void dispose() {
        batch.dispose();
        atlas.dispose();
    }

    public Sprite getPlayer() {
        return this.player;
    }

    public FitViewport getViewport() {
        return this.viewport;
    }

    public OrthographicCamera getCamera() {
        return this.camera;
    }

    public SpriteBatch getBatch() {
        return this.batch;
    }
}
