package com.academy.spring_lv4.controller;

import com.academy.spring_lv4.dto.lecture.LectureRequestDto;
import com.academy.spring_lv4.dto.lecture.LectureTeacherResponseDto;
import com.academy.spring_lv4.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lectures")
public class LectureController {

    private final LectureService lectureService;

    @PostMapping("/admin")
    public LectureTeacherResponseDto createLecture(@RequestBody LectureRequestDto requestDto) {
        return lectureService.createLecture(requestDto);
    }

    @GetMapping("/{id}")
    public LectureTeacherResponseDto findLecture(@PathVariable Long id) {
        return lectureService.getLecture(id);
    }

}
