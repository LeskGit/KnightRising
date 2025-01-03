package io.github.KnightRising;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.math.Rectangle;

public class Player extends Sprite {

    private TextureAtlas atlas;
    private float speed;
    private Rectangle playerRect;

    public Player(TextureAtlas atlas) {
        super(atlas.findRegion("player_idle_1"));
        this.atlas = atlas;
        playerRect = getBoundingRectangle();
        this.speed = 100f;
    }

    public float getSpeed() {
        return this.speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Rectangle getPlayerRect() {
        return this.playerRect;
    }


    public void update(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            this.setY(this.getY() + speed * delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            this.setY(this.getY() - speed * delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            this.setX(this.getX() - speed * delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            this.setX(this.getX() + speed * delta);
        }

        playerRect.set(getX(), getY(), getWidth(), getHeight());
    }




}
