package View;

import Model.Entities.MovableEntity;
import Utilities.EntityType;
import Utilities.Position;

class ConversionQueryable {

    static Position transformWithPlayerPosition(Position position, Position playerPosition){
        return(new Position(position.getX()-playerPosition.getX(), position.getY()- playerPosition.getY()));
    }

    static Position repeatingEverySize(Position position, int width, int height){
        return(new Position(Math.floorMod(position.getX()+width/2, width) - width/2, Math.floorMod(position.getY()+height/2, height) - height/2));
    }

    static UImageTypeEnum getImageType(MovableEntity movableEntity){
        if(movableEntity.getEntityType() == EntityType.player){
            return(UImageTypeEnum.PLAYER);
        }
        if(movableEntity.getEntityType() == EntityType.monster){
            return(UImageTypeEnum.GRUNT);
        }
        if(movableEntity.getEntityType() == EntityType.simpleProjectile){
            return(UImageTypeEnum.PLAYERPROJECTILE);
        }
        return(null);
    }
}
