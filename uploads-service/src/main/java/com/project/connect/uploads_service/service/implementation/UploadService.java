package com.project.connect.uploads_service.service.implementation;

import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UploadService implements com.project.connect.uploads_service.service.UploadService {

    @Autowired
    private Cloudinary cloudinary;
    @Override
    public String upload(MultipartFile multipartFile) {
        try {
            Map map = cloudinary.uploader().upload(multipartFile.getBytes(),Map.of());
            return  map.get("secure_url").toString();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
