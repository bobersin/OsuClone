package io.github.Osu;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;

import static io.github.Osu.Main.*;

public class MainMenu extends Menu {
    private final Button osuLogo;
    private boolean logoClicked;


    private final Button playButton;
    private final Button exitButton;

    public MainMenu() {
        super();

        float osuWidth = (1024 * screenScale);
        float osuHeight = (1024 * screenScale);

        double clickedX = (viewport.getWorldWidth() - osuWidth)/2.5;
        double clickedY = (viewport.getWorldHeight() - osuHeight)/2;

        float buttonHeight = 75 * screenScale;
        float buttonWidth = osuWidth/3;
        float buttonOffset = 75 * screenScale;

        playButton = new Button((int) ((clickedX + osuWidth /2) + osuWidth/3), (int) ((clickedY + osuHeight /2) - buttonHeight/2 + buttonOffset), (int) buttonWidth, (int) buttonHeight) {
            @Override
            public void clicked() {
                if (!logoClicked) return;
                unload();
                selectionMenu.load();
            }
        };

        exitButton = new Button((int) ((clickedX + osuWidth /2) + osuWidth/3), (int) ((clickedY + osuHeight /2) - buttonHeight/2 - buttonOffset), (int) buttonWidth, (int) buttonHeight) {
            @Override
            public void clicked() {
                if (!logoClicked) return;
                Gdx.app.exit();
            }
        };

        osuLogo = new Button((int) ((viewport.getWorldWidth() - osuWidth)/2), (int) clickedY, (int) osuWidth, (int) osuHeight, "Osulogo.png") {
            @Override
            public void clicked() {
                if (!logoClicked) super.changePosition((float) ((viewport.getWorldWidth() - osuWidth)/3), (viewport.getWorldHeight() - osuHeight)/2);
                logoClicked = true;
            }
        };
    }

    public void update(float dt) {
        super.update(dt);

        playButton.update(dt, mouse);
        exitButton.update(dt, mouse);
        osuLogo.update(dt, mouse);

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            if (logoClicked) {
                osuLogo.changePosition(((viewport.getWorldWidth() - osuLogo.getDimensions().getWidth())/2), (viewport.getWorldHeight() - osuLogo.getDimensions().getHeight())/2);
                logoClicked = false;
            } else {
                Gdx.app.exit();
            }
        }
    }

    public void draw(SpriteBatch batch) {
        if (!isActive()) {
            return;
        }
        batch.begin();
        playButton.draw(batch);
        exitButton.draw(batch);
        osuLogo.draw(batch);
        batch.end();
    }
}
