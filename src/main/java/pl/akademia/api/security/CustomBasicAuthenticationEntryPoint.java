package pl.akademia.api.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import pl.akademia.api.exceptions.ExceptionBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class CustomBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.setHeader("Content-Type", "application/json");
    response.setHeader("WWW-Authenticate", "Basic realm=" + getRealmName());
    PrintWriter writer = response.getWriter();
    writer.println(ExceptionBody.
        builder()
        .timestamp(new Date().toString())
        .message("HTTP status 401")
        .path(request.getRequestURI())
        .status(HttpServletResponse.SC_UNAUTHORIZED)
        .build());
  }

  @Override
  public void afterPropertiesSet() {
    setRealmName("Bike shop realm");
    super.afterPropertiesSet();
  }
}
