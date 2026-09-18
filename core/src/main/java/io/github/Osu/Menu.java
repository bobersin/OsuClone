package io.github.Osu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import static io.github.Osu.Main.viewport;

public class Menu {
    protected Vector2 mouse;
    private boolean active;

    public Menu() {
        mouse = new Vector2();
    }

    public void update(float dt) {
        if (!active) {
            return;
        }

        mouse.set(Gdx.input.getX(), Gdx.input.getY());
        viewport.unproject(mouse);
    }

    public void load() {
        active = true;
    }
    public void unload() { active = false;}

    public boolean isActive() {return active;}
}
