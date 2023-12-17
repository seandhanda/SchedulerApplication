package model;

import persistence.Writable;
import org.json.JSONObject;

import java.util.Objects;

//A SINGLE TASK WITH ID NUMBER, NAME DESCRIPTION, AND BOOLEAN COMPLETION STATUS
public class Task implements Writable {
    String nameID;
    boolean complete;

    //EFFECTS: tasks of same name assigned to be equals
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        Task task = (Task) o;
        return Objects.equals(nameID, task.nameID);
    }

    //EFFECTS: tasks of same name assigned to be equals
    @Override
    public int hashCode() {
        return Objects.hash(nameID);
    }

    //EFFECTS: Constructs new Task
    public Task(String nameID) {
        this.nameID = nameID;
        complete = false;
    }

    //EFFECTS: Constructs new Reader Task
    public Task(String nameID, boolean complete) {
        this.nameID = nameID;
        this.complete = complete;
    }

    //EFFECTS: gets task name
    public String getNameID() {
        return nameID;
    }

    //EFFECTS: gets task completion status
    public boolean isComplete() {
        return complete;
    }

    //MODIFIES: this
    //EFFECTS: sets task name to String str
    public void setNameID(String str) {
        this.nameID = str;
    }

    //MODIFIES: this
    //EFFECTS: sets tasks complete status to true
    public void setComplete() {
        complete = true;
    }

    //MODIFIES: this
    //EFFECTS: sets tasks complete status to false
    public void setIncomplete() {
        complete = false;
    }

    //EFFECTS: Displays TaskID and TaskName as String
    public String toString() {
        return "Task: " + nameID;
    }

    //EFFECTS: returns Task as a JSON object
    //REFERENCE: JsonSerializationDemo // ThingyClass // toJson()
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", nameID);
        return json;
    }
}

