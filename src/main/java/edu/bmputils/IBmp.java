package edu.bmputils;

public interface IBmp {
    void setPixelColor(int rowNumber, int position, Pixel pixel);

    int getWidth();

    int getHeight();

    Pixel getPixelColor(int rowNumber, int position);

    String getPixelInfo(int rowNumber, int position);

    void printHeaderInfo();
}
