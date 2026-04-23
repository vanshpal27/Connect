package com.project.connect.posts_service.OpenFeignClients;



import com.project.connect.posts_service.Configuration.ProjectConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "connect-upload",path = "/uploads",configuration = ProjectConfig.class)
public interface UploadClient {
    @PostMapping(value = "/core", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String upload(@RequestPart("image") MultipartFile multipartFile);
}
