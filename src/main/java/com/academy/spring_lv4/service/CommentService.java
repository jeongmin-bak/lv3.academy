package com.academy.spring_lv4.service;

import com.academy.spring_lv4.dto.comment.CommentRequestDto;
import com.academy.spring_lv4.entity.Comment;
import com.academy.spring_lv4.entity.Lecture;
import com.academy.spring_lv4.entity.User;
import com.academy.spring_lv4.repository.CommentRepository;
import com.academy.spring_lv4.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j(topic="comment service")
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final LectureRepository lectureRepository;

    public ResponseEntity registerComment(Long lectureId, CommentRequestDto requestDto, User user) {
        Lecture lecture = lectureRepository.findLectureByLectureId(lectureId).orElseThrow(
                () -> new IllegalArgumentException("해당 강의가 없습니다.")
        );

        Comment comment = new Comment(requestDto, user);
        comment.setLecture(lecture);
        commentRepository.save(comment);

        return ResponseEntity.status(HttpStatus.OK).body("댓글이 등록되었습니다.");
    }

    @Transactional
    public ResponseEntity editComment(Long lectureId, Long commentId, CommentRequestDto requestDto, User user) {
        Comment findcomment = commentRepository.findUserComment(lectureId, commentId, user.getId()).orElseThrow(
                () -> new IllegalArgumentException("수정가능한 댓글이 없습니다.")
        );
        findcomment.update(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body("댓글이 수정되었습니다.");
    }
}
