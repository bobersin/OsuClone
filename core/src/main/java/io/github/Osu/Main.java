package io.github.Osu;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all
 * platforms.
 */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private MainMenu mainMenu;
    SelectionMenu selectionMenu;
    private FitViewport viewport;
    private OrthographicCamera camera;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(1920, 1080, camera);
        batch = new SpriteBatch();
        selectionMenu = new SelectionMenu(viewport);
        mainMenu = new MainMenu(viewport, selectionMenu);
        mainMenu.load();
    }

    @Override
    public void render() {
        update();

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.setProjectionMatrix(camera.combined);
        mainMenu.draw(batch);
        selectionMenu.draw(batch);
    }

    public void update() {
        mainMenu.update();
        selectionMenu.update();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }
}
