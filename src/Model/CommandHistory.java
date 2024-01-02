package Model;
// CommandHistory.java
import java.util.Stack;

public class CommandHistory {
    private Stack<Command> undoStack = new Stack<>();

    public void executeCommand(Command command) {
        // Execute the command
        command.execute();
        // Add the command to the undo stack
        undoStack.push(command);
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            // Undo the last command
            Command lastCommand = undoStack.pop();
            lastCommand.undo();
        }
    }
}
