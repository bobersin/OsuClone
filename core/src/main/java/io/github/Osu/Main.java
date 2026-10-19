package io.github.Osu;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all
 * platforms.
 */

public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    public static Beatmap currentBeatmap;
    public static Song currentSong;
    public static Beatmap[] beatmaps;
    public static float screenScale;
    public static MainMenu mainMenu;
    public static SelectionMenu selectionMenu;
    public static GameMenu gameMenu;
    public static FitViewport viewport;
    private OrthographicCamera camera;
    public static Vector2 screenSize;

    @Override
    public void create() {
        beatmaps = loadBeatMaps();

        switchSong(MathUtils.random(beatmaps.length - 1), 0);
        screenSize = new Vector2(1920, 1080);
        screenScale = (float) screenSize.x / 1920;
        camera = new OrthographicCamera();
        viewport = new FitViewport( screenSize.x,  screenSize.y, camera);
        batch = new SpriteBatch();
        selectionMenu = new SelectionMenu();
        mainMenu = new MainMenu();
        mainMenu.load();
        gameMenu = new GameMenu();

        System.out.print(beatmaps.length);
    }

    @Override
    public void render() {
        update();

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.setProjectionMatrix(camera.combined);
        mainMenu.draw(batch);
        gameMenu.draw(batch);
        selectionMenu.draw(batch);
    }

    public void update() {
        float dt = Gdx.graphics.getDeltaTime();
        mainMenu.update(dt);
        selectionMenu.update(dt);
        gameMenu.update(dt);
        Gdx.app.log("TAG", "Update");
    }

    public void switchSong(int beatmapIndex, int songIndex) {
        currentBeatmap = beatmaps[beatmapIndex];
        Song[] songs = currentBeatmap.getSongs();
        currentSong = songs[songIndex];
        playSong(currentBeatmap, currentSong);
    }

    private void playSong(Beatmap beatmap, Song song) {
        String filename = song.getValue("General", "AudioFilename");
        String filepath = beatmap.getFilePath();

        try (ZipFile zipFile = new ZipFile(new File(filepath))) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();

            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();

                if (entry.getName().equals(filename)) {
                    FileHandle tempFile = Gdx.files.local("temp/" + entry.getName());
                    tempFile.parent().mkdirs();

                    try (InputStream input = zipFile.getInputStream(entry);
                         FileOutputStream output = new FileOutputStream(tempFile.file())) {
                        byte[] buffer = new byte[4096];
                        int bytesRead;
                        while ((bytesRead = input.read(buffer)) != -1) {
                            output.write(buffer, 0, bytesRead);
                        }
                    }
                    zipFile.close();

                    Music music = Gdx.audio.newMusic(tempFile);
                    music.setLooping(true);
                    music.play();
                    return;
                }
            }
        } catch (IOException ignored) {}

    }

    public Beatmap[] loadBeatMaps() {
        List<Beatmap> beatmaps = new ArrayList<>(List.of(
            new Beatmap("beatmaps/320118 Reol - No title.osz"),
            new Beatmap("beatmaps/2611637 Chata - Shiawase Gatari.osz")
        ));

        return beatmaps.toArray(new Beatmap[0]);
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
