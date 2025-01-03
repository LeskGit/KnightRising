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


    private Player player;
    private TextureAtlas atlas;


    @Override
    public void create() {
        atlas = new TextureAtlas(Utils.getInternalPath("atlas/player_atlas.atlas"));
        player = new Player(atlas);
        setScreen(new GameScreen(this));
    }

    public void render() {
        super.render();
    }

    public void dispose() {
        atlas.dispose();
    }

    public Player getPlayer() {
        return this.player;
    }


}
