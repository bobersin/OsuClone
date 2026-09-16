package io.github.Osu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button {
    private Rectangle dimensions;
    private Texture texture;
    private TextureRegion textureRegion;
    private boolean hovering;

    public Button(int x, int y, int width, int height) {
        texture = new Texture("1x1.png");
        textureRegion = new TextureRegion(texture);
        dimensions = new Rectangle(x, y, width, height);
    }

    public void update(Vector2 mouse) {
        if (dimensions.contains(mouse.x, mouse.y)) {
            hovering = true;

            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                clicked();
            }
        } else {
            hovering = false;
        }
    }

    public void clicked() {
    }

    public void draw(SpriteBatch batch) {
        batch.begin();
        batch.draw(textureRegion, dimensions.x, dimensions.y, dimensions.width, dimensions.height);
        batch.end();
    }
}
