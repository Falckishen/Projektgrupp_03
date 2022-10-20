package Model.Entities;

import Model.Position;

/**
 * An interface for creating entities that are subtypes of Projectile.
 * @author Ida Altenstedt
 */
public interface AddProjectile {
    /**
     *
     * @param position the position the projectile is launched from.
     * @param direction the direction the projectile is launched in.
     * @param velocity the speed with which the projectile is launched.
     * @param life for how many frames the projectile is "alive" before disappearing.
     * @param attackPower the damage done by the projectile on hit.
     */
    void createSimpleProjectile(Position position, Direction direction, int velocity, int life, int attackPower);
}