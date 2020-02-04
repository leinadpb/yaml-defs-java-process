package workflow;

import workflow.exception.ExecutionStepException;

public abstract class BaseStep {

    public static final String INTERPRETER_STEP = "interpreter-step";
    public static final String EMAIL_STEP = "email-step";
    public static final String HELLO_STEP = "hello-step";

    private String uniqueId;
    private int order;
    private String stepName;
    private ProgramInfo info;

    public BaseStep(String uniqueId) {
        uniqueId = uniqueId;
    }

    public BaseStep(String uniqueId, ProgramInfo info) {
        this.uniqueId = uniqueId;
        this.info = info;
    }

    public abstract void execute() throws ExecutionStepException;


    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public ProgramInfo getInfo() {
        return info;
    }

    public void setInfo(ProgramInfo info) {
        this.info = info;
    }
}
