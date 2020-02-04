package workflow.models;

import workflow.BaseStep;

import java.util.List;

public class StepDefinition {
    private boolean ordered;
    private String user;
    private List<StepDef> steps;


    public boolean isOrdered() {
        return ordered;
    }

    public void setOrdered(boolean ordered) {
        this.ordered = ordered;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<StepDef> getSteps() {
        return steps;
    }

    public void setSteps(List<StepDef> steps) {
        this.steps = steps;
    }
}
