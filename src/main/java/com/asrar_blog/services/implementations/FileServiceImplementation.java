package com.asrar_blog.services.implementations;

import com.asrar_blog.services.FileService;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

public class FileServiceImplementation implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();
        String randomId = UUID.randomUUID().toString();
        String fileName = randomId.concat(name.substring(name.lastIndexOf(".")));
        String filePath = path + File.separator + fileName;

        File f = new File(path);
        if(!f.exists())
            f.mkdir();
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return fileName;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path +File.separator +fileName;
        InputStream is = new FileInputStream(fullPath);
        return is;
    }
}
