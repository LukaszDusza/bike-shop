package pl.akademia.api.file;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class FileFrontController {
    private FileService fileService;

    public FileFrontController(FileService fileService) {

        this.fileService = fileService;
    }


@PostMapping("/files")
public String uploadFile(@RequestParam("file") MultipartFile file,
                         RedirectAttributes redirectAttributes) throws IOException {
       if (file.isEmpty()){
           redirectAttributes.addFlashAttribute("message","Select a file to upload");
       }
        fileService.uploadFile(file);
    redirectAttributes.addFlashAttribute("message","File is successfully uploaded"+file.getOriginalFilename());
    return "upload";
}
}
