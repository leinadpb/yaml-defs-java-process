package workflow.models;


public class StepDef implements Comparable<StepDef> {
    private String stepName;
    private Integer order;



    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public int compareTo(StepDef o) {
        return this.getOrder().compareTo(o.getOrder());
    }

    @Override
    public String toString() {
        return "StepDef{" +
                "stepName='" + stepName + '\'' +
                ", order=" + order +
                '}';
    }
}
