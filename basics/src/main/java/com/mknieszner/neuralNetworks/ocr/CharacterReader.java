package com.mknieszner.neuralNetworks.ocr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CharacterReader {
    public void redImage(String path) throws IOException {
        BufferedImage image = ImageIO.read(new File(path));
        byte[][] pixels = new byte[image.getWidth()][];
        System.out.print("new float[] {");
        for (int i = 0; i < image.getWidth(); i++) {
            pixels[i] = new byte[image.getHeight()];

            for (int j = 0; j < image.getHeight(); j++) {
                pixels[i][j] = (byte) (image.getRGB(i, j) == 0xFFFFFFFF ? 0 : 1);
                System.out.print(pixels[i][j] + ",");
            }
        }
        System.out.println("},");
    }
}
