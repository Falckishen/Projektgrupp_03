package Model.Entities;

/**
 * An interface for creating entities of that are subtypes of Enemy.
 * @author Ida Altenstedt
 */
public interface AddEnemy {
    /**
     * The Interface for creating the most basic monster.
     */
    void createWeakMonster();

    void createStrongMonster();
}