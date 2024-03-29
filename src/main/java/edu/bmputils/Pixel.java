package edu.bmputils;

public final class Pixel {
    public final int red;
    public final int green;
    public final int blue;

    public Pixel(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public boolean isEqual(Pixel pixel) {
        return (pixel.red == red && pixel.green == green && pixel.blue == blue);
    }
}
