package workflow.steps;

import workflow.BaseStep;
import workflow.ProgramInfo;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmail extends BaseStep {

    ProgramInfo programInfo;
    MimeMessage simpleMessage;

    public SendEmail(ProgramInfo programInfo) {

        super(BaseStep.EMAIL_STEP);

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");

        Session mailSession = Session.getDefaultInstance(props);
        simpleMessage = new MimeMessage(mailSession);

        programInfo = programInfo;
    }

    @Override
    public void execute() {
        try {
//            Transport.send(simpleMessage);
            System.out.println("Message was sent! to address: " + programInfo.getToMail());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
