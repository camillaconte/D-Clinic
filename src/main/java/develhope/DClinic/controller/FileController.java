package develhope.DClinic.controller;

import develhope.DClinic.service.FileStorageService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    private Logger logger = LoggerFactory.getLogger(FileStorageService.class);

    @Autowired
    private FileStorageService fileStorageService;

    //potrei anche scrivere MultipartFile file[] per upload multiplo
    //il programma gestirà un Array di file
    @PostMapping("/upload")
    public String upload(@RequestBody MultipartFile file){
        try {
            return fileStorageService.upload(file);
        } catch (IOException e) {
            logger.warn("It was not possible to upload the file");
            e.getMessage();
            return "It was not possible to upload the file";
        }
    }

    @PostMapping("/upload-multiple")
    public List<String> uploadMultiple(@RequestBody MultipartFile[] files) throws IOException {
        List<String> fileNames = new ArrayList<>();
        for (MultipartFile file: files){
            String singleUploadedFileName = fileStorageService.upload(file);
            fileNames.add(singleUploadedFileName);
        }
        return fileNames;
    }

    //interessante il @responseBody byte[]
    @GetMapping("/download")
    public @ResponseBody byte[] download(@RequestParam String fileName, HttpServletResponse response) throws IOException {
        System.out.println("Downloading " + fileName);
        //cosa da fare nel Controller: dire qual'è l'ESTENSIONE
        String extension = FilenameUtils.getExtension(fileName);
        switch (extension){
            case "gif":
                response.setContentType(MediaType.IMAGE_GIF_VALUE);
                break;
            case "jpg":
            case "jpeg":
                response.setContentType(MediaType.IMAGE_JPEG_VALUE);
                break;
            case "png":
                response.setContentType(MediaType.IMAGE_PNG_VALUE);
                break;
        }
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"" );
        return fileStorageService.download(fileName);
    }

    /**
     * TODO do we need a different method for PDF files???
     */
    @GetMapping("/download/PDF")
    public @ResponseBody byte[] downloadPDF(@RequestParam String fileName, HttpServletResponse response) throws IOException {
        System.out.println("Downloading " + fileName);
        //cosa da fare nel Controller: dire qual'è l'ESTENSIONE
        //TODO verify APPLICATION_PDF
        response.setContentType(String.valueOf(MediaType.APPLICATION_PDF));
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"" );
        return fileStorageService.download(fileName);
    }

    //TODO creare metodo statico setExtension e usarlo bene
    /*public static void setExtension(String fileName, HttpServletResponse response) throws IOException {
        //cosa da fare nel Controller: dire qual'è l'ESTENSIONE
        String extension = FilenameUtils.getExtension(fileName);
        switch (extension) {
            case "gif":
                response.setContentType(MediaType.IMAGE_GIF_VALUE);
                break;
            case "jpg":
            case "jpeg":
                response.setContentType(MediaType.IMAGE_JPEG_VALUE);
                break;
            case "png":
                response.setContentType(MediaType.IMAGE_PNG_VALUE);
                break;
        }
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"" );
    }*/

}
