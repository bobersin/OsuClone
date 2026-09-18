package io.github.Osu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class SelectionMenu extends Menu {
    private final BeatmapViewModel[] viewModels;

    public SelectionMenu() {
        super();
        Beatmap[] beatmaps = loadBeatMaps();

        int curHeight = 1080/2;
        List<BeatmapViewModel> tmpViewModel = new ArrayList<>();
        for (Beatmap beatmap : beatmaps) {
            int beatmapHeight = 75;
            int maxBeatmapWidth = 1920 / 3;
            int songHeight = 35;
            int songWidth = (int) (1920/3.3);
            BeatmapViewModel currentModel = new BeatmapViewModel(1920 - maxBeatmapWidth, curHeight, maxBeatmapWidth, beatmapHeight, beatmap, songHeight, songWidth);
            tmpViewModel.add(currentModel);
            curHeight = (int) (curHeight + (currentModel.getCombinedHeight() + 5));
        }
        viewModels = tmpViewModel.toArray(new BeatmapViewModel[0]);
    }

    public void update(float dt) {
        if (!isActive()) {
            return;
        }

        super.update(dt);

        for (BeatmapViewModel beatmapModel : viewModels) {
            beatmapModel.update(dt, mouse);
        }
    }

    public void draw(SpriteBatch batch) {
        if (!isActive()) {
            return;
        }
        batch.begin();
        for (BeatmapViewModel beatmapModel : viewModels) {
            beatmapModel.draw(batch);
        }
        batch.end();
    }

    public Beatmap[] loadBeatMaps() {
        List<Beatmap> beatmaps = new ArrayList<>(List.of(
            new Beatmap("beatmaps/320118 Reol - No title.osz")
        ));

        return beatmaps.toArray(new Beatmap[0]);
    }
}
