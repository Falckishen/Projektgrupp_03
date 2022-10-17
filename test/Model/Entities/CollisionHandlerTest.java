package Model.Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollisionHandlerTest {
    EntityCreator EC;
    @BeforeEach
    void init(){
        EC = new EntityCreator(1000,2);
    }
    @Test
    void testTest(){
        assertTrue(true);
    }
}