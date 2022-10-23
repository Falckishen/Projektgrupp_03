package View;

import Model.Entities.Entity;
import Model.Entities.EntityType;
import Model.Position;

/**
 * @author William Johansson
 */
class ConversionQueryable {

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
            return(ImageTypeEnum.GRUNT);
        }
        if(entity.getEntityType() == EntityType.simpleProjectile){
            return(ImageTypeEnum.PLAYERPROJECTILE);
        }
        return(null);
    }
}
