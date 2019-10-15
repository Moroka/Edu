package edu.bmputils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Bmp implements IBmp {
    static final int HEADER_SIZE = 54;

    private static final Logger LOGGER = LoggerFactory.getLogger(Bmp.class);
    private final byte[] bitMapBytes;
    private final int width;
    private final int height;

    private Bmp(byte[] bitMapBytes, int width, int height, int bytesLength) throws IOException {
        this.bitMapBytes = (bytesLength == 0) ? bitMapBytes : Arrays.copyOf(bitMapBytes, bytesLength);
        this.width = (width == 0) ? intFromFourBytes(bitMapBytes, 18) : width;
        this.height = (height == 0) ? intFromFourBytes(bitMapBytes, 22) : height;

        checkBitmap();
    }

    public void printHeaderInfo() {
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

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setPixelColor(int rowNumber, int position, int r, int g, int b) {
        final int startByteNumber = getPixelByteNumber(rowNumber, position);
        bitMapBytes[2 + startByteNumber] = (byte) r;
        bitMapBytes[1 + startByteNumber] = (byte) g;
        bitMapBytes[startByteNumber] = (byte) b;
        LOGGER.debug("Paint pixel at position {}x{} to: {} {} {}", rowNumber, position, r, g, b);
    }

    public int[] getPixelColor(int rowNumber, int position) {
        final int[] arr = new int[3];
        final int startByteNumber = getPixelByteNumber(rowNumber, position);
        arr[0] = bitMapBytes[2 + startByteNumber] & 0xFF;
        arr[1] = bitMapBytes[1 + startByteNumber] & 0xFF;
        arr[2] = bitMapBytes[startByteNumber] & 0xFF;
        return arr;
    }

    public void setIntToFourBytes(int offset, int value) {
        bitMapBytes[offset + 3] = ((byte) (value >> 24 & 0xFF));
        bitMapBytes[offset + 2] = ((byte) (value >> 16 & 0xFF));
        bitMapBytes[offset + 1] = ((byte) (value >> 8 & 0xFF));
        bitMapBytes[offset] = ((byte) (value & 0xFF));
    }

    public void saveAs(String path) throws IOException {
        Files.write(Paths.get(path), bitMapBytes);
    }

    private int getRowSize() {
        return (width * 3 % 4 == 0) ? width * 3 : (width * 3 - (width * 3 % 4) + 4);
    }

    private int getPixelByteNumber(int rowNumber, int position) {
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

    public static Bmp load(String sourcePath) throws IOException {
        return new Bmp(Files.readAllBytes(Paths.get(sourcePath)), 0, 0, 0);
    }

    public static Bmp createEmpty(int width, int height, int bytesLength) throws IOException {
        return new Bmp(Files.readAllBytes(Paths.get("src/main/resources/BmpUtils/Source.bmp")), width, height, bytesLength);
    }
}
