package edu.bmputils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static edu.bmputils.Bmp.HEADER_SIZE;

public class BmpUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(BmpUtils.class);

    public static byte[] toGrayScaleExceptRed(Bmp bmp) {
        for (int i = 0; i < bmp.getWidth(); i++) {
            for (int j = 0; j < bmp.getHeight(); j++) {
                final int r = bmp.getBytes()[2 + bmp.getPixelByteNumber(i, j)] & 0xFF;
                final int g = bmp.getBytes()[1 + bmp.getPixelByteNumber(i, j)] & 0xFF;
                final int b = bmp.getBytes()[bmp.getPixelByteNumber(i, j)] & 0xFF;
                if (r <= g || r <= b) {
                    int average = (r + g + b) / 3;
                    bmp.setPixelColor(i, j, average, average, average);
                    LOGGER.debug("Pixel {} : {} {} {} convert to: {} {} {}", i, r, g, b, average, average, average);
                } else
                    LOGGER.debug("Pixel {} : {} {} {} does not meet the requirements", i, r, g, b);
            }
        }
        return bmp.getBytes();
    }

    public static byte[] bmpDiff(Bmp firstBmp, Bmp secondBmp) {
        for (int i = 0; i < firstBmp.getWidth(); i++) {
            for (int j = 0; j < firstBmp.getHeight(); j++) {
                if (firstBmp.getPixelInfo(i, j).equals(secondBmp.getPixelInfo(i, j))) {
                    LOGGER.debug("{} are equals: {}", i, firstBmp.getPixelInfo(i, j));
                } else {
                    firstBmp.setPixelColor(i, j, 127, 127, 127);
                    LOGGER.debug("Pixels at position {} are not equals: {} {}", i, firstBmp.getPixelInfo(i, j), secondBmp.getPixelInfo(i, j));
                }
            }
        }
        return firstBmp.getBytes();
    }

    public static byte[] generateRedBlueBitmap(String sourcePath, int width, int height) throws IOException {
        final int bytesLength = HEADER_SIZE + getRowSize(width) * height;
        Bmp bmp = new BmpBuilder().setWidth(width)
                                  .setHeight(height)
                                  .setBytesLength(bytesLength)
                                  .build(sourcePath);

        // update header data
        bmp.intToFourBytes(18, width);
        bmp.intToFourBytes(22, height);
        bmp.intToFourBytes(2, bytesLength);
        bmp.intToFourBytes(34, getRowSize(width) * height);

        bmp.getHeaderInfo();

        int blueColor = 0;
        int redColor = 0;

        for (int i = 0; i < height; i++) {
            redColor = (i + 1 == height) ? 255 : redColor + (255 / height);
            for (int j = 0; j < width; j++) {
                bmp.setPixelColor(i, j, redColor, 0, blueColor);
                blueColor = (j + 1 == width) ? 0 : blueColor + (255 / width);
            }
        }
        return bmp.getBytes();
    }

    private static int getRowSize(int width) {
        final int a = width * 3;
        return (a % 4 == 0) ? a : (a - (a % 4) + 4);
    }

    public static void copy(String fileName, byte[] bytes) throws IOException {
        Files.write(Paths.get(fileName), bytes);
    }
}
