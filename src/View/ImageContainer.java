package View;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class ImageContainer {

    private static final String[] miscImagePaths = {"src/View/Images/NewerBackgroundImageOOP.png", "src/View/Images/CrosshairImageOOP.png"};
    private static final String[] PlayerImagePaths = {"src/View/Images/PlayerMove0.png", "src/View/Images/PlayerMove1.png", "src/View/Images/PlayerMove2.png"};
    private static final String[] MonsterTestImagePaths = {"src/View/Images/EnemyImageOOP.png"};
    private static BufferedImage[][] savedImagesMatrix;

    static void loadImages(){
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