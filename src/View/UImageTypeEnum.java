package View;

enum UImageTypeEnum {
    MISC, PLAYER, PLAYERPROJECTILE, GRUNT;

    private static final UImageTypeEnum[] allEnums = UImageTypeEnum.values();

    static int getIndex(UImageTypeEnum imageEnum){
        for(int i=0; i < allEnums.length; i++){
            if(imageEnum.equals(allEnums[i])){
                return(i);
            }
        }
        return(-1);
    }
}
