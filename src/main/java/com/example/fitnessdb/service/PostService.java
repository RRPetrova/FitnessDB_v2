package com.example.fitnessdb.service;

import com.example.fitnessdb.model.dto.PostDetailsDto;
import com.example.fitnessdb.model.dto.PostDetailsViewDto;

import java.util.List;

public interface PostService {
    void savePost(PostDetailsDto postDetailsDto);

    List<PostDetailsViewDto> findAllPostsForCurrWorkout(Long id);
    List<PostDetailsViewDto> lastFivePostsForCurrWorkout(Long id);

    void createPost();

}
