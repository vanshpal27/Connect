package com.project.connect.uploads_service.controller;


import com.project.connect.uploads_service.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
public class UploadController {

    @Autowired
    private  UploadService uploadService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> upload(@RequestPart(name = "image") MultipartFile multipartFile)
    {
        return  ResponseEntity.ok(uploadService.upload(multipartFile));
    }
}
