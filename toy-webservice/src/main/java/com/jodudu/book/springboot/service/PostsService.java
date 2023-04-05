package com.jodudu.book.springboot.service;

import com.jodudu.book.springboot.domain.posts.Posts;
import com.jodudu.book.springboot.domain.posts.PostsRepository;
import com.jodudu.book.springboot.web.dto.PostsListResponseDto;
import com.jodudu.book.springboot.web.dto.PostsResponseDto;
import com.jodudu.book.springboot.web.dto.PostsSaveRequestDto;
import com.jodudu.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = findAndValidateById(id);
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = findAndValidateById(id);
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = findAndValidateById(id);
        postsRepository.delete(posts);
    }

    private Posts findAndValidateById(Long id) {
        return postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
    }
}
