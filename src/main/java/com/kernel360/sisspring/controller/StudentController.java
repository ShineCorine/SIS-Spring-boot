package com.kernel360.sisspring.controller;

import com.kernel360.sisspring.model.StudentRegisterDto;
import com.kernel360.sisspring.model.SubjectEnrollDto;
import com.kernel360.sisspring.service.MajorService;
import com.kernel360.sisspring.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final MajorService majorService;

    @GetMapping
    public String showSubjectForm(Model model) {
        model.addAttribute("student", new SubjectEnrollDto());
        model.addAttribute("majors", majorService.getMajorList());
        return "student";
    }

    @PostMapping
    public String addStudent(
            @ModelAttribute("student") StudentRegisterDto student, Model model) {
        studentService.enrollStudent(student);
        model.addAttribute("message", "학생이  성공적으로 등록되었습니다.");
        return "student-enroll-result";
    }
}
