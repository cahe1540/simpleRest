package dev.carlos.simpleRest.utils.ses;

import java.lang.String;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class EmailHelper {

  private static final String EMAIL_TEMPLATE_PATH= "src/main/resources/static/email.html";

  public static String getHTMLFile(Charset encoding) throws IOException{
    String email = null;
      email = Files.lines(Paths.get(EMAIL_TEMPLATE_PATH))
        .collect(Collectors.joining(System.lineSeparator()));
      return email;
  }

  public static String prepareBody(String html, String param) {
    html = html.replace("<PARAM>", param);
    return html;
  }
}