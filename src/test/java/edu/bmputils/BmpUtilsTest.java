package edu.bmputils;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class BmpUtilsTest {

    @Test
    public void colorTest() throws IOException {
        final Bmp bmp = Bmp.load("src/main/resources/BmpUtils/ColorTest.bmp");
        bmp.printHeaderInfo();
        assertEquals(bmp.getPixelInfo(0, 0), "#ff0000");
        assertEquals(bmp.getPixelInfo(0, 1), "#00ff00");
        assertEquals(bmp.getPixelInfo(0, 2), "#0000ff");
        assertEquals(bmp.getPixelInfo(0, 3), "#ffffff");
        assertEquals(bmp.getPixelInfo(0, 4), "#000000");
    }

    @Test
    public void copyTest() throws IOException {
        final Bmp bmp = Bmp.load("src/main/resources/BmpUtils/ColorTest.bmp");
        bmp.saveAs("src/main/resources/BmpUtils/ColorTestCopy.bmp");
    }

    @Test
    public void toGrayScaleExceptRed() throws IOException {
        final Bmp bmp = Bmp.load("src/main/resources/BmpUtils/ToGrayScaleExceptRed.bmp");
        final Bmp processedBmp = BmpUtils.toGrayScaleExceptRed(bmp);
        processedBmp.saveAs("src/main/resources/BmpUtils/ToGrayScaleExceptRedCopy.bmp");
    }

    @Test
    public void bmpDiff() throws IOException {
        final Bmp firstBmp = Bmp.load("src/main/resources/BmpUtils/BmpDiffFirst.bmp");
        final Bmp secondBmp = Bmp.load("src/main/resources/BmpUtils/BmpDiffSecond.bmp");
        final Bmp processedBmp = BmpUtils.bmpDiff(firstBmp, secondBmp);
        processedBmp.saveAs("src/main/resources/BmpUtils/BmpDiff.bmp");
    }

    @Test
    public void generateRedBlueBitmap() throws IOException {
        final Bmp bmp = BmpUtils.generateRedBlueBitmap(10, 10);
        bmp.printHeaderInfo();
        bmp.saveAs("src/main/resources/BmpUtils/RedBlueBitmap.bmp");
    }
}

