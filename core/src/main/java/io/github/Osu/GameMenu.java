package io.github.Osu;

public class GameMenu extends Menu {
    private Song song;
    public GameMenu() {
        super();
    }

    public void start(Song currentSong) {
        song = currentSong;
    }
}
