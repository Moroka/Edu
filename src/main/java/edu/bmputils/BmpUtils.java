package edu.bmputils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import static edu.bmputils.Bmp.HEADER_SIZE;

public class BmpUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(BmpUtils.class);

    public static byte[] toGrayScaleExceptRed(Bmp bmp) throws IOException {
        final byte[] bytes = Arrays.copyOf(bmp.getBytes(), bmp.getBytes().length);
        for (int i = 0; i < bmp.getWidth(); i++) {
            for (int j = 0; j < bmp.getHeight(); j++) {
                final int r = bytes[2 + bmp.getPixelByteNumber(i, j)] & 0xFF;
                final int g = bytes[1 + bmp.getPixelByteNumber(i, j)] & 0xFF;
                final int b = bytes[bmp.getPixelByteNumber(i, j)] & 0xFF;
                if (r <= g || r <= b) {
                    int average = (r + g + b) / 3;
                    setPixelColor(bytes, bmp.getRowSize(), i, j, average, average, average);
                    LOGGER.debug("Pixel {} : {} {} {} convert to: {} {} {}", i, r, g, b, average, average, average);
                } else
                    LOGGER.debug("Pixel {} : {} {} {} does not meet the requirements", i, r, g, b);
            }
        }
        return bytes;
    }

    public static byte[] bmpDiff(Bmp firstBmp, Bmp secondBmp) throws IOException {
        final byte[] firstBmpBytes = Arrays.copyOf(firstBmp.getBytes(), firstBmp.getBytes().length);

        for (int i = 0; i < firstBmp.getWidth(); i++) {
            for (int j = 0; j < firstBmp.getHeight(); j++) {
                if (firstBmp.getPixelInfo(i, j).equals(secondBmp.getPixelInfo(i, j))) {
                    LOGGER.debug("{} are equals: {}", i, firstBmp.getPixelInfo(i, j));
                } else {
                    setPixelColor(firstBmpBytes, firstBmp.getRowSize(), i, j, 127, 127, 127);
                    LOGGER.debug("Pixels at position {} are not equals: {} {}", i, firstBmp.getPixelInfo(i, j), secondBmp.getPixelInfo(i, j));
                }
            }
        }
        return firstBmpBytes;
    }

    public static byte[] generateRedBlueBitmap(Bmp source, int width, int height) {
        final int bytesLength = HEADER_SIZE + getRowSize(width) * height;
        final byte[] bytes = Arrays.copyOf(source.getBytes(), bytesLength);

        // update header data
        intToFourBytes(bytes, 18, width);
        intToFourBytes(bytes, 22, height);
        intToFourBytes(bytes, 2, bytesLength);
        intToFourBytes(bytes, 34, getRowSize(width) * height);

        int blueColor = 0;
        int redColor = 0;

        for (int i = 0; i < height; i++) {
            redColor = (i + 1 == height) ? 255 : redColor + (255 / height);
            for (int j = 0; j < width; j++) {
                setPixelColor(bytes, getRowSize(width), i, j, redColor, 0, blueColor);
                blueColor = (j + 1 == width) ? 0 : blueColor + (255 / width);
            }
        }
        return bytes;
    }

    private static int getRowSize(int width) {
        final int a = width * 3;
        return (a % 4 == 0) ? a : (a - (a % 4) + 4);
    }

    private static void setPixelColor(byte[] bytes, int rowSize, int rowNumber, int position, int r, int g, int b) {
        final int startByteNumber = HEADER_SIZE + rowSize * rowNumber + position * 3;
        bytes[2 + startByteNumber] = (byte) r;
        bytes[1 + startByteNumber] = (byte) g;
        bytes[startByteNumber] = (byte) b;
        LOGGER.debug("Paint pixel at position {}x{} to: {} {} {}", rowNumber, position, r, g, b);
    }

    private static void intToFourBytes(byte[] bytes, int offset, int value) {
        bytes[offset + 3] = ((byte) (value >> 24 & 0xFF));
        bytes[offset + 2] = ((byte) (value >> 16 & 0xFF));
        bytes[offset + 1] = ((byte) (value >> 8 & 0xFF));
        bytes[offset] = ((byte) (value & 0xFF));
    }

    public static void copy(String fileName, byte[] bytes) throws IOException {
        Files.write(Paths.get(fileName), bytes);
    }
}
