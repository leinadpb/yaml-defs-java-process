package workflow;

public class Main {

    public static void main(String[] args) {
        ProgramInfo info = new ProgramInfo();

        Workflow workflow = new Workflow(info);

        System.out.println(workflow.state);

        try {
            workflow.build("src/main/java/workflow/steps.yaml");

            System.out.println(workflow.state);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
