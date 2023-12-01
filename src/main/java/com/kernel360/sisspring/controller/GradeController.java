package com.kernel360.sisspring.controller;



import com.kernel360.sisspring.db.Score;
import com.kernel360.sisspring.db.Subject;
import com.kernel360.sisspring.model.GradeDto;
import com.kernel360.sisspring.service.GradeService;
import com.kernel360.sisspring.service.ScoreService;
import com.kernel360.sisspring.service.StudentService;
import com.kernel360.sisspring.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/grade")
public class GradeController {
    private final StudentService studentService;
    private final SubjectService subjectService;
    private final ScoreService scoreService;
    private final GradeService gradeService;

    @GetMapping
    public String showGradeMain(Model model){
        List<Subject> subjects = subjectService.getAllClasses();
        model.addAttribute("subjects", subjects);
        return "grade-inquiry";
    }

    @PostMapping("/select")
    public String getGradeData(
            @RequestParam Long subjectId,
            Model model
    ){
        String subjectName = subjectService.getSubjectByName(subjectId).getSubjectName();
        model.addAttribute("subjectName", subjectName);
        List<Score> scores = scoreService.getListBySubjectId(subjectId);
        List<GradeDto> gradeDtoList = gradeService.getGradeList(scores);
        model.addAttribute("gradeInfoList", gradeDtoList);


        return "grade-inquiry-result";
    }

}
