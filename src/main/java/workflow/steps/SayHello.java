package workflow.steps;

import workflow.BaseStep;
import workflow.ProgramInfo;

public class SayHello extends BaseStep {

    public SayHello(ProgramInfo info) {
        super(BaseStep.HELLO_STEP, info);
    }

    @Override
    public void execute() {
        // TODO: implement
        System.out.println("HOLA!");
    }
}
