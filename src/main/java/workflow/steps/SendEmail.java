package workflow.steps;

import workflow.BaseStep;
import workflow.ProgramInfo;
import workflow.exception.ExecutionStepException;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmail extends BaseStep {

    MimeMessage simpleMessage;

    public SendEmail(ProgramInfo info) {
        super(BaseStep.EMAIL_STEP, info);

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");

        Session mailSession = Session.getDefaultInstance(props);
        simpleMessage = new MimeMessage(mailSession);
    }

    @Override
    public void execute() throws ExecutionStepException {
        try {
            Transport.send(simpleMessage);
//            System.out.println("Message was sent! to address: " + getInfo().getToMail());
        } catch (Exception e) {
            throw new ExecutionStepException("Error sending msg: " + e.getMessage());
        }
    }

}
