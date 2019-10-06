package edu.bmputils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class BmpUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(BmpUtils.class);
    private static final int HEADER_SIZE = 54;
    private static int cap;
    private static byte[] bitMapBytes;

    public BmpUtils(String path) throws IOException {
        checkBitmap(path);

        final InputStream fileStream = new FileInputStream(path);
        bitMapBytes = new byte[cap];
        fileStream.read(bitMapBytes, 0, cap);
        fileStream.close();
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
        return (bitMapBytes[2 + HEADER_SIZE + pixelNumber * 3] & 0xFF) + " " + (bitMapBytes[1 + HEADER_SIZE + pixelNumber * 3] & 0xFF) + " " + (bitMapBytes[HEADER_SIZE + pixelNumber * 3] & 0xFF);
    }

    public void getCopy(String fileName) throws IOException {
        File targetFile = new File("src/main/resources/BmpUtils/" + fileName + ".bmp");
        OutputStream outStream = new FileOutputStream(targetFile);
        outStream.write(bitMapBytes);
    }

    private void checkBitmap(String path) throws IOException {
        final InputStream fileStream = new FileInputStream(path);
        final byte[] headerBytes = new byte[HEADER_SIZE];
        fileStream.read(headerBytes, 0, HEADER_SIZE);
        cap = intFromFourBytes(headerBytes, 18) * intFromFourBytes(headerBytes, 22) * intFromTwoBytes(headerBytes, 28);
        fileStream.close();
        if (intFromFourBytes(headerBytes, 10) != 54 || intFromFourBytes(headerBytes, 28) != 24 || intFromFourBytes(headerBytes, 30) != 0) {
            throw new IOException("BmpUtils supports only 24 bit per pixel bitmap without compression");
        }
    }

    private int intFromFourBytes(byte[] bytes, int offset) {
        return bytes[offset + 3] << 24 | (bytes[offset + 2] & 0xFF) << 16 | (bytes[offset + 1] & 0xFF) << 8 | (bytes[offset] & 0xFF);
    }

    private int intFromTwoBytes(byte[] bytes, int offset) {
        return (bytes[offset + 1] & 0xFF) << 8 | (bytes[offset] & 0xFF);
    }
}

