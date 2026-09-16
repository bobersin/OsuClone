package io.github.Osu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Song {

    private Map<String, HashMap<String, String>> values;

    public Song(ZipFile zipFile, ZipEntry entry) {
        values = new HashMap<>();
        try (InputStream is = zipFile.getInputStream(entry);
             InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(isr)) {

            String line;
            String currentKey = null;
            while ((line = reader.readLine()) != null) {
                if (line.contains("[") && line.contains("]")) {
                    currentKey = line.substring(line.indexOf("[") + 1, line.indexOf("]"));
                    values.put(currentKey, new HashMap<>());
                    continue;
                }

                if (line.contains("//") || line.isEmpty() || currentKey == null) {
                    continue;
                }


                switch (currentKey) {
                    case "Events", "TimingPoints", "HitObjects":
                        values.get(currentKey).put(String.valueOf(values.get(currentKey).size()), line);
                        break;
                    default:
                        int index = line.indexOf(":");
                        if (index == -1) {
                            break;
                        }
                        if (index + 1 < line.length()) {
                            values.get(currentKey).put(line.substring(0, index), line.substring(index + 1).strip());
                        } else {
                            values.get(currentKey).put(line.substring(0, index), "");
                        }
                        break;
                }
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print(values);
    }

    public String getValue(String key, String value) {
        return values.get(key).get(value);
    }
}
