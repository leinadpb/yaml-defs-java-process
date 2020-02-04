package workflow.steps;

import workflow.BaseStep;
import workflow.ProgramInfo;
import workflow.exception.ExecutionStepException;

public class SayHello extends BaseStep {

    public SayHello(ProgramInfo info) {
        super(BaseStep.HELLO_STEP, info);
    }

    @Override
    public void execute() throws ExecutionStepException {
        // TODO: implement
        System.out.println("HOLA!");
    }
}
