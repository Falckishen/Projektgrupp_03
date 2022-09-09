package Model.Weapons;

public class WeaponFactory {

    public static Weapon getGun(){
        return new SingleShooter("Gun",32,7,40);
    }

    public static Weapon getTestWeapon(){
        return new SingleShooter("testWeapon",2,50,60);
    }
}
