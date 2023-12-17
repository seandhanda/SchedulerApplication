package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

// TEST CLASS FOR TASK
public class TaskTest {

    @BeforeEach
    public void runBefore(){
        Task task = new Task("", false);
    }

    @Test
    public void constructorTest() {
        Task t =  new Task("");
        assertEquals(t, new Task(""));
    }
}
