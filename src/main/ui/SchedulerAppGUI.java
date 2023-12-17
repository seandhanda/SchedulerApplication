package ui;

import model.OutOfBoundsException;
import model.Task;
import model.ToDoList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

//CLASS REFERENCE: ListDemo.java
//GRAPHICAL USER INTERFACE SCHEDULER APPLICATION
public class SchedulerAppGUI extends JPanel implements ListSelectionListener {
    private static final String JSON_STORE = "./data/ToDoList.json";
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);

    private JList<Task> jlist;
    private DefaultListModel<Task> listModel;

    private static final String createString = "Create New Task!";
    private static final String removeString = "Remove Task!";
    private static final String displayString = "Display All Tasks!";
    private static final String saveString = "Save!";
    private static final String loadString = "Load!";
    private static final String markCompleteString = "Mark Task Complete";
    private static final String numTasksCompleteString = "Number of Tasks Complete";
    private static final String numTasksIncompleteString = "Number of Tasks Incomplete";
    private JButton createButton;
    private JButton removeButton;
    private JButton displayButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton markCompleteButton;
    private JButton numTasksCompleteButton;
    private JButton numTasksIncompleteButton;
    private JTextField taskNameID;
    private JTextField taskID;
    private JLabel labelTaskNameID;
    private JLabel labelTaskID;

    //REFERENCE: ListDemo.java
    //EFFECTS: runs the ToToList Application
    public SchedulerAppGUI() throws FileNotFoundException {
        super(new BorderLayout());
        playSound("./data/woohoo.wav");
        createAndShowGUI();
        init();


        //Create the list and put it in a scroll pane.
        JScrollPane listScrollPane = createJList();

        JButton createButton = new JButton(createString);
        CreateListener createListener = new CreateListener(createButton);
        createButton.setActionCommand(createString);
        createButton.addActionListener(createListener);
        createButton.setEnabled(false);

        removeButton = new JButton(removeString);
        removeButton.setActionCommand(removeString);
        removeButton.addActionListener(new RemoveListener());

        saveButton = new JButton(saveString);
        saveButton.setActionCommand(saveString);
        saveButton.addActionListener(new SaveListener());

        loadButton = new JButton(loadString);
        loadButton.setActionCommand(loadString);
        loadButton.addActionListener(new LoadListener());


        createPanel(listScrollPane, createButton, createListener);
        revalidate();
        repaint();
    }

    //REFERENCE: ListDemo.java
    //MODIFIES: this
    //EFFECTS: constructs bottom panel
    private void createPanel(JScrollPane listScrollPane, JButton createButton, CreateListener createListener) {
        taskNameID = new JTextField(10);
        taskNameID.addActionListener(createListener);
        taskNameID.getDocument().addDocumentListener(createListener);

        taskID = new JTextField(10);
        taskID.addActionListener(createListener);
        taskID.getDocument().addDocumentListener(createListener);

        //Create a panel that uses BoxLayout.
        JPanel buttonPane = boxLayoutPanel();

        labelTaskNameID = new JLabel("ENTER: NEW TASK   ");
        buttonPane.add(labelTaskNameID);
        buttonPane.add(taskNameID);

//        buttonPane.add(taskNameID);
//        labelTaskID = new JLabel("Enter Task ID ");
//        buttonPane.add(labelTaskID);
//        buttonPane.add(taskID);


        buttonPane.add(createButton);


        buttonPane.add(removeButton);
        if (listModel.size() == 0) {
            removeButton.setEnabled(false);
        }

        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(saveButton);
        buttonPane.add(loadButton);


        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }

    //REFERENCE: ListDemo.java
    //MODIFIES: this
    //EFFECTS: constructs boxLayoutPanel
    //    @org.jetbrains.annotations.NotNull
    private JPanel boxLayoutPanel() {
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        return buttonPane;
    }

    //REFERENCE: ListDemo.java
    //MODIFIES: this
    //EFFECTS: constructs Scroll panel
    private JScrollPane createJList() {
        jlist = new JList<Task>(listModel);
        jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jlist.setSelectedIndex(0);
        jlist.addListSelectionListener(this);
        jlist.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(jlist);
        return listScrollPane;
    }

    //REFERENCE: ListDemo.java
    //represents actionListener for remove task button
    class RemoveListener implements ActionListener {

        //REFERENCE: ListDemo.java
        //MODIFIES:this
        //EFFECTS: update list model to not include selected task; Phase 4: catches OutOfBoundsException!
        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever's selected.
            int index = jlist.getSelectedIndex();

            if (index > -1) {
                listModel.remove(index);
                try {
                    list.removeTask(list.getTaskInList(index));
                } catch (OutOfBoundsException outOfBoundsException) {
                    System.out.println("To-Do Schedule List Does Not Have That Many Indexes!");
                }
                playSound("./data/woohoo.wav");
            }


            int size = listModel.getSize();

            if (size == 0) { //No Tasks left, disable remove.
                removeButton.setEnabled(false);

            } else { //Select an index.
                if (index == listModel.getSize()) {
                    //removed item in last position
                    index--;
                }

                jlist.setSelectedIndex(index);
                jlist.ensureIndexIsVisible(index);
            }
        }
    }

    //REFERENCE: ListDemo.java
    //represents actionListener for save task button
    class SaveListener implements ActionListener {

        //REFERENCE: ListDemo.java
        //MODIFIES:this
        //EFFECTS: saving items in listmodel to json file
        public void actionPerformed(ActionEvent e) {
            try {
                jsonWriter.open();
                jsonWriter.write(list);
                jsonWriter.close();
                playSound("./data/woohoo.wav");
                //System.out.println("Saved Schedule to: " + JSON_STORE);
            } catch (FileNotFoundException ex) {
                //System.out.println("Cannot Write to File: " + JSON_STORE);
            }
        }
    }

    //REFERENCE: ListDemo.java
    //represents actionListener for save task button
    class LoadListener implements ActionListener {

        //REFERENCE: ListDemo.java
        //MODIFIES:this
        //EFFECTS: loading items from json file to listmodel
        public void actionPerformed(ActionEvent e) {
            try {
                list = jsonReader.read();
                //jlist = new JList<>();
                listModel.clear();

                for (Task task : list.getToDoList()) {
                    listModel.addElement(task);
                }

                playSound("./data/woohoo.wav");

                //System.out.println("Loaded Scheduler from " + JSON_STORE);
            } catch (IOException ex) {
                //System.out.println("Cannot Read From File: " + JSON_STORE);
            }
        }
    }


    //REFERENCE: ListDemo.java
    //This listener is shared by the text field and the create button.
    class CreateListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        //REFERENCE: ListDemo.java
        //MODIFIES: this
        //EFFECTS: constructs createListener
        public CreateListener(JButton button) {
            this.button = button;
        }

        //REQUIRES ActionListener
        //MODIFIES: this
        //EFFECTS: creates task, plays sound
        public void actionPerformed(ActionEvent e) {
            String commandInput = taskNameID.getText();
            if (commandInput.length() != 0) {


                try {
                    createTask();
                } catch (Exception exception) {
                    //System.out.println("Task already exists");
                }


                playSound("./data/woohoo.wav");

                String name = taskNameID.getText();
                //String id = taskID.getText();

                //User didn't type in a unique name or id...
//                if (name.equals("") || alreadyInList(name)) {
//                    Toolkit.getDefaultToolkit().beep();
//                    taskNameID.requestFocusInWindow();
//                    taskNameID.selectAll();
//                    return;
//                }

                int index = jlist.getSelectedIndex(); //get selected index
                if (index == -1) { //no selection, so insert at beginning
                    index = 0;
                } else {           //add after the selected item
                    index++;
                }

                //listModel.insertElementAt(task, index);
                //If we just wanted to add to the end, we'd do this:
                //listModel.addElement(taskName.getText());

                //Reset the text field.
                // taskID.setText("");
                taskNameID.setText("");

                //Select the new item and make it visible.
                jlist.setSelectedIndex(index);
                jlist.ensureIndexIsVisible(index);
            }
        }

        /*class DisplayListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                //This method can be called only if
                //there's a valid selection
                //so go ahead and remove whatever's selected.
                int index = jlist.getSelectedIndex();
                listModel.remove(index);

                int size = listModel.getSize();

                if (size == 0) { //No Tasks left, disable remove.
                    removeButton.setEnabled(false);

                } else { //Select an index.
                    if (index == listModel.getSize()) {
                        //removed item in last position
                        index--;
                    }

                    jlist.setSelectedIndex(index);
                    jlist.ensureIndexIsVisible(index);
                }
            }
        }*/

        //REFERENCE: ListDemo.java
        //EFFECTS: Returns true if name is already in ListModel; false otherwise.
        protected boolean alreadyInList(String name) {
            return listModel.contains(name);
        }

        //REFERENCE: ListDemo.java
        //Requires: DocumentListener
        //MODIFIES: this
        //EFFECTS: calls enableButton()
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        //REFERENCE: ListDemo.java
        //Requires: DocumentListener
        //MODIFIES: this
        //EFFECTS: calls handleEmptyTextField()
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        //REFERENCE: ListDemo.java
        //Requires: DocumentListener
        //MODIFIES: this
        //EFFECTS: calls enableButton if !handleEmptyTextField
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        //REFERENCE: ListDemo.java
        //MODIFIES: this
        //EFFECTS: if button is disabled, enables it
        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        //REFERENCE: ListDemo.java
        //MODIFIES: this
        //EFFECTS: returns true if getDocument length is less than 0, false otherwise
        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    //REFERENCE: ListDemo.java
    //REQUIRED: ListSelectionListener
    //EFFECTS: Enables and Disables RemoveButton
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (jlist.getSelectedIndex() == -1) {
                //No selection, disable remove button.
                removeButton.setEnabled(false);

            } else {
                //Selection, enable the remove button.
                removeButton.setEnabled(true);
            }
        }
    }


    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    //REFERENCE: ListDemo.java
    //MODIFIES: this
    //EFFECTS: Creates the GUI and shows it
    private void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("SchedulerApp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = null;
        newContentPane = this;
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setSize(1000, 1000);
        frame.setVisible(true);
    }


    private Scanner input;
    private Task task;
    private ToDoList list;
/*

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
*/
/*
    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("t")) {
            createTask();
        } else if (command.equals("r")) {
            removeTask();
        } else if (command.equals("c")) {
            markTaskComplete();
        } else if (command.equals("i")) {
            displayTasks();
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
    }*/


    // MODIFIES: this
    // EFFECTS: initializes tasks
    private void init() {
        task = new Task("", false);
        list = new ToDoList();
        input = new Scanner(System.in);
        listModel = new DefaultListModel<Task>();
    }


    /*
    //MODIFIES: this
    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tt -> Create New Task");
        System.out.println("\tr -> Remove Task");
        System.out.println("\tc -> Mark Task Complete");
        System.out.println("\ti -> Display All Tasks In Scheduler!");
        System.out.println("\tn -> Show Number of Tasks Already Completed!");
        System.out.println("\tm -> Show Number of Tasks Still Need to Do!");
        System.out.println("\ts -> Save Schedule to File");
        System.out.println("\tl -> Load Schedule from File");
        System.out.println("\tq -> Quit");
    }*/

    //MODIFIES: this
    //EFFECTS: creates a new task and adds it to list in scheduler
    private void createTask() throws Exception {
        String commandInput = taskNameID.getText();

        task.setNameID(commandInput);

        for (Task t : list.getToDoList()) {
            if (commandInput == t.getNameID()) {
                //System.out.println("Task Already Exists");
                return;
            }
        }

        Task tempTask = new Task(commandInput);
        if (!listModel.contains(tempTask)) {
            list.addTask(tempTask);


            //keep in mind
            listModel.addElement(tempTask);
        } else {
            createButton.setEnabled(false);
            throw new Exception();
        }


        //System.out.print("Task Created!");

    }

    //MODIFIES:this
    //EFFECTS:conducts removing task from scheduler
    private void removeTask() {
        try {
            //System.out.print("Enter task nameID");
            String commandInput = input.next();

            list.getToDoList();
            Task temp = list.find(commandInput);

            if (temp == null) {
                //System.out.println("Task Does not Exist");
            } else {
                list.getToDoList().remove(temp);
                listModel.removeElement(temp);
                //System.out.println("Task Removed!");
            }
        } catch (InputMismatchException e) {
            //System.out.println("It needs to be a Valid Integer!");
        }
    }


    //MODIFIES: this
    //EFFECTS: marks task complete in scheduler
    private void markTaskComplete() {
        try {
            //System.out.print("Enter task id");
            String commandInput = input.next();

            Task temp = null;
            for (Task t : list.getToDoList()) {
                if (t.getNameID() == commandInput) {
                    temp = t;
                }
            }
            if (temp != null) {
                temp.setComplete();
                //System.out.print("Task Complete!");
            } else {
                //System.out.println("Task Does Not Exist");
            }
        } catch (InputMismatchException e) {
            //System.out.println("It needs to be a Valid Integer!");
        }
    }

    //EFFECTS: returns all tasks in scheduler
    private void displayTasks() {
        //System.out.println("All Tasks:");

        if (list.numberTasksIncomplete() == 0 && list.numberTasksComplete() == 0) {
            //System.out.println("No Tasks Found, Sorry!");
        } else {
            //System.out.println(list.getToDoList());
        }
        //System.out.println("Enter any Key to Return Back to Menu!");
    }

    //EFFECTS: returns number of complete tasks in scheduler
    private void numberTasksComplete() {
        //System.out.println("Number of Tasks Completed:");
        //System.out.println(list.numberTasksComplete());
        //System.out.println("Enter any Key to Return Back to Menu!");
    }

    //EFFECTS: returns number of incomplete tasks in scheduler
    private void numberTasksIncomplete() {
        //System.out.println("Number of Tasks Remaining:");
        //System.out.println(list.numberTasksIncomplete());
        //System.out.println("Enter any Key to Return Back to Menu!");
    }

    /*//EFFECTS: saves the Scheduler to file
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
    }*/

    //METHOD REFERENCE: http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html
    //MODIFIES: this
    //EFFECTS: Sound Audio Created
    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound!");
            ex.printStackTrace();
        }
    }
}



/*displayButton = new JButton(displayString);
        displayButton.setActionCommand(displayString);
        //displayButton.addActionListener(new DisplayListener());*/