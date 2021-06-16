package pl.akademia.api.exceptions;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ExceptionBody {
  private String message;
  private String timestamp;
  private String path;
  private int status;
}
