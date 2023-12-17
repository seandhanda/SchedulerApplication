package persistence;

import model.ToDoList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.json.JSONObject;


// Represents a writer that writes JSON representation of ToDoList to file
//CLASS REFERENCE: JasonSerializationDemo // JsonWriter CLASS
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    // REFERENCE: JasonSerializationDemo // JsonWriter CLASS
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    // REFERENCE: JasonSerializationDemo // JsonWriter CLASS
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of ToDoList to file
    // REFERENCE: JasonSerializationDemo // JsonWriter CLASS
    public void write(ToDoList toDoList) {
        JSONObject json = toDoList.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    // REFERENCE: JasonSerializationDemo // JsonWriter CLASS
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    // REFERENCE: JasonSerializationDemo // JsonWriter CLASS
    private void saveToFile(String json) {
        writer.print(json);
    }
}
