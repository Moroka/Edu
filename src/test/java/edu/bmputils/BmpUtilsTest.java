package edu.bmputils;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class BmpUtilsTest {

    @Test
    public void colorTest() throws IOException {
        BmpUtils bmpUtils = new BmpUtils("src/main/resources/BmpUtils/ColorTest.bmp");
        bmpUtils.getHeaderInfo();

        assertEquals(bmpUtils.getPixelInfo(0), "255 0 0");
        assertEquals(bmpUtils.getPixelInfo(1), "0 255 0");
        assertEquals(bmpUtils.getPixelInfo(2), "0 0 255");
        assertEquals(bmpUtils.getPixelInfo(3), "255 255 255");
        assertEquals(bmpUtils.getPixelInfo(4), "0 0 0");
    }

    @Test
    public void copyTest() throws IOException {
        BmpUtils bmpUtils = new BmpUtils("src/main/resources/BmpUtils/ColorTest.bmp");
        bmpUtils.getCopy("ColorTestCopy");
    }
}
