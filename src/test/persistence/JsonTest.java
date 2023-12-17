package persistence;

import model.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

//TEST CLASS FOR Json
public class JsonTest {

    protected void checkTask(String name,int id, Task task){
        assertEquals(name, task.getNameID());
    }
}
