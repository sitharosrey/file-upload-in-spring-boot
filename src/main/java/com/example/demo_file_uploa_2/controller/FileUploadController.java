package com.example.demo_file_uploa_2.controller;

import com.example.demo_file_uploa_2.service.FileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/file")
public class FileUploadController {
    private final FileService fileService;

    public FileUploadController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(value = "/file-upload", consumes = {"multipart/form-data"})
    public String uploadFileToServer(
            @RequestParam("file") MultipartFile file
    ) {

        System.out.println(file);
        try {
            String fileUpload = fileService.saveFile(file);
            System.out.println("this is file : " + fileUpload);

            return fileUpload;
        } catch (Exception e) {
            System.out.println("error message : " + e.getMessage());
        }
        return null;
    }
}
