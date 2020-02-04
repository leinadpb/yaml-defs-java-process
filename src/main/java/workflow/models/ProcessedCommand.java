package workflow.models;

import java.util.HashMap;

public class ProcessedCommand {
    private Command command;
    private HashMap<String, String> commandInfo;

    public ProcessedCommand(String commandStr) {
        command = new Command();
        commandInfo = new HashMap<String, String>();
    }
}
