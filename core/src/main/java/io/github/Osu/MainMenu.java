package io.github.Osu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class MainMenu {
    private Texture osuLogoTexture;
    private TextureRegion osuLogoRegion;

    private boolean active;
    private FitViewport mainViewPort;
    private Vector2 mouse;

    private Button playButton;
    private Button exitButton;

    public MainMenu(FitViewport viewport, SelectionMenu selectionMenu) {
        osuLogoTexture = new Texture("Osulogo.png");
        osuLogoRegion = new TextureRegion(osuLogoTexture);
        mainViewPort = viewport;

        playButton = new Button(100, 850, 100, 50) {
            @Override
            public void clicked() {
                active = false;
                selectionMenu.load();
            }
        };

        exitButton = new Button(100, 700, 100, 50) {
            @Override
            public void clicked() {
                Gdx.app.exit();
            }
        };
    }

    public void update() {
        if (!active) {
            return;
        }

        mouse = new Vector2(
                Gdx.input.getX(),
                Gdx.input.getY());
        mainViewPort.unproject(mouse);
        playButton.update(mouse);
        exitButton.update(mouse);
    }

    public void draw(SpriteBatch batch) {
        if (!active) {
            return;
        }

        batch.begin();
        batch.draw(osuLogoRegion, 100, 100, 500, 500);
        batch.end();
        playButton.draw(batch);
        exitButton.draw(batch);
    }

    public void dispose() {
        osuLogoTexture.dispose();
    }

    public void load() {
        active = true;
    }
}
