package dev.carlos.simpleRest.repository.dao.impl;

import dev.carlos.simpleRest.repository.dao.HelloDAO;
import dev.carlos.simpleRest.repository.dao.dto.HelloDTO;
import org.springframework.stereotype.Repository;

@Repository
public class HelloDAOImpl implements HelloDAO {

  @Override
  public HelloDTO getHello() {
    return new HelloDTO(1, "Hello world!", "USA");
  }
}
