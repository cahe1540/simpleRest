package dev.carlos.simpleRest.service;

import dev.carlos.simpleRest.repository.dao.dto.EmailDataDAO;
import dev.carlos.simpleRest.repository.dao.dto.HelloDTO;
import java.io.IOException;
import java.util.List;

public interface HelloService {
  HelloDTO getHello();

  String postHello(List<EmailDataDAO> name);
}
