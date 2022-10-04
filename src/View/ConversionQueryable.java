package View;

import Model.Entities.Entity;
import Utilities.EntityType;
import Utilities.Position;

import java.util.Random;

class ConversionQueryable {
    static ImageTypeEnum gruntType = null;

    static Position transformWithPlayerPosition(Position position, Position playerPosition){
        return(new Position(position.getX()-playerPosition.getX(), position.getY()- playerPosition.getY()));
    }

    static Position repeatingEverySize(Position position, int width, int height){
        return(new Position(Math.floorMod(position.getX()+width/2, width) - width/2, Math.floorMod(position.getY()+height/2, height) - height/2));
    }

    static ImageTypeEnum getImageType(Entity entity){
        if(entity.getEntityType() == EntityType.player){
            return(ImageTypeEnum.PLAYER);
        }
        if(entity.getEntityType() == EntityType.monster){
            if(gruntType == null){
                if(new Random().nextInt(10) == 1){
                    gruntType = ImageTypeEnum.RICK;
                }else{
                    gruntType = ImageTypeEnum.GRUNT;
                }
            }
            return(gruntType);
        }
        if(entity.getEntityType() == EntityType.simpleProjectile){
            return(ImageTypeEnum.PLAYERPROJECTILE);
        }
        return(null);
    }
}
