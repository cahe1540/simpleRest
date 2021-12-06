package dev.carlos.simpleRest.service.impl;

import com.amazonaws.regions.Regions;
import dev.carlos.simpleRest.repository.dao.HelloDAO;
import dev.carlos.simpleRest.repository.dao.dto.EmailDataDAO;
import dev.carlos.simpleRest.repository.dao.dto.HelloDTO;
import dev.carlos.simpleRest.service.HelloService;
import dev.carlos.simpleRest.utils.ses.EmailHelper;
import dev.carlos.simpleRest.utils.ses.EmailSender;
import dev.carlos.simpleRest.utils.ses.dto.EmailDTO;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import javax.inject.Inject;
import lombok.Value;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class HelloServiceImpl implements HelloService {

  @Inject
  private final HelloDAO helloDao;

  private EmailDTO emailDTO;

  public HelloServiceImpl(HelloDAO helloDao, EmailDTO emailDTO) {
    this.helloDao = helloDao;
    this.emailDTO = emailDTO;
  }

  @Override
  public HelloDTO getHello() {
    return helloDao.getHello();
  }

  @Override
  public String postHello(List<EmailDataDAO> contacts) {
    Thread emailAsync = new Thread(() -> {
      try {
        for(EmailDataDAO data: contacts) {
          String emailMarkup = EmailHelper.getHTMLFile(Charset.defaultCharset());
          emailMarkup = EmailHelper.prepareBody(emailMarkup, data.getName());

          this.emailDTO.setTo(data.getEmail());
          this.emailDTO.setAwsregion(Regions.US_WEST_1);
          this.emailDTO.setHtmlBody(emailMarkup);

          log.info("Sending this.emailDTO" + this.emailDTO);

          EmailSender.sendEmail(this.emailDTO);
        }
      } catch (IOException exception){
        log.error(exception);
      }
      });
    emailAsync.run();
    return new String("Email Sent!");
 }
}
