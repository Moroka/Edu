package edu.bmputils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class BmpUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(BmpUtils.class);

    public static Bmp toGrayScaleExceptRed(Bmp bmp) {
        for (int i = 0; i < bmp.getWidth(); i++) {
            for (int j = 0; j < bmp.getHeight(); j++) {
                final int r = bmp.getPixelColor(i, j).red;
                final int g = bmp.getPixelColor(i, j).green;
                final int b = bmp.getPixelColor(i, j).blue;

                if (r <= g || r <= b) {
                    int average = (r + g + b) / 3;
                    bmp.setPixelColor(i, j, new Pixel(average, average, average));
                } else if (LOGGER.isDebugEnabled())
                    LOGGER.debug("Pixel {} : {} {} {} does not meet the requirements", i, r, g, b);
            }
        }
        return bmp;
    }

    public static Bmp bmpDiff(Bmp firstBmp, Bmp secondBmp) {
        for (int i = 0; i < firstBmp.getWidth(); i++) {
            for (int j = 0; j < firstBmp.getHeight(); j++) {
                if (firstBmp.getPixelInfo(i, j).equals(secondBmp.getPixelInfo(i, j))) {
                    if (LOGGER.isDebugEnabled())
                        LOGGER.debug("{} are equals: {}", i, firstBmp.getPixelInfo(i, j));
                } else {
                    firstBmp.setPixelColor(i, j, new Pixel(127, 127, 127));
                    if (LOGGER.isDebugEnabled())
                        LOGGER.debug("Pixels at position {} are not equals: {} {}", i, firstBmp.getPixelInfo(i, j), secondBmp.getPixelInfo(i, j));
                }
            }
        }
        return firstBmp;
    }

    public static Bmp generateRedBlueBitmap(int width, int height) throws IOException {

        Bmp bmp = Bmp.createEmpty(width, height);

        int blueColor = 0;
        int redColor = 0;

        for (int i = 0; i < height; i++) {
            redColor = (i + 1 == height) ? 255 : redColor + (255 / height);
            for (int j = 0; j < width; j++) {
                bmp.setPixelColor(i, j, new Pixel(redColor, 0, blueColor));
                blueColor = (j + 1 == width) ? 0 : blueColor + (255 / width);
            }
        }
        return bmp;
    }
}
