package com.project.connect.posts_service.Controller;

import com.project.connect.posts_service.ContextHandler.UserContextHandler;
import com.project.connect.posts_service.Dto.PostDto;
import com.project.connect.posts_service.Dto.PostRequestDto;
import com.project.connect.posts_service.OpenFeignClients.UploadClient;
import com.project.connect.posts_service.Service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UploadClient uploadClient;

    @GetMapping("/ready")
    public  String getMapping(HttpServletRequest httpServletRequest)
    {

        String id = httpServletRequest.getHeader("X-User-Id");
       Long userId = UserContextHandler.getCurrentUserId();
        System.out.println(userId);
        return  "hey working" + id;
    }

    @PostMapping
    public ResponseEntity<PostDto> create(@RequestBody PostRequestDto postRequestDto)
    {
        Long userId = UserContextHandler.getCurrentUserId();

        return new ResponseEntity<>(postService.create(postRequestDto,userId), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<PostDto> getById(@PathVariable Long id)
    {
        return  new ResponseEntity<>(postService.getPostById(id),HttpStatus.OK);
    }

    @GetMapping("/getAllPost/{userId}")
    public  ResponseEntity<List<PostDto>> getAllPost(@PathVariable Long userId)
    {
        return  new ResponseEntity<>(postService.getAllPost(userId),HttpStatus.OK);
    }
    @PostMapping("/image")
    public ResponseEntity<PostDto> createImage(@RequestParam(name = "image") MultipartFile multipartFile)
    {
        Long userId = UserContextHandler.getCurrentUserId();
        String url  = uploadClient.upload(multipartFile);
        PostRequestDto postRequestDto = PostRequestDto.builder().content(url).build();
        return new ResponseEntity<>(postService.create(postRequestDto,userId),HttpStatus.CREATED);
    }
}
