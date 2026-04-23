package com.project.connect.posts_service.Service.Imple;

import com.project.connect.posts_service.ContextHandler.UserContextHandler;
import com.project.connect.posts_service.Dto.PersonDto;
import com.project.connect.posts_service.Dto.PostDto;
import com.project.connect.posts_service.Dto.PostRequestDto;
import com.project.connect.posts_service.Entity.Post;
import com.project.connect.posts_service.Event.PostEvent;
import com.project.connect.posts_service.Exception.ResourceNotFoundException;
import com.project.connect.posts_service.OpenFeignClients.ConnectionClient;
import com.project.connect.posts_service.Repository.PostRepo;
import com.project.connect.posts_service.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImple implements PostService {

    private  final PostRepo postRepo;
    private  final ModelMapper modelMapper;
    private  final ConnectionClient connectionClient;

    private  final KafkaTemplate<Long, PostEvent> kafkaTemplate;
    @Override
    public PostDto create(PostRequestDto postRequestDto,Long userId) {

        Post post = Post.builder().userId(userId).content(postRequestDto.getContent()).build();
        post = postRepo.save(post);
        PostEvent postEvent = modelMapper.map(post,PostEvent.class);
        kafkaTemplate.send("post-create-topic",postEvent);
        return  modelMapper.map(post,PostDto.class);
    }

    @Override
    public PostDto getPostById(Long postId) {

        List<PersonDto> personDto = connectionClient.getAllFirstDegreeConnection();
        Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException());
        return  modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> getAllPost(Long userId) {
       List<Post> posts = postRepo.findByUserId(userId);
       List<PostDto> postDtoList = posts
               .stream()
               .map(post -> modelMapper.map(post, PostDto.class))
               .collect(Collectors.toList());
       return  postDtoList;
    }
}
