package io.github.Osu;

public class BeatmapViewModel extends SelectionEntry {
    private Beatmap beatmap;

        public BeatmapViewModel(int x, int y, int width, int height, Beatmap attachedBeatmap) {
            super(x,y,width,height);
            beatmap = attachedBeatmap;
        }
}
