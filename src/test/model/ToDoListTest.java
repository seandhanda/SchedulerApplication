package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//TEST CLASS FOR ToDoList
class ToDoListTest {
    ToDoList list;

    @BeforeEach
    public void runBefore() {
        list = new ToDoList();
    }

    @Test
    public void addTask() {
        Task t = new Task("Wash Dishes", false);
        list.addTask(t);

        assertEquals(1, list.numberTasksIncomplete());
        list.toString();
        list.getToDoList().toString();
        Task t1 = new Task("", false);
        assertEquals(null, list.find("2"));
        assertFalse(t1.equals(null));
    }

    @Test
    public void removeTaskTrue() {
        Task t = new Task("Wash Dishes", false);
        assertEquals("Wash Dishes", t.getNameID());
        list.addTask(t);

        list.removeTask(t);
        assertEquals(0, list.numberTasksIncomplete());
        assertEquals(0, list.numberTasksComplete());
    }

    @Test
    public void removeTaskFalse() {
        Task t = new Task("Wash Dishes", false);
        Task t2 = new Task("Iron Clothes", false);
        list.addTask(t);

        list.removeTask(t2);
        assertEquals(1, list.numberTasksIncomplete());
        assertEquals(0, list.numberTasksComplete());
    }

    @Test
    public void numberOfTasksCompleteEmptyTest() {
        Task t = new Task("Wash Dishes", false);
        assertFalse(t.isComplete());

        assertEquals(0, list.numberTasksComplete());
        assertEquals(0, list.numberTasksIncomplete());

    }

    @Test
    public void numberOfTasksCompleteTrueTest() {
        Task t = new Task("Wash Dishes", false);
        Task t2 = new Task("Iron Clothes", false);
        list.addTask(t);
        list.removeTask(t);
        assertFalse(t.isComplete());
        t.setComplete();
        assertTrue(t.isComplete());
        list.addTask(t);
        list.addTask(t2);
        assertTrue(t.isComplete());
        list.getToDoList();


        assertEquals(1, list.numberTasksComplete());
        assertEquals(1, list.numberTasksIncomplete());
    }


    @Test
    public void numberOfTasksCompleteFalseTest() {
        Task t = new Task("Wash Dishes", false);
        Task t2 = new Task("Iron Clothes", false);
        t.setComplete();
        assertTrue(t.isComplete());
        t.setIncomplete();
        assertFalse(t.isComplete());
        list.addTask(t);
        list.addTask(t2);

        assertEquals(0, list.numberTasksComplete());
        assertEquals(2, list.numberTasksIncomplete());
    }

    @Test
    public void setIncompleteTest() {
        Task t = new Task("Wash Dishes", false);
        list.addTask(t);
        list.removeTask(t);
        t.setComplete();
        assertTrue(t.isComplete());
        t.setIncomplete();
        assertFalse(t.isComplete());
        list.addTask(t);
        list.getToDoList();

        assertEquals(0, list.numberTasksComplete());
        assertEquals(1, list.numberTasksIncomplete());
    }

    @Test
    public void getNameTest() {
        Task t = new Task("Wash Dishes", false);
        t.setNameID("Clean Clothes");
        assertEquals("Clean Clothes", t.getNameID());
    }

    @Test
    public void findTrueTest() {
        Task t = new Task("Wash Dishes", false);
        list.addTask(t);
        assertEquals(t, list.find(t.getNameID()));
    }

    @Test
    public void findFullFalseTest() {
        Task t = new Task("Wash Dishes", false);
        list.addTask(t);
        assertEquals(t, list.find("Wash Dishes"));
    }

    @Test
    public void taskConstructorTest(){
        Task t = new Task("hello", true);
        assertEquals("hello", t.getNameID());
        assertEquals("hello", t.getNameID());
        assertEquals(true, t.isComplete());
    }

    @Test
    public void toJsonTest(){
        JSONObject json = new JSONObject();
        Task task = new Task("", false);
        task.toJson(); }

    @Test
    public void hashcodeTest(){
        Task t = new Task("hello", true);
        assertEquals(99162353, t.hashCode());
        hashCode();
    }

    @Test
    public void OutOfBoundsExceptionNotExpectedTest() {
        Task t = new Task("");
        list.addTask(t);
        try{
            list.getTaskInList(0);
            assertEquals(t, list.getTaskInList(0));
            //expected
        } catch (OutOfBoundsException outOfBoundsException){
            fail("OutOfBoundsException thrown, No Exception Expected");
        }
    }

    @Test
    public void OutOfBoundsExceptionExpectedTest() {
        Task t = new Task("");
        list.addTask(t);
        try{
            list.getTaskInList(5);
            fail("No Exception Thrown, OutOfBoundsException expected");
        } catch (OutOfBoundsException outOfBoundsException){
            //expected
        }
    }
}