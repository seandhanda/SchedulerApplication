package persistence;

import org.json.JSONObject;

//WRITEABLE CLASS
public interface Writable {
    // EFFECTS: returns this as JSON object
    public JSONObject toJson();
}