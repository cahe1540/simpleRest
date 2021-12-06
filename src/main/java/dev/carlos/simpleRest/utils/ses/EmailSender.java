package dev.carlos.simpleRest.utils.ses;

import dev.carlos.simpleRest.utils.ses.dto.EmailDTO;
import java.io.IOException;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class EmailSender {
  public static void sendEmail(EmailDTO email) throws IOException {
    try {
      AmazonSimpleEmailService client =
          AmazonSimpleEmailServiceClientBuilder.standard()
              .withRegion(email.getAwsregion()).build();
      SendEmailRequest request = new SendEmailRequest()
          .withDestination(
              new Destination().withToAddresses(email.getTo()))
          .withMessage(new Message()
              .withBody(new Body()
                  .withHtml(new Content()
                      .withCharset("UTF-8").withData(email.getHtmlBody()))
                  .withText(new Content()
                      .withCharset("UTF-8").withData(email.getTextBody())))
              .withSubject(new Content()
                  .withCharset("UTF-8").withData(email.getSubject())))
          .withSource(email.getFrom());
      client.sendEmail(request);
      log.info("Sending email request...");
    } catch (Exception ex) {
      log.error("The email was not sent. Error message: "
          + ex.getMessage());
    }
  }
}
