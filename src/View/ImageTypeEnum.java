package View;

enum ImageTypeEnum {
    MISC, PLAYER, PLAYERPROJECTILE, GRUNT, RICK;

    static int getIndex(ImageTypeEnum imageEnum){
        return(imageEnum.ordinal());
    }
}
