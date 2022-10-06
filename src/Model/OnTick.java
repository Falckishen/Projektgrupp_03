package Model;

/**
 * An interface used by every object that will update when a tick occurs.
 * @author Ida Altenstedt
 */
public interface OnTick {
    /**
     * The method called every time an update occurs.
     */
    void doOnTick();
}