package io.github.Osu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;

import static io.github.Osu.Main.selectionMenu;
import static io.github.Osu.Main.viewport;

public class MainMenu extends Menu {
    private final Button osuLogo;
    private boolean logoClicked;


    private final Button playButton;
    private final Button exitButton;

    public MainMenu() {
        super();

        int osuWidth = 500;
        int osuHeight = 500;

        double clickedX = (viewport.getWorldWidth() - osuWidth)/2.5;
        double clickedY = (viewport.getWorldHeight() - osuHeight)/2;

        int buttonHeight = 75;
        int buttonWidth = osuWidth/3;
        int buttonOffset = 75;

        playButton = new Button((int) (clickedX + (double) osuWidth /2) + osuWidth/3, (int) (clickedY + (double) osuHeight /2) - buttonHeight/2 + buttonOffset, buttonWidth, buttonHeight) {
            @Override
            public void clicked() {
                if (!logoClicked) return;
                unload();
                selectionMenu.load();
            }
        };

        exitButton = new Button((int) (clickedX + (double) osuWidth /2) + osuWidth/3, (int) (clickedY + (double) osuHeight /2) - buttonHeight/2 - buttonOffset, buttonWidth, buttonHeight) {
            @Override
            public void clicked() {
                if (!logoClicked) return;
                Gdx.app.exit();
            }
        };

        osuLogo = new Button((int) ((viewport.getWorldWidth() - osuWidth)/2), (int) clickedY, osuWidth, osuHeight, "Osulogo.png") {
            @Override
            public void clicked() {
                if (!logoClicked) super.changePosition((float) ((viewport.getWorldWidth() - osuWidth)/2.5), (viewport.getWorldHeight() - osuHeight)/2);
                logoClicked = true;
            }
        };
    }

    public void update(float dt) {
        super.update(dt);

        playButton.update(dt, mouse);
        exitButton.update(dt, mouse);
        osuLogo.update(dt, mouse);
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
