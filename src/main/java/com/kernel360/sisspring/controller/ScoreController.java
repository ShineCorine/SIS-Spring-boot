package com.kernel360.sisspring.controller;


import com.kernel360.sisspring.db.Score;
import com.kernel360.sisspring.db.Student;
import com.kernel360.sisspring.db.Subject;
import com.kernel360.sisspring.model.UpdateScoreDto;
import com.kernel360.sisspring.service.ScoreService;
import com.kernel360.sisspring.service.StudentService;
import com.kernel360.sisspring.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/score")
@RequiredArgsConstructor
public class ScoreController {

    private final SubjectService subjectService;
    private final StudentService studentService;
    private final ScoreService scoreService;

    @GetMapping
    public String selectSubject(Model model
    ){
        List<Subject> subjects = subjectService.getAllClasses();
        model.addAttribute("subjects", subjects);
        return "input-score";
    }
    @PostMapping("/select")
    public String inputScore(
            @RequestParam("subjectId") Long subjectId,
            Model model
    ){
        Subject selectedsubject = subjectService.getSubjjectByName(subjectId);
        model.addAttribute("selectedSubject", selectedsubject);
        List<Student> students = subjectService.enRolledStudent(subjectId);
        model.addAttribute("enrolledStudents", students);
        List<Score> scores = scoreService.getListBySubjectId(subjectId);
        UpdateScoreDto updateScoreDto = new UpdateScoreDto(scores);
        model.addAttribute("scores", updateScoreDto);
//        model.addAttribute("")

        return "input-score";
    }
    @PostMapping("/process")
    public String submitScores(
            @RequestParam Long subjectId,
            UpdateScoreDto updateScoreDto,
            Model model
    ) {

        scoreService.updateScores(updateScoreDto.getScoreList());

        return "redirect:/score";  // 성적 제출 후 다시 성적 입력 페이지로 이동
    }


}
