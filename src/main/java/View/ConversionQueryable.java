package View;

import Model.Entities.Entity;
import Model.Entities.EntityType;
import Model.Position;

/**
 * @author William Johansson
 */
class ConversionQueryable {

    /**
     * Transforms the inputed position into a position relative to the players position
     * @param position Position to transform
     * @param playerPosition Players position
     * @return Transformed position
     */
    static Position transformWithPlayerPosition(Position position, Position playerPosition){
        return(new Position(position.getX()-playerPosition.getX(), position.getY()- playerPosition.getY()));
    }

    /**
     * Loops the position such that it repeats every width or height
     * @param position The position to loop
     * @param width the width of the loop
     * @param height the height of the loop
     * @return The looped position
     */
    static Position repeatingEverySize(Position position, int width, int height){
        return(new Position(Math.floorMod(position.getX()+width/2, width) - width/2, Math.floorMod(position.getY()+height/2, height) - height/2));
    }

    /**
     * Gets the corresponding ImageTypeEnum to a entity
     * @param entity entity to convert
     * @return The resulting ImageTypeEnum
     */
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
