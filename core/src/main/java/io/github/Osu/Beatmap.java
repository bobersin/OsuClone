package io.github.Osu;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Beatmap {
    private final String filepath;
    private final Song[] songs;

    public Beatmap(String file) {
        filepath = file;
        List<Song> songList = new ArrayList<>();

        try (ZipFile zipFile = new ZipFile(new File(filepath))) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();

            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();

                if (!entry.isDirectory() && entry.getName().endsWith(".osu")) {
                    songList.add(new Song(zipFile, entry));
                }
            }
        } catch (IOException ignored) {}

        songs = songList.toArray(new Song[0]);
    }

    public Song[] getSongs() {
        return songs;
    }

    public String getName() {
        return filepath.substring(filepath.indexOf("/") + 8);
    }
}
