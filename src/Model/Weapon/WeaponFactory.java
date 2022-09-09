package Model.Weapon;

public class WeaponFactory {
    static Weapon getGun(){
        return new SingleShooter();
    }
}
