package persistence;

import model.Task;
import model.ToDoList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//TEST CLASS FOR JsonWriter
public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            ToDoList toDoList = new ToDoList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testWriterEmptyToDoList() {
        try {
            ToDoList toDoList = new ToDoList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyToDoList.json");
            writer.open();
            writer.write(toDoList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyToDoList.json");
            toDoList = reader.read();
            assertEquals(0, toDoList.numberTasksComplete());
            assertEquals(0, toDoList.numberTasksIncomplete());
        } catch (IOException e) {
            fail("IO Exception thrown, no Exception expected");
        }
    }

    @Test
    void testWriterGeneralToDoList() {
        try {
            ToDoList toDoList = new ToDoList();
            toDoList.addTask(new Task("Take out Garbage", false));
            toDoList.addTask(new Task("Wash the Dishes", false));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralToDoList.json");
            writer.open();
            writer.write(toDoList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralToDoList.json");
            toDoList = reader.read();
            List<Task> tasks = toDoList.getToDoList();
            assertEquals(2, toDoList.numberTasksIncomplete());
            assertEquals(0, toDoList.numberTasksComplete());
        } catch (IOException e) {
            fail("IO Exception thrown, no Exception expected");
        }
    }
}