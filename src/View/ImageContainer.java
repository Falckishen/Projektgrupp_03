package View;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class ImageContainer {

    private static final String[] miscImagePaths = {"src/View/Images/NewerBackgroundImageOOP.png", "src/View/Images/CrosshairImageOOP.png"};
    private static final String[] PlayerImagePaths = {"src/View/Images/PlayerMove0.png", "src/View/Images/PlayerMove1.png", "src/View/Images/PlayerMove2.png"};
    private static final String[] PlayerProjectileImagePaths = {"src/View/Images/PlayerProjectile.png"};
    private static final String[] MonsterTestImagePaths = {"src/View/Images/EnemyImageOOP.png"};
    private static final String[] RickImagePaths = {"src/View/Images/Rick0Image.png", "src/View/Images/Rick1Image.png", "src/View/Images/Rick2Image.png",
            "src/View/Images/Rick3Image.png", "src/View/Images/Rick4Image.png", "src/View/Images/Rick5Image.png", "src/View/Images/Rick6Image.png",
            "src/View/Images/Rick7Image.png", "src/View/Images/Rick8Image.png", "src/View/Images/Rick9Image.png", "src/View/Images/Rick10Image.png",
            "src/View/Images/Rick11Image.png"};
    private static BufferedImage[][] savedImagesMatrix;

    static void loadImages(){
        savedImagesMatrix = new BufferedImage[ImageTypeEnum.values().length][0];
        savedImagesMatrix[ImageTypeEnum.getIndex(ImageTypeEnum.MISC)] = getLoadedFileList(miscImagePaths);
        savedImagesMatrix[ImageTypeEnum.getIndex(ImageTypeEnum.PLAYER)] = getLoadedFileList(PlayerImagePaths);
        savedImagesMatrix[ImageTypeEnum.getIndex(ImageTypeEnum.PLAYERPROJECTILE)] = getLoadedFileList(PlayerProjectileImagePaths);
        savedImagesMatrix[ImageTypeEnum.getIndex(ImageTypeEnum.GRUNT)] = getLoadedFileList(MonsterTestImagePaths);
        savedImagesMatrix[ImageTypeEnum.getIndex(ImageTypeEnum.RICK)] = getLoadedFileList(RickImagePaths);
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

    public static BufferedImage getImageFromTypeVariant(ImageTypeEnum type, int variant){
        return(savedImagesMatrix[ImageTypeEnum.getIndex(type)][variant]);
    }
}