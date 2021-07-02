package pl.akademia.api.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileService {

  private final static Logger logger = LoggerFactory.getLogger(FileService.class);

  // @Value("${files.directory}")
  private String directory = "C:\\files\\";

  public FileService() throws IOException {
    createDirectory();
  }

  private void createDirectory() throws IOException {
    Path path = Paths.get(directory);
    if(Files.notExists(path)) {
      logger.info("Create directory in path: {}", directory);
      Files.createDirectory(path);
    } else {
      logger.info("Directory for files exists: {}", directory);
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
    String link = ServletUriComponentsBuilder
        .fromCurrentContextPath()
        .path("/api/v1/files/")
        .path(fileName)
        .path("/image")
        .toUriString();
    logger.info("Generated weblink for file {} : {}", fileName, link );
    return link;
  }

  //todo zrobic metodę listującą wszystkie pliki w folderze zamieniając nazwy plików w List<String> na weblinki.

  public List<String> getAllFiles() throws IOException {
    Stream<Path> files = Files.walk(Paths.get(directory)).filter(Files::isRegularFile);
    return files.map(f -> getWebLink(f.getFileName().toString())).collect(Collectors.toList());
  }

  public Resource getFile(String file) {
    Path path = Paths.get(directory + file);
    try {
      logger.info("find resource: {}", path.toString());
      return new UrlResource(path.toUri());
    } catch (MalformedURLException e) {
      logger.error("Exception: {}", e.getMessage());
    }
    logger.error("Cannot find resource for file: {}", file);
    return null;
  }
}