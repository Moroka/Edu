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

    public Bmp(String path) throws IOException {
        bitMapBytes = Files.readAllBytes(Paths.get(path));
        checkBitmap();
    }

    public void getHeaderInfo() {
        LOGGER.info("::::::::::BMP Header::::::::::");
        LOGGER.info("ID field: {}{}", (char) bitMapBytes[0], (char) bitMapBytes[1]);
        LOGGER.info("Size of the BMP file: {}", intFromFourBytes(bitMapBytes, 2));
        LOGGER.info("Offset where the pixel array can be found: {}", intFromFourBytes(bitMapBytes, 10));
        LOGGER.info("Width of the bitmap in pixels: {}", intFromFourBytes(bitMapBytes, 18));
        LOGGER.info("Height of the bitmap in pixels: {}", intFromFourBytes(bitMapBytes, 22));
        LOGGER.info("Number of bits per pixel: {}", intFromTwoBytes(bitMapBytes, 28));
        LOGGER.info("Compression used: {}", intFromFourBytes(bitMapBytes, 30));
    }

    public String getPixelInfo(int pixelNumber) {
        return String.format("#%02x%02x%02x", bitMapBytes[2 + HEADER_SIZE + pixelNumber * 3] & 0xFF,
                bitMapBytes[1 + HEADER_SIZE + pixelNumber * 3] & 0xFF, bitMapBytes[HEADER_SIZE + pixelNumber * 3] & 0xFF);
    }

    public int getPixelCount() {
        return intFromFourBytes(bitMapBytes, 18) * intFromFourBytes(bitMapBytes, 22);
    }

    public byte[] getBytes() {
        return bitMapBytes;
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
