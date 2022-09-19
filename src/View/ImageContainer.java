package View;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageContainer {
    static String[] miscImagePaths = {"src/View/Images/NewBackgroundOOP.png"};
    static String[] PlayerImagePaths = {"src/View/Images/PlayerImageOOP.png"};
    static String[] MonsterTestImagePaths = {"src/View/Images/EnemyImageOOP.png"};

    static BufferedImage[][] savedImagesMatrix;

    public static void loadImages(){
        savedImagesMatrix = new BufferedImage[UImageTypeEnum.values().length][0];
        savedImagesMatrix[UImageTypeEnum.getIndex(UImageTypeEnum.MISC)] = getLoadedFileList(miscImagePaths);
        savedImagesMatrix[UImageTypeEnum.getIndex(UImageTypeEnum.PLAYER)] = getLoadedFileList(PlayerImagePaths);
        savedImagesMatrix[UImageTypeEnum.getIndex(UImageTypeEnum.GRUNT)] = getLoadedFileList(MonsterTestImagePaths);
    }

    private static BufferedImage[] getLoadedFileList(String[] fileList){
        String filePath;
        BufferedImage[] outputList = new BufferedImage[fileList.length];
        for(int i=0; i < fileList.length; i++){
            filePath = fileList[i];
            try {
                outputList[i] = ImageIO.read(new File(filePath));
            } catch (IOException e) {
                System.out.println("Failed to load >" + filePath);
            }
        }
        return outputList;
    }

    public static BufferedImage getImageFromTypeVariant(UImageTypeEnum type, int variant){
        return(savedImagesMatrix[UImageTypeEnum.getIndex(type)][variant]);
    }
}