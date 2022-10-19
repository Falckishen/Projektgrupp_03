package Model.Entities;

/**
 * @author Ida Altenstedt
 */
class Projectile extends MovableEntity {

    private int lifeLeft;
    private final int attackPower;

    /**
     *
     * @param entityType
     * @param hitBoxRadiusX
     * @param hitBoxRadiusY
     * @param x
     * @param y
     * @param velocity The projectile's speed
     * @param piercingPower How many enemies the projectile can pierce in a row
     * @param direction the direction the projectile is moving
     * @param lifeLeft How many more frames it should be drawn before being considered dead.
     * @param attackPower The damage done on hit
     */
    protected Projectile(EntityType entityType, int hitBoxRadiusX, int hitBoxRadiusY, int x, int y, int velocity,
                         int piercingPower, Direction direction, int lifeLeft, int attackPower) {
        super(entityType, hitBoxRadiusX, hitBoxRadiusY, x, y, velocity,piercingPower);
        setDirection(direction);
        this.lifeLeft = lifeLeft;
        this.attackPower = attackPower;
    }

    protected int getAttackPower() {
        return attackPower;
    }

    protected void CollidedWithEnemy(){
        takeDamage(1);
    }

    protected void collidedWithNonLivingObject(){
        setIsDead();
    }

    @Override
    public void doOnTick() {
        if(lifeLeft>0){
            move();
            lifeLeft -= 1;
        } else {
            setIsDead();
        }
    }
}