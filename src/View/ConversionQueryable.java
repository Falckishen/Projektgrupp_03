package View;

import Model.Entities.Entity;
import Model.Entities.Monster;
import Model.Entities.Player;
import Model.Entities.Position;

public class ConversionQueryable {
    static Position transformWithPlayerPosition(Position position, Position playerPosition){
        return(new Position(position.getX()-playerPosition.getX(), position.getY()- playerPosition.getY()));
    }

    static Position repeatingEverySize(Position position, int width, int height){
        return(new Position(Math.floorMod(position.getX()+width/2, width) - width/2, Math.floorMod(position.getY()+height/2, height) - height/2));
    }

    static UImageTypeEnum getImageType(Entity entity){
        if(entity.getClass() == Player.class){
            return(UImageTypeEnum.PLAYER);
        }
        if(entity.getClass() == Monster.class){
            return(UImageTypeEnum.GRUNT);
        }
        return(null);
    }
}
