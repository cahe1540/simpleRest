package dev.carlos.simpleRest.utils.ses.dto;

import com.amazonaws.regions.Regions;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Setter
@Getter
public class EmailDTO {

  @Value("${email.ses.from}")
  private String from;

  private String to;

  private Regions awsregion;

  @Value("${email.ses.subject.noStateFunding}")
  private String subject;

  private String htmlBody;

  @Value("${email.ses.textBody.noStateFunding}")
  private String textBody;
}
