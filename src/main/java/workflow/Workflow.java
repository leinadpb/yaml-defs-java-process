package workflow;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import workflow.models.StepDef;
import workflow.models.StepDefinition;
import workflow.models.WorkflowState;
import workflow.steps.InterpretCAI;
import workflow.steps.SayHello;
import workflow.steps.SendEmail;

import java.io.File;
import java.util.*;

public class Workflow {

    List<BaseStep> steps;
    ObjectMapper mapper;
    WorkflowState state;
    ProgramInfo info;

    public Workflow(ProgramInfo info) {
        steps = new ArrayList<BaseStep>();
        mapper = new ObjectMapper(new YAMLFactory());
        state = WorkflowState.INIT;
        info = info;
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

                        steps.add(new SendEmail(info));

                    } else if (BaseStep.HELLO_STEP.contains(stepDef.getStepName())) {

                        steps.add(new SayHello(info));

                    }
                }

                // All good
                state = WorkflowState.RUNNING;

            } catch (Exception e) {
                e.printStackTrace();
                state = WorkflowState.FAILED;
            }
        } else {
            throw new Exception("State mismatch error");
        }
    }

}
