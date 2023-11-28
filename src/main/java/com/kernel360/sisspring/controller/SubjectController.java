package com.kernel360.sisspring.controller;



import com.kernel360.sisspring.model.SubjectEnrollDto;
import com.kernel360.sisspring.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping("/subjects")
    public String showSubjectForm(Model model) {
        model.addAttribute("subject", new SubjectEnrollDto());
        return "subject";
    }

    @PostMapping("/subjects")
    public String addSubject(SubjectEnrollDto subject, Model model) {
        subjectService.addSubject(subject);
        model.addAttribute("message", "과목이 성공적으로 등록되었습니다.");
        return "result";
    }
}
