package View;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class ImageContainer {

    private static final String[] miscImagePaths = {"src/View/Images/NewerBackgroundImageOOP.png", "src/View/Images/CrosshairImageOOP.png"};
    private static final String[] PlayerImagePaths = {"src/View/Images/PlayerMove0.png", "src/View/Images/PlayerMove1.png", "src/View/Images/PlayerMove2.png"};
    private static final String[] PlayerProjectileImagePaths = {"src/View/Images/PlayerProjectile.png"};
    private static final String[] MonsterTestImagePaths = {"src/View/Images/EnemyImageOOP.png"};
    private static final String[] RickImagePaths = {"src/View/Images/Rick0Image.png", "src/View/Images/Rick1Image.png", "src/View/Images/Rick2Image.png",
            "src/View/Images/Rick3Image.png", "src/View/Images/Rick4Image.png", "src/View/Images/Rick5Image.png", "src/View/Images/Rick6Image.png",
            "src/View/Images/Rick7Image.png", "src/View/Images/Rick8Image.png", "src/View/Images/Rick9Image.png", "src/View/Images/Rick10Image.png",
            "src/View/Images/Rick11Image.png"};
    private static final String[] NumeralsImagePaths = {"src/View/Images/7seg0.png", "src/View/Images/7seg1.png",
            "src/View/Images/7seg2.png", "src/View/Images/7seg3.png", "src/View/Images/7seg4.png", "src/View/Images/7seg5.png",
            "src/View/Images/7seg6.png", "src/View/Images/7seg7.png", "src/View/Images/7seg8.png", "src/View/Images/7seg9.png"};
    private static BufferedImage[][] savedImagesMatrix;

    /**
     * Loads all the images contained in the different image paths and places them in a general image matrix that can be
     * accessed through getImageFromTypeVariant.
     */
    static void loadImages(){
        savedImagesMatrix = new BufferedImage[ImageTypeEnum.values().length][0];
        savedImagesMatrix[ImageTypeEnum.MISC.ordinal()] = getLoadedFileList(miscImagePaths);
        savedImagesMatrix[ImageTypeEnum.PLAYER.ordinal()] = getLoadedFileList(PlayerImagePaths);
        savedImagesMatrix[ImageTypeEnum.PLAYERPROJECTILE.ordinal()] = getLoadedFileList(PlayerProjectileImagePaths);
        savedImagesMatrix[ImageTypeEnum.GRUNT.ordinal()] = getLoadedFileList(MonsterTestImagePaths);
        savedImagesMatrix[ImageTypeEnum.RICK.ordinal()] = getLoadedFileList(RickImagePaths);
        savedImagesMatrix[ImageTypeEnum.NUMERAL.ordinal()] = getLoadedFileList(NumeralsImagePaths);
    }

    /**
     * Loads a list of filepaths and returns the loaded BufferedImages in a list
     * @param fileList Filepaths to load
     * @return List of loaded images
     */
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

    static BufferedImage getImageFromTypeVariant(ImageTypeEnum type, int variant){
        return(savedImagesMatrix[type.ordinal()][variant]);
    }
}