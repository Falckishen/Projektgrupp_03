package View;

public enum UImageTypeEnum {
    MISC, PLAYER, GRUNT;

    private static UImageTypeEnum[] allEnums = UImageTypeEnum.values();

    public static int getIndex(UImageTypeEnum imageEnum){
        for(int i=0; i < allEnums.length; i++){
            if(imageEnum.equals(allEnums[i])){
                return(i);
            }
        }
        return(-1);
    }
}
