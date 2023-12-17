package ui;

import java.io.FileNotFoundException;

//MAIN CLASS
public class Main {

    //EFFECTS: runs schedulerApp Console-Based UI and Graphical Based UI
    public static void main(String[] args) {
        try {
            new SchedulerAppGUI();
            new SchedulerAppConsoleUI();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot Run Application: file not found!");
        }
    }
}





//NOT NEEDED

       /* markCompleteButton = new JButton(markCompleteString);
        markCompleteButton.setActionCommand(markCompleteString);
        markCompleteButton.addActionListener(new MarkCompleteListener());

        numTasksIncompleteButton = new JButton(numTasksIncompleteString);
        numTasksIncompleteButton.setActionCommand(numTasksIncompleteString);
        numTasksIncompleteButton.addActionListener(new NumTasksIncompleteListener());

        numTasksCompleteButton = new JButton(numTasksCompleteString);
        numTasksCompleteButton.setActionCommand(numTasksCompleteString);
        numTasksCompleteButton.addActionListener(new NumTasksCompleteListener());*/


//NOTES FOR MYSELF, PLEASE IGNORE


//ALREADY FIXED BUGS, STUDY FOR LATER!
/*

remove does not say' item does not exist' in a non-empty list
= create new function called find
_____________

exception, for command inputting letter key instead of int key

-------------

code coverage
= make sure to check code coverage!

 -------------

 *What does modifies refer to in ui?

 -------------

 */

//Additional Methods for After Phase 1:
/*  //EFFECTS: creates new list of all incomplete tasks
    public ArrayList<Task> needToDo() {
        ArrayList<Task> needToDo = new ArrayList<>();
        for (Task t : toDoList) {
            if (t.isComplete() == false) {
                needToDo.add(t);
            }
        }
        return needToDo;

}*/


//PHASE 2
// JSON DATA STORAGE: when we load past saved data, it hasn't already saved the loaded memory, instead it creates a new
//environment from the JSON stored data. IT CONSTRCUTS NEW ENVIRONMENT FROM SAVED DATA IN JSON.


// IN TASKCLASS, public JSONObject toJson(), do i need to add complete
//JSON IMPORT PROBLEM SOLUTION? = FILE , PROGRAM STRUCTURE, LIBRARIES, PLUS SIGN   -> FIGURE OUT WHY!


//JSONREADERTEST and JSONWRITER TEST:  //assertEquals(toDoList, toDoList.getToDoList());





//PHASE 3
//CREATED NEW USER STORY: DISPLAY ALL TASKS

//MY DISPLAYTASKS METHOD DOESN'T DISPLAY SPECIFICS LIKE "NAME" AND "ID", IT JUST HAS THE TASK ITSELF.
//SHOULD I ADJUST NUMCOMPLETETASKS AND NUMINCOMPLETE TASKS TO DISPLAY THE TASKS INSTEAD OF NUMBER OF TASKS???? = LATER


//MARKTASKINCOMPLETE IS POSSIBLE USER STORY



//PHASE 3 BUG SOLUTIONS:
// 1. DUPLICATE TASKS = GUARD IN CREATETASK()
// 2. SAVE: SAVE FUNCTION DOES NOT HANDLE ALL CASES!  REMOVE CREATRELISTENER IS SUPPOSED TO REMOVE FROM JSON WL AS WELL!
// 3. LOAD NOT FINDING KEY VALUE AND NOT PRINTING = I WAS SEARCHING FOR KEY INSTEAD OF VALUE!

/*
if (taskName.getText().equals("") || taskID.getText().equals("")) {
        createButton.setEnabled(false);
        } else {
        createButton.setEnabled(true);*/


//UPDATE:
// NO MORE BUGS!!!

//COMPLETED PHASE 3











//PHASE 4

//Task 1 Complete
//Task 2 Complete- OutOfBoundsException - ReadME
//Task 3 Complete- UML_Design_Diagram.pdf - ReadME

//PHASE 4 COMPLETE!






//PERSONAL PROJECT- SCHEDULER APPLICATION- COMPLETE!!!
