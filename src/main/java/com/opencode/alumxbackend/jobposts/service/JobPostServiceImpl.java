package com.opencode.alumxbackend.jobposts.service;

import com.opencode.alumxbackend.jobposts.dto.JobPostRequest;
import com.opencode.alumxbackend.jobposts.model.JobPost;
import com.opencode.alumxbackend.jobposts.repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.opencode.alumxbackend.users.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.UUID;

// different from interface as here we are going to implement what we need
@Service
@RequiredArgsConstructor
public class JobPostServiceImpl implements JobPostService{
    private final JobPostRepository jobPostRepository;
    private final UserRepository userRepository;

    @Override
    public JobPost createJobPost(JobPostRequest request) {



        if(!userRepository.existsByUsername(request.getUsername())){
            throw new IllegalArgumentException("Username does not exist: " + request.getUsername());
        }
        
        JobPost jobPost = JobPost.builder()
                .postId(UUID.randomUUID().toString())
                .username(request.getUsername())
                .description(request.getDescription())
                .imageUrls(request.getImageUrls())
                .createdAt(LocalDateTime.now())
                .build();


        return jobPostRepository.save(jobPost);
    }
}
