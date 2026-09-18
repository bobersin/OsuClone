package io.github.Osu;
import com.badlogic.gdx.math.Circle;

public class HitCircle {
    private int time;
    private int objectType;
    private int hitSound;
    private int size;
    private Circle dimensions;

    public HitCircle(String rawElement, int circleSize) {
        int minSize = 10;
        int maxSize = 50;
        size = 1;
        String[] data = rawElement.split(",\\s*");


        dimensions = new Circle(Integer.parseInt(data[0]), Integer.parseInt(data[1]), ((float) ((maxSize - minSize) * circleSize) /10) + minSize);
        time = Integer.parseInt(data[2]);
        objectType = Integer.parseInt(data[3]);
        hitSound = Integer.parseInt(data[4]);
    }

    public int getTime() {
        return time;
    }

}
