package io.github.Osu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class BeatmapViewModel extends Button {
    private final String name;
    private final SongViewModel[] songs;
    private boolean toggled;
    private int fullHeight;

    public BeatmapViewModel(int x, int y, int width, int height, Beatmap attachedBeatmap, int songHeight, int songWidth) {
        super(x - Math.abs(y - 1080/2)/10,y,width,height);
        name = attachedBeatmap.getName();

        Song[] beatmapSongs = attachedBeatmap.getSongs();
        songs = new SongViewModel[beatmapSongs.length];
        for (int i = 0; i < songs.length; i++) {
            songs[i] = new SongViewModel(1920 - songWidth, (y - songHeight * i) - 5 * i, 1920, songHeight, beatmapSongs[i]);
        }
    }

    @Override
    public void clicked() {
        toggled = !toggled;

        if (toggled) {
            int songheights = 0;
            for (SongViewModel song : songs) {
                songheights += (int) song.getDimensions().height;
            }
            fullHeight = (int) dimensions.height + songheights;
        } else {
            fullHeight = (int) dimensions.height;
        }
    }

    public void update(float dt, Vector2 mouse) {

        super.update(dt, mouse);

        if (!toggled) {
            return;
        }

        for (SongViewModel song : songs) {
            song.update(dt, mouse);
        }
    }

    public float getCombinedHeight() {
        return fullHeight;
    }

    public void draw(SpriteBatch batch) {

        super.draw(batch);

        if (!toggled) {
            return;
        }

        for (SongViewModel song : songs) {
            song.draw(batch);
        }
    }
}
