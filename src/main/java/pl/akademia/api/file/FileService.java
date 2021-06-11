package pl.akademia.api.file;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

@Service
public class FileService {

  // @Value("${files.directory}")
  private String directory = "C:\\files\\";

  public FileService() throws IOException {
    createDirectory();
  }

  private void createDirectory() throws IOException {
    Path path = Paths.get(directory);
    if(Files.notExists(path)) {
      Files.createDirectory(path);
    }
  }

  public LocalFile uploadFile(MultipartFile file) throws IOException {
    Path path = Paths.get(directory + file.getOriginalFilename());
    Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
    return LocalFile
        .builder()
        .name(file.getOriginalFilename())
        .downloadUri(getWebLink(file.getOriginalFilename()))
        .fileType(Files.probeContentType(path))
        .creationTime(Files.readAttributes(path, BasicFileAttributes.class).creationTime().toString())
        .build();
  }

  public String getWebLink(String fileName) {
    return ServletUriComponentsBuilder
        .fromCurrentContextPath()
        .path("/api/v1/files/")
        .path(fileName)
        .path("/image")
        .toUriString();
  }

  public Resource getFile(String file) {
    Path path = Paths.get(directory + file);
    try {
      return new UrlResource(path.toUri());
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    return null;
  }
}