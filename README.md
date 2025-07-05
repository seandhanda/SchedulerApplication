<h1><strong>Scheduler Application</strong></h1>

<h2>A SOLUTION TO MISSED DEADLINES</h2>

<strong>What My Application Will Do:</strong><br>
<ul>
<li> Keeps Track of Things to Do</li>
<li> Crosses Things Off List</li>
<li> Displays List of All Items or only Outstanding Items!</li>
</ul>

<p> This program can be used by: <strong>anyone with a busy schedule that needs a free personal assistant
to keep them on track of things!</strong></p>

<p> <strong>What does this project mean to me?</strong><br>
During my first year in university, I always seemed to forget due dates- whether that be 
 assignment deadlines, exam dates (yikes!), or get togethers with friends and family. This 
 software will immediately get rid of these problems!</p>

<h2>USER STORIES:</h2> 
<ul>
<li> As a user, I want to be able to add a task to my to-do list</li>
<li> As a user, I want to be able to remove a task from my to-do-list</li>
<li> As a user, I want to be able to view the list of tasks currently in my to-do-list</li>
<li> NON-GUI: As a user, I want to be able to mark a task as complete on my to-do list</li>
<li> NON-GUI: As a user, I want to be able to see the number of incomplete and number of completed tasks on my to-do list</li>
</ul>
DATA PERSISTENCE
<ul>
<li> As a user, I want to be able to save my to-do list to file</li>
<li> As a user, I want to be able to be able to load my to-do list from file </li>
</ul>
<h2>PHASE 4</h2> 
PHASE 4: TASK 2
<ul>
<li> Option 1: Test and design a class in your model package that is robust.  You must have at least one method that throws a checked exception.  You must have one test for the case where the exception is expected and another where the exception is not expected.</li>
<li> Throwing the Checked OutOfBoundsException -->  Class: Main.Model.ToDoList-----> Method: getTaskInList() </li> 
<li> Catching the Checked OutOfBoundsException -->  Class: Main.UI.SchedulerAppGUI.RemoveListener-----> Method: actionPerformed(ActionEvent e) </li>
<li> Testing the Checked OutOfBoundsException  -->  Class: Test.Model.ToDoListTest -----> Methods: OutOfBoundsExceptionNotExpectedTest & OutOfBoundsExceptionExpectedTest </li>
</ul>
PHASE 4: TASK 3
<p><u>Refactoring I would implement (if I had more time!) to improve my program design:</u></p>
<p>All Cases of Refactoring UML_Design_Diagram.pdf to Reduce Coupling:</p>
<ul>
<li>There are many instances of MULTIPLE PATHS in my UML Class Diagram- there should only be SINGULAR PATHS present!</li>
<li>The SchedulerAppConsoleUI should REMOVE ITS ASSOCIATION with the Task Class and go through ToDoList instead to reduce COUPLING.</li>
<li>The SchedulerAppGUI should REMOVE ITS ASSOCIATION with the Task Class and go through ToDoList instead to reduce COUPLING.</li>
<li>The duplication of code amongst the RemoveListener, SaveListener, LoadListener, and CreateListener Class is design-flawed. An interface should be EXTRACTED and contain the signatures of the duplicate methods within these classes.</li>
<li>The SchedulerAppConsoleUI and SchedulerAppGUI should extend a SUPERCLASS "UserInterface" to reduce coupling!</li>
</ul>
<p>All Cases of Refactor/Extract/Delegate to Improve Cohesion: </p>
<ul>
<li>The numberTasksComplete and numberTasksIncomplete methods in the ToDoList Class should be DELEGATED to a "Calculation" Class to maintain a Single Point of Responsibility.</li>
</ul>
<p>All Cases of Refactor/Extract Method to improve design (too many specific cases to list all): </p>
<li>For every confusing CODE BLOCK in my Main Package, I would Refactor/ExtractMethod with a descriptive method name to make the code more readable.</li>
<li>For every instance of CODE DUPLICATION in my Main Package, I would Refactor/ExtractMethod to make the code adhere to design principles.</li>
<li>For every LOOP in my Main Package, I would Refactor/ExtractMethod to make the code more concise.</li>
