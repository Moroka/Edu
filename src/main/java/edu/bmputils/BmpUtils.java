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

    public static void toGrayScaleExceptRed(Bmp bmp) throws IOException {
        final byte[] bytes = Arrays.copyOf(bmp.getBytes(), bmp.getBytes().length);
        for (int i = 0; i < bmp.getPixelCount(); i++) {
            final int r = bytes[2 + HEADER_SIZE + i * 3] & 0xFF;
            final int g = bytes[1 + HEADER_SIZE + i * 3] & 0xFF;
            final int b = bytes[HEADER_SIZE + i * 3] & 0xFF;
            if (r <= g || r <= b) {
                int average = Math.round((r + g + b) / 3);
                bytes[2 + HEADER_SIZE + i * 3] = (byte) average;
                bytes[1 + HEADER_SIZE + i * 3] = (byte) average;
                bytes[HEADER_SIZE + i * 3] = (byte) average;
                LOGGER.info("Pixel {} : {} {} {} convert to: {} {} {}", i, r, g, b, average, average, average);
            } else
                LOGGER.info("Pixel {} : {} {} {} does not meet the requirements", i, r, g, b);
        }
        copy("src/main/resources/BmpUtils/ToGrayScaleExceptRedCopy.bmp", bytes);
    }

    public static void bmpDiff(Bmp firstBmp, Bmp secondBmp) throws IOException {
        System.out.println(Arrays.toString(firstBmp.getBytes()));
        System.out.println(Arrays.toString(secondBmp.getBytes()));

        final byte[] firstBmpBytes = Arrays.copyOf(firstBmp.getBytes(), firstBmp.getBytes().length);

        for (int i = 0; i < firstBmp.getPixelCount(); i++) {
            if (firstBmp.getPixelInfo(i).equals(secondBmp.getPixelInfo(i))) {
                LOGGER.info("{} are equals: {}", i, firstBmp.getPixelInfo(i));
            } else {
                firstBmpBytes[2 + HEADER_SIZE + i * 3] = (byte) 127;
                firstBmpBytes[1 + HEADER_SIZE + i * 3] = (byte) 127;
                firstBmpBytes[HEADER_SIZE + i * 3] = (byte) 127;
                LOGGER.info("Pixels at position {} are not equals: {} {}", i, firstBmp.getPixelInfo(i), secondBmp.getPixelInfo(i));
            }
        }
        copy("src/main/resources/BmpUtils/BmpDiff.bmp", firstBmpBytes);
    }

    public static void generateRedBlueBitmap(Bmp source, int width, int height) throws IOException {
        final byte[] bytes = Arrays.copyOf(source.getBytes(), calcBitmapSize(width, height));
        // update header data
        intToFourBytes(bytes, 18, width);
        intToFourBytes(bytes, 22, height);
        intToFourBytes(bytes, 2, calcBitmapSize(width, height));

        int blueColor = 0;
        int redColor = 0;

        for (int i = 0; i < width * height; i++) {
            LOGGER.info("Paint pixel at position {} to: {} {} {}", i, blueColor, 0, redColor);
            bytes[2 + HEADER_SIZE + i * 3] = (byte) redColor;
            bytes[1 + HEADER_SIZE + i * 3] = (byte) 0;
            bytes[HEADER_SIZE + i * 3] = (byte) blueColor;
            blueColor += 255 / width;
        }

        copy("src/main/resources/BmpUtils/RedBlueBitmap.bmp", bytes);
        final Bmp bmp = new Bmp("src/main/resources/BmpUtils/RedBlueBitmap.bmp");
        bmp.getHeaderInfo();
    }

    private static int calcBitmapSize(int width, int height) {
        final int a = width * height * 3;
        return (a % 4 == 0) ? 54 + a : 54 + (a - (a % 4) + 4);
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
