package com.kernel360.sisspring.service;


import com.kernel360.sisspring.db.*;
import com.kernel360.sisspring.model.StudentRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final SubjectService subjectService;
    private final MajorService majorService;
    private final ScoreRepository scoreRepository;

    public void enrollStudent(StudentRegisterDto student){
        studentRepository.save(toEntity(student));
    }

    private Student toEntity(StudentRegisterDto dto){

        String focusSubjectName = majorService.getFocusSubjectName(dto.getMajor());
        Subject majorSubject = subjectService.findBySubjectName(focusSubjectName);

        return Student.builder()
                .studentId(dto.getStudentId())
                .studentName(dto.getStudentName())
                .majorSubject(majorSubject)
                .build();
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(()-> new RuntimeException("해당 학생이 없습니다:" + studentId));
    }

    public boolean enrollStudentInSubject(Long studentId, Long subjectId) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new RuntimeException("잘못된 학생 아이디입니다: "+ studentId)
        );
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(
                () -> new RuntimeException("잘못된 과목 아이디입니다: "+ subjectId)
        );

        // 이미 수강 중이거나 수강 가능한 상태인지 확인
        if (!student.getSubjects().contains(subject)) {
            // 수강 가능한 상태이면 수강 처리
            student.enrollInSubject(subject);

            // 성적 관련 로직 추가: 학생과 과목 간의 성적을 초기화하고 저장
            Score score = new Score(student, subject);
            scoreRepository.save(score);

//            studentRepository.save(student);
            return true;
        }

        // 수강 실패
        return false;
    }
}

