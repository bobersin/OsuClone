package io.github.Osu;

import java.util.ArrayList;
import java.io.IOException;
import com.badlogic.gdx.Gdx;

public class GameMenu extends Menu {
    private Song song;
    private ArrayList<HitCircle> hitCircles;

    public GameMenu() {
        super();
        hitCircles = new ArrayList<HitCircle>();
    }

    public void start(Song currentSong) {
        song = currentSong;
        Gdx.app.log("TAG", "Started");
    }

    public void update(float dt) {
        super.update(dt);
        Gdx.app.log("TAG", "main menu update");

        int i = 0;
        boolean condition = true;   
        while (condition) {
            try {
                String raw = song.getValue("HitObjects", Integer.toString(i));
                hitCircles.add(new HitCircle(raw, 1));
            } catch (Exception iqnored) {
                condition = false;
            }
        }

        Gdx.app.log("TAG", hitCircles.toString());
    }
}
