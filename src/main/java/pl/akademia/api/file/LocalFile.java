package pl.akademia.api.file;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocalFile {
  private String name;
  private String creationTime;
  private String downloadUri;
  private String fileType;
}