package pl.akademia.api.file;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/v1")
public class FileController {

  private final FileService fileService;

  public FileController(FileService fileService) {
    this.fileService = fileService;
  }

  @GetMapping("/files/{file}")
  public ResponseEntity<?> getFile(@PathVariable String file) throws IOException {
    Resource resource = fileService.getFile(file);
    File targetFile = new File(resource.getFile().getAbsolutePath());
    String contentType = Files.probeContentType(Paths.get(resource.getURI()));
    return ResponseEntity
        .ok()
        .contentType(MediaType.parseMediaType(contentType))
        .header(HttpHeaders.CONTENT_DISPOSITION
         //   ,
        //    "attachment; filename=\"" + targetFile.getName() + "\""
        )
        .contentLength(targetFile.length())
        .body(resource);
  }

}
