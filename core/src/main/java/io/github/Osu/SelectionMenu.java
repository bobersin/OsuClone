package io.github.Osu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SelectionMenu {
    private boolean active;
    private FitViewport mainViewPort;
    private Vector2 mouse;
    private Beatmap[] beatmaps;
    private int beatmapHeight = 75;
    private int maxBeatmapWidth = 1920/3;
    private int SongHeight = 50;
    private int SongWidth = 1920/4;
    private int selectionIndex = 0;

    public SelectionMenu(FitViewport viewport) {
        mainViewPort = viewport;
        beatmaps = loadBeatMaps();
    }

    public void update() {
        if (!active) {
            return;
        }

        mouse = new Vector2(
                Gdx.input.getX(),
                Gdx.input.getY());
        mainViewPort.unproject(mouse);
    }

    public void draw(SpriteBatch batch) {
        if (!active) {
            return;
        }
        batch.begin();
        for (int i = 0; i < beatmaps.length; i++) {
            Texture beatmapTexture = new Texture("1x1.png");
            TextureRegion beatmapRegion = new TextureRegion(beatmapTexture);
            batch.draw(beatmapRegion, 1920 - maxBeatmapWidth, 900, maxBeatmapWidth, beatmapHeight);
        }
        batch.end();
    }

    public Beatmap[] loadBeatMaps() {
        List<Beatmap> beatmaps = new ArrayList<>(List.of(
            new Beatmap("beatmaps/320118 Reol - No title.osz")
        ));

        return beatmaps.toArray(new Beatmap[0]);
    }

    public void load() {
        active = true;
    }
}
