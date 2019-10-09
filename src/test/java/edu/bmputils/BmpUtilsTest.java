package edu.bmputils;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class BmpUtilsTest {

    @Test
    public void colorTest() throws IOException {
        final Bmp bmp = new Bmp("src/main/resources/BmpUtils/ColorTest.bmp");
        bmp.getHeaderInfo();

        assertEquals(bmp.getPixelInfo(0), "#ff0000");
        assertEquals(bmp.getPixelInfo(1), "#00ff00");
        assertEquals(bmp.getPixelInfo(2), "#0000ff");
        assertEquals(bmp.getPixelInfo(3), "#ffffff");
        assertEquals(bmp.getPixelInfo(4), "#000000");
    }

    @Test
    public void copyTest() throws IOException {
        final Bmp bmp = new Bmp("src/main/resources/BmpUtils/ColorTest.bmp");
        BmpUtils.copy("src/main/resources/BmpUtils/ColorTestCopy.bmp", bmp.getBytes());
    }

    @Test
    public void toGrayScaleExceptRed() throws IOException {
        final Bmp bmp = new Bmp("src/main/resources/BmpUtils/ToGrayScaleExceptRed.bmp");
        BmpUtils.toGrayScaleExceptRed(bmp);
    }

    @Test
    public void bmpDiff() throws IOException {
        final Bmp firstBmp = new Bmp("src/main/resources/BmpUtils/BmpDiffFirst.bmp");
        final Bmp secondBmp = new Bmp("src/main/resources/BmpUtils/BmpDiffSecond.bmp");

        BmpUtils.bmpDiff(firstBmp, secondBmp);
    }

    @Test
    public void generateRedBlueBitmap() throws IOException {
        final Bmp source = new Bmp("src/main/resources/BmpUtils/Source.bmp");
        BmpUtils.generateRedBlueBitmap(source, 10, 1);
    }
}

