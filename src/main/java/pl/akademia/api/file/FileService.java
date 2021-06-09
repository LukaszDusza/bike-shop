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

  public String uploadFile(MultipartFile file) throws IOException {
    Path path = Paths.get(directory + file.getOriginalFilename());
    Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
    return file.getName();
  }

  public String getWebLink(String fileName) {
    return ServletUriComponentsBuilder
        .fromCurrentContextPath()
        .path("/api/v1/files/")
        .path(fileName)
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