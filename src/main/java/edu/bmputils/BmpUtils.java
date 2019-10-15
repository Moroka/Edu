package edu.bmputils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static edu.bmputils.Bmp.HEADER_SIZE;

public class BmpUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(BmpUtils.class);

    public static Bmp toGrayScaleExceptRed(Bmp bmp) {
        for (int i = 0; i < bmp.getWidth(); i++) {
            for (int j = 0; j < bmp.getHeight(); j++) {
                final int r = bmp.getPixelColor(i, j)[0];
                final int g = bmp.getPixelColor(i, j)[1];
                final int b = bmp.getPixelColor(i, j)[2];

                if (r <= g || r <= b) {
                    int average = (r + g + b) / 3;
                    bmp.setPixelColor(i, j, average, average, average);
                    LOGGER.debug("Pixel {} : {} {} {} convert to: {} {} {}", i, r, g, b, average, average, average);
                } else
                    LOGGER.debug("Pixel {} : {} {} {} does not meet the requirements", i, r, g, b);
            }
        }
        return bmp;
    }

    public static Bmp bmpDiff(Bmp firstBmp, Bmp secondBmp) {
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
        return firstBmp;
    }

    public static Bmp generateRedBlueBitmap(int width, int height) throws IOException {
        final int bytesLength = HEADER_SIZE + getRowSize(width) * height;
        Bmp bmp = Bmp.createEmpty(width, height, bytesLength);

        // update header data
        bmp.setIntToFourBytes(18, width);
        bmp.setIntToFourBytes(22, height);
        bmp.setIntToFourBytes(2, bytesLength);
        bmp.setIntToFourBytes(34, getRowSize(width) * height);

        bmp.printHeaderInfo();

        int blueColor = 0;
        int redColor = 0;

        for (int i = 0; i < height; i++) {
            redColor = (i + 1 == height) ? 255 : redColor + (255 / height);
            for (int j = 0; j < width; j++) {
                bmp.setPixelColor(i, j, redColor, 0, blueColor);
                blueColor = (j + 1 == width) ? 0 : blueColor + (255 / width);
            }
        }
        return bmp;
    }

    private static int getRowSize(int width) {
        final int a = width * 3;
        return (a % 4 == 0) ? a : (a - (a % 4) + 4);
    }
}
