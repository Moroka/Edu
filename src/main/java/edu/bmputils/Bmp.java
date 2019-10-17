package edu.bmputils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Bmp implements IBmp {
    private static final int HEADER_SIZE = 54;
    private static final Logger LOGGER = LoggerFactory.getLogger(Bmp.class);
    private final byte[] bitMapBytes;
    private final int width;
    private final int height;

    private Bmp(byte[] bitMapBytes, int width, int height) throws IOException {
        this.bitMapBytes = bitMapBytes;
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

    public String getPixelInfo(int position, int rowNumber) {
        return String.format("#%02x%02x%02x", bitMapBytes[2 + getPixelByteNumber(rowNumber, position)] & 0xFF,
                bitMapBytes[1 + getPixelByteNumber(rowNumber, position)] & 0xFF, bitMapBytes[getPixelByteNumber(rowNumber, position)] & 0xFF);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setPixelColor(int position, int rowNumber, Pixel pixel) {
        final int startByteNumber = getPixelByteNumber(rowNumber, position);
        bitMapBytes[2 + startByteNumber] = (byte) pixel.red;
        bitMapBytes[1 + startByteNumber] = (byte) pixel.green;
        bitMapBytes[startByteNumber] = (byte) pixel.blue;
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("Paint pixel at position {}x{} to: {} {} {}", rowNumber, position, pixel.red, pixel.green, pixel.blue);
    }

    public Pixel getPixelColor(int position, int rowNumber) {
        final int startByteNumber = getPixelByteNumber(rowNumber, position);
        return new Pixel(bitMapBytes[2 + startByteNumber] & 0xFF, bitMapBytes[1 + startByteNumber] & 0xFF, bitMapBytes[startByteNumber] & 0xFF);
    }

    public void saveAs(String path) throws IOException {
        Files.write(Paths.get(path), bitMapBytes);
    }

    private int getPixelByteNumber(int rowNumber, int position) {
        return HEADER_SIZE + getRowSize(width) * rowNumber + position * 3;
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

    private static void setIntToFourBytes(byte[] bytes, int offset, int value) {
        bytes[offset + 3] = ((byte) (value >> 24 & 0xFF));
        bytes[offset + 2] = ((byte) (value >> 16 & 0xFF));
        bytes[offset + 1] = ((byte) (value >> 8 & 0xFF));
        bytes[offset] = ((byte) (value & 0xFF));
    }

    private static int getRowSize(int width) {
        return (width * 3 % 4 == 0) ? width * 3 : (width * 3 - (width * 3 % 4) + 4);
    }

    public static Bmp load(String sourcePath) throws IOException {
        return new Bmp(Files.readAllBytes(Paths.get(sourcePath)), 0, 0);
    }

    public static Bmp createEmpty(int width, int height) throws IOException {
        final int bytesLength = HEADER_SIZE + getRowSize(width) * height;
        byte[] bytes = Arrays.copyOf(Files.readAllBytes(Paths.get("src/main/resources/BmpUtils/Source.bmp")), bytesLength);
        // update header data
        Bmp.setIntToFourBytes(bytes, 18, width);
        Bmp.setIntToFourBytes(bytes, 22, height);
        Bmp.setIntToFourBytes(bytes, 2, bytesLength);
        Bmp.setIntToFourBytes(bytes, 34, getRowSize(width) * height);

        return new Bmp(bytes, width, height);
    }
}
