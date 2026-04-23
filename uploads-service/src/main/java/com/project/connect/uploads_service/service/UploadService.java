package com.project.connect.uploads_service.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    public  String upload(MultipartFile multipartFile);
}
