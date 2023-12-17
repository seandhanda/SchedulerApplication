package persistence;

import model.Task;
import model.ToDoList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;


// Represents a reader that reads ToDoList from JSON data stored in file
//CLASS REFERENCE: JasonSerializationDemo // JsonReader CLASS
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    // REFERENCE: JasonSerializationDemo // JsonReader CLASS
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads ToDoList from file and returns it;
    //          otherwise throws IOException if an error occurs reading data from file
    // REFERENCE: JasonSerializationDemo // JsonReader CLASS
    public ToDoList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseToDoList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    //          otherwise throws IOException if an error occurs reading data from file
    // REFERENCE: JasonSerializationDemo // JsonReader CLASS
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses ToDoList from JSON object and returns it
    // REFERENCE: JasonSerializationDemo // JsonReader CLASS
    private ToDoList parseToDoList(JSONObject jsonObject) {
        ToDoList list = new ToDoList();
        JSONArray toDo = jsonObject.getJSONArray("to-do list");
        for (Object task : toDo) {
            JSONObject task1 = (JSONObject) task;

            String name = task1.getString("nameID");
            boolean complete = task1.getBoolean("complete");

            Task task2 = new Task(name, complete);
            list.addTask(task2);
        }
        return list;
    }

 /*   // MODIFIES: toDoList
    // EFFECTS: parses Tasks from JSON object and adds them to ToDoList
    // REFERENCE: JasonSerializationDemo // JsonReader CLASS
    private void addTasks(ToDoList toDoList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("tasks");
        for (Object json : jsonArray) {
            JSONObject nextTask = (JSONObject) json;
            addTask(toDoList, nextTask);
        }
    }*/

/*    // MODIFIES: toDoList
    // EFFECTS: parses task from JSON object and adds it to ToDoList
    // REFERENCE: JasonSerializationDemo // JsonReader CLASS
    private void addTask(ToDoList toDoList, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int id = jsonObject.getInt("id");
        Task task = new Task(name, id);
        toDoList.addTask(task);
    }*/
}

