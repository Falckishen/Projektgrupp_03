package View;

enum UImageTypeEnum {
    MISC, PLAYER, PLAYERPROJECTILE, GRUNT;

    static int getIndex(UImageTypeEnum imageEnum){
        return(imageEnum.ordinal());
    }
}
