package com.kernel360.sisspring.controller;

import com.kernel360.sisspring.db.Student;
import com.kernel360.sisspring.db.Subject;
import com.kernel360.sisspring.service.StudentService;
import com.kernel360.sisspring.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EnrollmentController {

    private final StudentService studentService;
    private final SubjectService subjectService;


    @GetMapping("/enroll")
    public String showEnrollmentPage(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "enroll";
    }

    @PostMapping("/enroll")
    public String enrollSubject(@RequestParam("studentId") Long studentId,
                                @RequestParam(value = "subjectId", required = false) Long subjectId,
                                Model model) {

        if (subjectId != null) {
            boolean enrollmentSuccess = studentService.enrollStudentInSubject(studentId, subjectId);

            if (enrollmentSuccess) {
                // 수강신청 성공 시
                model.addAttribute("message", "수강신청이 성공적으로 완료되었습니다.");
                return "enrollment-success"; // 성공 시 보여줄 뷰의 이름을 적절히 변경하세요.
            } else {
                // 수강신청 실패 시
                model.addAttribute("message", "수강신청에 실패했습니다. 이미 수강 중이거나 다른 이유일 수 있습니다.");
                return "enrollment-failure"; // 실패 시 보여줄 뷰의 이름을 적절히 변경하세요.
            }
        }

        Student selectedStudent = studentService.getStudentById(studentId);
//        List<Subject> subjects = subjectService.getSubjectsAvailableForEnrollment(studentId);
        List<Subject> subjects = subjectService.getClasses(studentId);
        model.addAttribute("selectedStudent", selectedStudent);
        model.addAttribute("subjects", subjects);

        return "enroll";
    }
}
