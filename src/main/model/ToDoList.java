package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

//A COLLECTION OF TASKS
public class ToDoList implements Writable {
    ArrayList<Task> toDoList;

    //EFFECTS: Constructs new ToDoList
    public ToDoList() {
        toDoList = new ArrayList<>();
    }

    //REQUIRES: task (Id) must not already be in toDoList
    //MODIFIES: this
    //EFFECTS: adds Task t to toDoList
    public void addTask(Task t) {
        toDoList.add(t);
    }

    //EFFECTS: if index is larger that list size, throws OutOfBoundsException; otherwise gets index
    public Task getTaskInList(int index) throws OutOfBoundsException {
        if (index > toDoList.size()) {
            throw new OutOfBoundsException();
        }
        return toDoList.get(index);
    }

    //REQUIRES: toDoList must already contain Task t
    //MODIFIES: this
    //EFFECTS: removes Task t from toDoList
    public void removeTask(Task t) {
        toDoList.remove(t);
    }

    //REQUIRES:toDoList must be >= 0
    //EFFECTS: produces number of tasks in toDoList that are incomplete
    public int numberTasksIncomplete() {
        int tasksIncomplete = 0;
        for (Task t : toDoList) {
            if (!t.isComplete()) {
                tasksIncomplete = tasksIncomplete + 1;
            }
        }
        return tasksIncomplete;
    }

    //REQUIRES:toDoList must be >= 0
    //EFFECTS: produces number of tasks in toDoList that are complete
    public int numberTasksComplete() {
        int tasksComplete = 0;
        for (Task t : toDoList) {
            if (t.isComplete()) {
                tasksComplete = tasksComplete + 1;
            }
        }
        return tasksComplete;
    }

    /*//REQUIRES:toDoList must be >= 0
    //EFFECTS: produces list of tasks in toDoList that are incomplete
    public ArrayList<Task> displayTasksIncomplete() {
        ArrayList<Task> displayTasksIncomplete = new ArrayList();
        for (Task t : toDoList) {
            if (!t.isComplete()) {
                displayTasksIncomplete.add(t);
            }
        }
        return displayTasksIncomplete;
    }

    //REQUIRES:toDoList must be >= 0
    //EFFECTS: produces list of tasks in toDoList that are complete
    public ArrayList<Task> displayTasksComplete() {
        ArrayList<Task> displayTasksComplete = new ArrayList();
        for (Task t : toDoList) {
            if (t.isComplete()) {
                displayTasksComplete.add(t);
            }
        }
        return displayTasksComplete;
    }*/

    //EFFECTS: gets toDoList
    public ArrayList<Task> getToDoList() {
        return toDoList;
    }

    //EFFECTS: transforms toDoList to String
    @Override
    public String toString() {
        return getToDoList().toString();
    }

    //EFFECTS: if int taskId is located in toDoList then return the Task, else return false
    public Task find(String taskId) {
        for (Task t : toDoList) {
            if (t.getNameID() == taskId) {
                return t;
            }
        }
        return null;
    }

    //EFFECTS: returns ToDoList as a JSON object
    //REFERENCE: JsonSerializationDemo // WorkRoomClass // toJson()
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("to-do list", toDoList);
        return json;
    }

    /*// EFFECTS: returns tasks in this ToDoList as a JSON array
    //REFERENCE: JsonSerializationDemo // WorkRoomClass // ThingiesToJson()
    private JSONArray toDoListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Task t : toDoList) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }*/
}
