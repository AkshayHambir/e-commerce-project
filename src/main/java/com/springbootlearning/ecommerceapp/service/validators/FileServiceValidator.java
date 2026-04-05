package com.springbootlearning.ecommerceapp.service.validators;

import com.springbootlearning.ecommerceapp.exceptions.APIException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class FileServiceValidator {

    public void validateIfImageIsValid(MultipartFile file){
        if(Objects.isNull(file) || Objects.isNull(file.getOriginalFilename())){
            throw new APIException("Invalid file");
        }
        String fileName = file.getOriginalFilename();

        String extension = fileName.split("\\.")[1].toLowerCase();
        if(file.isEmpty() || !(extension.equals("png") || extension.equals("jpg") || extension.equals("jpeg"))){
            throw new APIException("Not a valid image");
        }
    }
}
