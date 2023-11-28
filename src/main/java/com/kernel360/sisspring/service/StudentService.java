package com.kernel360.sisspring.service;


import com.kernel360.sisspring.db.Student;
import com.kernel360.sisspring.db.StudentRepository;
import com.kernel360.sisspring.db.Subject;
import com.kernel360.sisspring.model.StudentRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final SubjectService subjectService;
    private final MajorService majorService;

    public void enrollStudent(StudentRegisterDto student){
        studentRepository.save(toEntity(student));
    }

    private Student toEntity(StudentRegisterDto dto){

        String focusSubjectName = majorService.getFocusSubjectName(dto.getMajor());
        Subject majorSubject = subjectService.findBySubjectName(focusSubjectName);

        return Student.builder()
                .studentId(dto.getStudentId())
                .studentName(dto.getStudentName())
                .subject(majorSubject)
                .build();
    }

}

