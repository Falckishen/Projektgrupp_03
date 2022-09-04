package View;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageContainer {
    private static final String[][] preCompileMatrix = {{"src\\View\\Images\\PlayerPNG.png"}};

    private static BufferedImage[][] imageMatrix;

    public static void compileImages(){
        BufferedImage[][] returnMatrix;
        returnMatrix = new BufferedImage[preCompileMatrix.length][0];
        BufferedImage newImage = null;
        for(int list_index=0; list_index < preCompileMatrix.length; list_index++) {
            returnMatrix[list_index] = new BufferedImage[preCompileMatrix[list_index].length];
            for (int elem_index = 0; elem_index < preCompileMatrix[list_index].length; elem_index++) {
                try {
                    newImage = ImageIO.read(new File(preCompileMatrix[list_index][elem_index]));
                } catch (IOException e) {
                    System.out.println("Failed to load >" + preCompileMatrix[list_index][elem_index]);
                }
                returnMatrix[list_index][elem_index] = newImage;
            }
        }
        imageMatrix = returnMatrix;
    }

    public static BufferedImage getImageFromTypeAndVariant(int type, int variant){
        return(imageMatrix[type][variant]);
    }
}