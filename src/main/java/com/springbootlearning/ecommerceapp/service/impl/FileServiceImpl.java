package com.springbootlearning.ecommerceapp.service.impl;

import com.springbootlearning.ecommerceapp.exceptions.APIException;
import com.springbootlearning.ecommerceapp.service.FileService;
import com.springbootlearning.ecommerceapp.service.validators.FileServiceValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileServiceImpl implements FileService {

    private final FileServiceValidator fileServiceValidator;

    @Value("${project.files.maxsize}")
    private long maxImageSize;


    @Override
    public String uploadImage(String path, MultipartFile file) {
        // validate image is valid or not
        fileServiceValidator.validateFileSize(file, maxImageSize);
        fileServiceValidator.validateIfImageIsValid(file);

        // get file Name
        String originalFileName = file.getOriginalFilename();

        // generate a unique file name
        String randomId = UUID.randomUUID().toString();
        String fileName = randomId.concat(originalFileName.substring(originalFileName.lastIndexOf(".")));

        String filePath = path + File.separator + fileName;

        // check if path exists and create
        File folder = new File(path);
        if(!folder.exists()){
            try {
                Files.createDirectories(Paths.get(path));
            } catch (IOException e){
                log.error("Error occurred while creating folder : {}", e);
                throw new APIException(e.getMessage());
            }
        }

        // Upload image to server
        try {
            Files.copy(file.getInputStream(), Paths.get(filePath));
        } catch (IOException e){
            log.error("Error occurred : {}", e);
            throw new APIException(e.getMessage());
        }
        return fileName;
    }

    @Override
    public void deleteImage(String path, String fileName) {
        try {
            String filePath = path + File.separator + fileName;
            File file = new File(filePath);

            if (file.exists()) {
                if (file.delete()) {
                    log.info("Image file deleted successfully: {}", fileName);
                } else {
                    log.warn("Failed to delete image file: {}", fileName);
                    throw new APIException("Failed to delete image file: " + fileName);
                }
            } else {
                log.warn("Image file not found: {}", fileName);
            }
        } catch (Exception e) {
            log.error("Error occurred while deleting image: {}", e);
            throw new APIException("Error occurred while deleting image: " + e.getMessage());
        }
    }
}
