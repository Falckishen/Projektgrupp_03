package Model;

import Model.Entities.Monster;
import Model.Entities.Position;

public class MonsterTest {
    public static void main(String[] args) {
        testAngle();
    }

    public static void testAngle() {
        // int coordX, int coordY, int hitboxWidthRadius, int hitboxHeightRadius
        Monster m = new Monster(0,0, 1, 1, 1);
        System.out.println(m.findDirectionToPosition(new Position(1,1)));
        System.out.println(m.findDirectionToPosition(new Position(1,0)));

        System.out.println(m.findDirectionToPosition(new Position(1,-1)));
        System.out.println(m.findDirectionToPosition(new Position(0,-1)));
        System.out.println(m.findDirectionToPosition(new Position(-1,-1)));

        System.out.println(m.findDirectionToPosition(new Position(-1,0)));
        System.out.println(m.findDirectionToPosition(new Position(-1, 1)));

        System.out.println(m.findDirectionToPosition(new Position(0,1)));


    }
}
