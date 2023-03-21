package com.example.demo_file_uploa_2.serviceImp;

import com.example.demo_file_uploa_2.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileServiceImp implements FileService {

    Path path;

    public FileServiceImp() {
        path = Paths.get("src/main/resources/images");
    }

    @Override
    public String saveFile(MultipartFile file) throws IOException {

        String fileName = file.getOriginalFilename();
        UUID uuid = UUID.randomUUID();
        fileName = uuid + fileName;

        Path resolvePath = path;
        if (!file.isEmpty()) {
            resolvePath = path.resolve(fileName);
        }
        Files.copy(file.getInputStream(), resolvePath, StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }
}
