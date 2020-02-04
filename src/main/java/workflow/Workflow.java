package workflow;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import workflow.exception.ExecutionStepException;
import workflow.models.StepDef;
import workflow.models.StepDefinition;
import workflow.models.WorkflowState;
import workflow.steps.InterpretCAI;
import workflow.steps.SayHello;
import workflow.steps.SendEmail;
import java.io.File;
import java.util.*;

public class Workflow {

    private List<BaseStep> steps;
    private ObjectMapper mapper;
    WorkflowState state;
    private ProgramInfo programInfo;
    private List<BaseStep> failedSteps;

    public Workflow(ProgramInfo info) {
        steps = new ArrayList<BaseStep>();
        mapper = new ObjectMapper(new YAMLFactory());
        state = WorkflowState.INIT;
        programInfo = info;
    }

    // TODO: Create a custom Exception model
    public void build(String stepsDefinitionPath) throws Exception {

        if (state == WorkflowState.INIT) {

            try {
                System.out.println(System.getProperty("user.dir"));

                StepDefinition stepDefinition = mapper.readValue(new File(stepsDefinitionPath), StepDefinition.class);
                List<StepDef> stepDefs = stepDefinition.getSteps();

                // Order steps if needed
                if (stepDefinition.isOrdered()) {
                    // Sort in ASCENDING order
                    Collections.sort(stepDefs);
                }

                // First step will always be the same:
                steps.add(new InterpretCAI("SETMOJB:CREATESUBSCRIBER:MSISDN,8299876565"));

                for (StepDef stepDef: stepDefinition.getSteps()) {

                    if (BaseStep.EMAIL_STEP.contains(stepDef.getStepName())) {

                        steps.add(new SendEmail(programInfo));

                    } else if (BaseStep.HELLO_STEP.contains(stepDef.getStepName())) {

                        steps.add(new SayHello(programInfo));

                    }
                }

                // All good
                state = WorkflowState.READY;

            } catch (Exception e) {
                e.printStackTrace();
                state = WorkflowState.FAILED;
            }
        } else {
            throw new Exception("State mismatch error");
        }
    }

    // TODO: Create custom exception model
    public void execute() throws Exception {
        if (WorkflowState.READY == state) {

            state = WorkflowState.RUNNING;

            this.failedSteps = new ArrayList<>();

            // For each step, call its execute method  :)
            for (BaseStep step: steps) {
                try {
                    step.execute();
                } catch (ExecutionStepException e) {
                    this.failedSteps.add(step);
                }
            }

            if (failedSteps.size() > 0) {
                state = WorkflowState.FAILED;
            } else {
                state = WorkflowState.DONE;
            }

        } else {
            throw new Exception("Run build before continue. State mismatch exception");
        }

    }

    // TODO: Create custom exception model
    public String getResult() throws Exception {
        if (state == WorkflowState.DONE) {
            return "Workflow processed all steps successfully: " + steps.size() + "/" + steps.size();
        } else if (state == WorkflowState.FAILED) {
            return "Workflow finished with errors: " + failedSteps.size() + " out of " + steps.size() + " failed.";
        } else {
            throw new Exception("Status mismatch. To get results first run execute method.");
        }
    }

}
