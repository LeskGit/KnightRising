package io.github.KnightRising;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import jdk.internal.foreign.SystemLookup;

public class Player extends Sprite {

    private TextureAtlas atlas;
    private float speed;

    private Polygon playerPolygon;

    public Player(TextureAtlas atlas) {
        super(atlas.findRegion("player_idle_1"));
        float[] vertices = {
            15f, 0,                        // Bas gauche
            getWidth() * 0.7f, 0,               // Bas droite
            getWidth() * 0.7f, getHeight()*0.6f,     // Haut droite
            15f, getHeight() * 0.6f              // Haut gauche
        };
        playerPolygon = new Polygon(vertices);
        playerPolygon.setPosition(getX(), getY());

        this.speed = 100f;
    }



    public float getSpeed() {
        return this.speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Polygon getPlayerPolygon() {
        return this.playerPolygon;
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

        playerPolygon.setPosition(getX(), getY());
    }




}
