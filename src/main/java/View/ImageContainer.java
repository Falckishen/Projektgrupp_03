package View;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class ImageContainer {
    private final static String imageSrcPath = "classes/Images/";

    private static final String[] miscImagePaths = {"NewerBackgroundImageOOP.png", "CrosshairImageOOP.png"};
    private static final String[] PlayerImagePaths = {"PlayerMove0.png", "PlayerMove1.png", "PlayerMove2.png"};
    private static final String[] PlayerProjectileImagePaths = {"PlayerProjectile.png"};
    private static final String[] MonsterTestImagePaths = {"EnemyImageOOP.png"};
    private static final String[] NumeralsImagePaths = {"7seg0.png", "7seg1.png", "7seg2.png", "7seg3.png",
            "7seg4.png", "7seg5.png", "7seg6.png", "7seg7.png", "7seg8.png", "7seg9.png"};
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
            filePath = imageSrcPath + fileList[i];
            try {
                outputList[i] = ImageIO.read(new File(filePath));
            } catch (IOException e) {
                System.out.println("Failed to load >" + filePath);
            }
        }
        return outputList;
    }

    /**
     * @param type the type of image to get
     * @param variant the variant of the specified type
     * @return The image of the specified type and variant
     */
    static BufferedImage getImageFromTypeVariant(ImageTypeEnum type, int variant){
        return(savedImagesMatrix[type.ordinal()][variant]);
    }
}