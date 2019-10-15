package edu.bmputils;

public interface IBmp {
    void setIntToFourBytes(int offset, int value);

    void setPixelColor(int rowNumber, int position, int r, int g, int b);

    int getWidth();

    int getHeight();

    int[] getPixelColor(int rowNumber, int position);

    String getPixelInfo(int rowNumber, int position);

    void printHeaderInfo();
}
