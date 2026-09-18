package io.github.Osu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button {
    protected Rectangle dimensions;
    protected Rectangle currentdimensions;
    protected Rectangle animationDimensions;
    private final TextureRegion textureRegion;
    private boolean hovering;
    private boolean holding;
    private final float animationSpeed = 4.5f;
    private float animationProgress = 0f;

    public Button(int x, int y, int width, int height) {
        Texture texture = new Texture("1x1.png");
        textureRegion = new TextureRegion(texture);
        dimensions = new Rectangle(x, y, width, height);
        animationDimensions = new Rectangle(dimensions);
        currentdimensions = new Rectangle(dimensions);
    }

    public Button(int x, int y, int width, int height, String texturePath) {
        Texture texture = new Texture(texturePath);
        textureRegion = new TextureRegion(texture);
        dimensions = new Rectangle(x, y, width, height);
        animationDimensions = new Rectangle(dimensions);
        currentdimensions = new Rectangle(dimensions);
    }

    public void update(float dt, Vector2 mouse) {
        handleMouse(mouse);
        if (animationProgress < 1f) {
            float changeX = dimensions.getX() - animationDimensions.getX();
            float newX = (float) (animationDimensions.getX() + (changeX * (Math.pow(animationProgress,2))));
            currentdimensions.setX(newX);
            if (animationDimensions.getX() != dimensions.getX()) {
                //todo
            }
            animationProgress += (dt * animationSpeed);
        }
    }

    private void handleMouse(Vector2 mouse) {
        if (dimensions.contains(mouse.x, mouse.y)) {
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                holding = true;
            } else if (holding) {
                clicked();
                holding = false;
            }

            hovering = true;
            return;
        }
        hovering = false;
    }

    public void clicked() {
    }

    public void changePosition(float x,float y) {
        animationProgress = 0f;
        dimensions.setPosition(x,y);
    }

    public boolean isHovering() {
        return hovering;
    }

    public boolean isHolding() {
        return holding;
    }

    public Rectangle getDimensions() {
        return dimensions;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(textureRegion, currentdimensions.x, currentdimensions.y, currentdimensions.width, currentdimensions.height);
    }
}
