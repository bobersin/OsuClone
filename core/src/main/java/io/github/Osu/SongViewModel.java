package io.github.Osu;

import static io.github.Osu.Main.selectionMenu;
import static io.github.Osu.Main.gameMenu;

public class SongViewModel extends Button {
    private final Song song;
    public SongViewModel(int x, int y, int width, int height, Song attahcedSong) {
        super(x,y,width,height);
        song = attahcedSong;
    }

    @Override
    public void clicked() {
        selectionMenu.unload();
        gameMenu.load();
        gameMenu.start(song);
    }

}
