package org.example;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RestController
public class FileController {

    private final String uploadDir = "uploads";
    private final Map<Integer, String> fileStorage = new HashMap<>();
    private Integer currentId = 0;

    public FileController() {
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    @PostMapping("/upload")
    public Long uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
//        try {
//            client0.uploadFile(file);
//        } catch (Exception ignored){}
//        try {
//            client1.uploadFile(file);
//        } catch (Exception ignored){}
//        try {
//            client2.uploadFile(file);
//        } catch (Exception ignored){}
        return repository.save(FileEntity.builder().bytes(file.getBytes()).build()).getId();
    }

    @GetMapping("/{id}")
    public Resource downloadFile(@PathVariable("id") Integer id) throws MalformedURLException, FileNotFoundException {
        String filePath = fileStorage.get(id);
        if (filePath == null) {
            throw new FileNotFoundException("File not found with ID: " + id);
        }
        return new UrlResource(Paths.get(filePath).toUri());
    }
}
