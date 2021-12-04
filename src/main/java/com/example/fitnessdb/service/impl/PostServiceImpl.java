package com.example.fitnessdb.service.impl;

import com.example.fitnessdb.model.dto.PostDetailsDto;
import com.example.fitnessdb.model.dto.PostDetailsViewDto;
import com.example.fitnessdb.model.dto.UserCredentialsDto;
import com.example.fitnessdb.model.entity.PostEntity;
import com.example.fitnessdb.model.entity.UserEntity;
import com.example.fitnessdb.model.entity.WorkoutEntity;
import com.example.fitnessdb.repo.PostRepo;
import com.example.fitnessdb.repo.UserRepo;
import com.example.fitnessdb.repo.WorkoutRepo;
import com.example.fitnessdb.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final ModelMapper modelMapper;
    private final PostRepo postRepo;
    private final UserRepo userRepo;
    private final WorkoutRepo workoutRepo;

    public PostServiceImpl(ModelMapper modelMapper, PostRepo postRepo,
                           UserRepo userRepo, WorkoutRepo workoutRepo) {
        this.modelMapper = modelMapper;
        this.postRepo = postRepo;
        this.userRepo = userRepo;
        this.workoutRepo = workoutRepo;
    }


    @Override
    public void savePost(PostDetailsDto postDetailsDto) {
        PostEntity postEntity = this.modelMapper.map(postDetailsDto, PostEntity.class);
        UserEntity userEntity = this.userRepo
                .findByUsername(postDetailsDto.getUserCredentialsDto().getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found."));
        postEntity.setAuthor(userEntity);

        WorkoutEntity workoutEntity = this.workoutRepo
                .findById(postDetailsDto.getWorkoutsWithDetailsDto().id)
                .orElseThrow(IllegalArgumentException::new);

        List<PostEntity> reviews = new ArrayList<>();
        reviews.add(postEntity);
        workoutEntity.setReviews(reviews);
        postEntity.setWorkout(workoutEntity);
postEntity.setDate(LocalDateTime.now());
        this.postRepo.save(postEntity);

    }

    @Override
    public List<PostDetailsViewDto> findAllPostsForCurrWorkout(Long id) {
        List<PostDetailsViewDto> collect = this.postRepo.findAllByWorkoutId(id)
                .stream()
                .map(currPostEntity -> {
                    PostDetailsViewDto postDetailsViewDto = this.modelMapper.map(currPostEntity, PostDetailsViewDto.class);
                    postDetailsViewDto
                            .setUserCredentialsDto(this.modelMapper
                                    .map(currPostEntity.getAuthor(), UserCredentialsDto.class));
                    return postDetailsViewDto;
                })
                .collect(Collectors.toList());

        System.out.println();
        return collect;

    }

    @Override
    public List<PostDetailsViewDto> lastFivePostsForCurrWorkout(Long id) {
        return this.postRepo.findLastPostsByWorkoutId(id)
                .stream()
                .map(currPostEntity -> {
                    PostDetailsViewDto postDetailsViewDto = this.modelMapper.map(currPostEntity, PostDetailsViewDto.class);
                    postDetailsViewDto
                            .setUserCredentialsDto(this.modelMapper
                                    .map(currPostEntity.getAuthor(), UserCredentialsDto.class));
                    return postDetailsViewDto;
                })
                .limit(5)
                .collect(Collectors.toList());
    }

    @Override
    public void createPost() {
        PostEntity postEntity = new PostEntity();
        postEntity.setMessage("Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                        "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                        " Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip" +
                        " ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate " +
                        "velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident," +
                        " sunt in culpa qui officia deserunt mollit anim id est laborum.")
                .setDate(LocalDateTime.now())
                .setWorkout(this.workoutRepo.getById(1L))
                .setAuthor(this.userRepo.getById(1L))
                .setStars(3.5F);
        this.postRepo.save(postEntity);
    }
}
