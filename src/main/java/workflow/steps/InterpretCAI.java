package workflow.steps;

import workflow.BaseStep;
import workflow.exception.ExecutionStepException;
import workflow.models.ProcessedCommand;

public class InterpretCAI extends BaseStep {

    private ProcessedCommand processedCommand;

    public InterpretCAI(String receivedCommand) {
        super(BaseStep.INTERPRETER_STEP);

        getInfo(receivedCommand);
    }


    @Override
    public void execute() throws ExecutionStepException {
        // TODO: implement
        System.out.println("CAI command interpreted!");
    }

    private void getInfo(String receivedCommand) {
        this.processedCommand = new ProcessedCommand(receivedCommand);
    }

}
