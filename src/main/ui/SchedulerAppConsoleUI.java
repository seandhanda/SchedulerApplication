package ui;

import model.Task;
import model.ToDoList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.json.JSONObject;

//CONSOLE-BASED USER INTERFACE SCHEDULER APPLICATION
public class SchedulerAppConsoleUI {
    private static final String JSON_STORE = "./data/ToDoList.json";
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);

    //EFFECTS: runs the ToToList Application
    public SchedulerAppConsoleUI() throws FileNotFoundException {
        runScheduler();
    }

    private Scanner input;
    private Task task;
    private ToDoList list;

    //MODIFIES:this
    //EFFECTS: processes user input
    private void runScheduler() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.nextLine();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
                input.nextLine();
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("t")) {
            createTask();
        } else if (command.equals("r")) {
            removeTask();
        } else if (command.equals("c")) {
            markTaskComplete();
        } else if (command.equals("n")) {
            numberTasksComplete();
        } else if (command.equals("m")) {
            numberTasksIncomplete();
        } else if (command.equals("s")) {
            saveScheduler();
        } else if (command.equals("l")) {
            loadScheduler();
        } else {
            System.out.println("Selection not valid...");
            System.out.println("Enter any Key to Return Back to Menu!");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes tasks
    private void init() {
        task = new Task("");
        list = new ToDoList();
        input = new Scanner(System.in);
    }

    //MODIFIES: this
    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tt -> Create New Task");
        System.out.println("\tr -> Remove Task");
        System.out.println("\tc -> Mark Task Complete");
        System.out.println("\tn -> Show Number of Tasks Already Completed!");
        System.out.println("\tm -> Show Number of Tasks Still Need to Do!");
        System.out.println("\ts -> Save Schedule to File");
        System.out.println("\tl -> Load Schedule from File");
        System.out.println("\tq -> Quit");
    }

    //MODIFIES: this
    //EFFECTS: creates a new task and adds it to list in scheduler
    private void createTask() {
        System.out.print("Enter task name");
        String commandInput = input.nextLine();

        task.setNameID(commandInput);

        for (Task t : list.getToDoList()) {
            if (commandInput == t.getNameID()) {
                System.out.println("Task Already Exists");
                System.out.println("");
                System.out.println("Enter any Key to Return Back to Menu!");
                return;
            }
        }

        list.addTask(new Task(commandInput));

        System.out.print("Task Created!");
        System.out.println("");
        System.out.println("Enter any Key to Return Back to Menu!");
    }

    //MODIFIES:this
    //EFFECTS:conducts removing task from scheduler
    private void removeTask() {
        try {
            System.out.print("Enter task id");
            String commandInput = input.next();

            list.getToDoList();
            Task temp = list.find(commandInput);

            if (temp == null) {
                System.out.println("Task Does not Exist");
            } else {
                list.getToDoList().remove(temp);
                System.out.println("Task Removed!");
            }
        } catch (InputMismatchException e) {
            System.out.println("It needs to be a Valid Integer!");
        }
    }


    //MODIFIES: this
    //EFFECTS: marks task complete in scheduler
    private void markTaskComplete() {
        try {
            System.out.print("Enter task id");
            String commandInput = input.next();

            Task temp = null;
            for (Task t : list.getToDoList()) {
                if (t.getNameID() == commandInput) {
                    temp = t;
                }
            }
            if (temp != null) {
                temp.setComplete();
                System.out.print("Task Complete!");
            } else {
                System.out.println("Task Does Not Exist");
            }
        } catch (InputMismatchException e) {
            System.out.println("It needs to be a Valid Integer!");
        }
    }

    //EFFECTS: returns number of complete tasks in scheduler
    private void numberTasksComplete() {
        System.out.println("Number of Tasks Completed:");
        System.out.println(list.numberTasksComplete());
        System.out.println("Enter any Key to Return Back to Menu!");
    }

    //EFFECTS: returns number of incomplete tasks in scheduler
    private void numberTasksIncomplete() {
        System.out.println("Number of Tasks Remaining:");
        System.out.println(list.numberTasksIncomplete());
        System.out.println("Enter any Key to Return Back to Menu!");
    }

    //EFFECTS: saves the Scheduler to file
    //REFERENCE: JsonSerializationDemo // WorkRoomApp.java // saveWorkRoom()
    private void saveScheduler() {
        try {
            jsonWriter.open();
            jsonWriter.write(list);
            jsonWriter.close();
            System.out.println("Saved Schedule to: " + JSON_STORE);
            System.out.println("Enter any Key to Return Back to Menu!");
        } catch (FileNotFoundException e) {
            System.out.println("Cannot Write to File: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads Scheduler from file
    //REFERENCE: JsonSerializationDemo // WorkRoomApp.java // loadWorkRoom()
    private void loadScheduler() {
        try {
            list = jsonReader.read();
            System.out.println("Loaded Scheduler from " + JSON_STORE);
            System.out.println("Enter any Key to Return Back to Menu!");
        } catch (IOException e) {
            System.out.println("Cannot Read From File: " + JSON_STORE);
        }
    }
}