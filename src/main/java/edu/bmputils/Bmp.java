package edu.bmputils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Bmp {
    static final int HEADER_SIZE = 54;

    private static final Logger LOGGER = LoggerFactory.getLogger(Bmp.class);
    private final byte[] bitMapBytes;
    private final int width;
    private final int height;

    public Bmp(String path) throws IOException {
        bitMapBytes = Files.readAllBytes(Paths.get(path));
        checkBitmap();
        width = intFromFourBytes(bitMapBytes, 18);
        height = intFromFourBytes(bitMapBytes, 22);
    }

    public void getHeaderInfo() {
        LOGGER.info("::::::::::BMP Header::::::::::");
        LOGGER.info("ID field: {}{}", (char) bitMapBytes[0], (char) bitMapBytes[1]);
        LOGGER.info("Size of the BMP file: {}", intFromFourBytes(bitMapBytes, 2));
        LOGGER.info("Offset where the pixel array can be found: {}", intFromFourBytes(bitMapBytes, 10));
        LOGGER.info("Width of the bitmap in pixels: {}", width);
        LOGGER.info("Height of the bitmap in pixels: {}", height);
        LOGGER.info("Number of planes: {}", intFromTwoBytes(bitMapBytes, 26));
        LOGGER.info("Number of bits per pixel: {}", intFromTwoBytes(bitMapBytes, 28));
        LOGGER.info("Compression used: {}", intFromFourBytes(bitMapBytes, 30));
        LOGGER.info("Image size: {}", intFromFourBytes(bitMapBytes, 34));
    }

    public String getPixelInfo(int rowNumber, int position) {
        return String.format("#%02x%02x%02x", bitMapBytes[2 + getPixelByteNumber(rowNumber, position)] & 0xFF,
                bitMapBytes[1 + getPixelByteNumber(rowNumber, position)] & 0xFF, bitMapBytes[getPixelByteNumber(rowNumber, position)] & 0xFF);
    }

    public byte[] getBytes() {
        return bitMapBytes;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getRowSize() {
        return (width * 3 % 4 == 0) ? width * 3 : (width * 3 - (width * 3 % 4) + 4);
    }

    public int getPixelByteNumber(int rowNumber, int position) {
        return HEADER_SIZE + getRowSize() * rowNumber + position * 3;
    }

    private void checkBitmap() throws IOException {
        if (intFromFourBytes(bitMapBytes, 10) != 54 || intFromFourBytes(bitMapBytes, 28) != 24 || intFromFourBytes(bitMapBytes, 30) != 0) {
            throw new IOException("Bmp supports only 24 bit per pixel bitmap without compression");
        }
    }

    private int intFromFourBytes(byte[] bytes, int offset) {
        return bytes[offset + 3] << 24 | (bytes[offset + 2] & 0xFF) << 16 | (bytes[offset + 1] & 0xFF) << 8 | (bytes[offset] & 0xFF);
    }

    private int intFromTwoBytes(byte[] bytes, int offset) {
        return (bytes[offset + 1] & 0xFF) << 8 | (bytes[offset] & 0xFF);
    }
}
