package edu.bmputils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class BmpBuilder {
    private int width = 0;
    private int height = 0;
    private int bytesLength = 0;

    public BmpBuilder setWidth(int width) {
        this.width = width;
        return this;
    }

    public BmpBuilder setHeight(int height) {
        this.height = height;
        return this;
    }

    public BmpBuilder setBytesLength(int bytesLength) {
        this.bytesLength = bytesLength;
        return this;
    }

    public Bmp build(String path) throws IOException {
        return new Bmp(Files.readAllBytes(Paths.get(path)), width, height, bytesLength);
    }
}
