package edu.bmputils;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class BmpUtilsTest {

    @Test
    public void colorTest() throws IOException {
        final Bmp bmp = new BmpBuilder().build("src/main/resources/BmpUtils/ColorTest.bmp");
        bmp.getHeaderInfo();
        assertEquals(bmp.getPixelInfo(0, 0), "#ff0000");
        assertEquals(bmp.getPixelInfo(0, 1), "#00ff00");
        assertEquals(bmp.getPixelInfo(0, 2), "#0000ff");
        assertEquals(bmp.getPixelInfo(0, 3), "#ffffff");
        assertEquals(bmp.getPixelInfo(0, 4), "#000000");
    }

    @Test
    public void copyTest() throws IOException {
        final Bmp bmp = new BmpBuilder().build("src/main/resources/BmpUtils/ColorTest.bmp");
        BmpUtils.copy("src/main/resources/BmpUtils/ColorTestCopy.bmp", bmp.getBytes());
    }

    @Test
    public void toGrayScaleExceptRed() throws IOException {
        final Bmp bmp = new BmpBuilder().build("src/main/resources/BmpUtils/ToGrayScaleExceptRed.bmp");
        final byte[] resultBytes = BmpUtils.toGrayScaleExceptRed(bmp);
        final String resultPath = "src/main/resources/BmpUtils/ToGrayScaleExceptRedCopy.bmp";
        BmpUtils.copy(resultPath, resultBytes);
    }

    @Test
    public void bmpDiff() throws IOException {
        final Bmp firstBmp = new BmpBuilder().build("src/main/resources/BmpUtils/BmpDiffFirst.bmp");
        final Bmp secondBmp = new BmpBuilder().build("src/main/resources/BmpUtils/BmpDiffSecond.bmp");
        final byte[] resultBytes = BmpUtils.bmpDiff(firstBmp, secondBmp);
        final String resultPath = "src/main/resources/BmpUtils/BmpDiff.bmp";
        BmpUtils.copy(resultPath, resultBytes);
    }

    @Test
    public void generateRedBlueBitmap() throws IOException {
        final byte[] resultBytes = BmpUtils.generateRedBlueBitmap("src/main/resources/BmpUtils/Source.bmp", 10, 10);
        final String resultPath = "src/main/resources/BmpUtils/RedBlueBitmap.bmp";
        BmpUtils.copy(resultPath, resultBytes);
        final Bmp result = new BmpBuilder().build(resultPath);
        result.getHeaderInfo();
    }
}

