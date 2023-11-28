package com.kernel360.sisspring.service;


import com.kernel360.sisspring.db.Subject;
import com.kernel360.sisspring.db.SubjectRepository;
import com.kernel360.sisspring.model.SubjectEnrollDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public Subject findBySubjectName(String subjectName){
        return subjectRepository.findBySubjectName(subjectName).orElseThrow(
                () -> new RuntimeException("해당 과목이 없습니다: "+subjectName)
        );
    }

    public void addSubject(SubjectEnrollDto subjectDto) {
        subjectRepository.save(toEntity(subjectDto));
    }
    public Subject toEntity(SubjectEnrollDto subjectDto){

        return Subject.builder()
                .subjectName(subjectDto.getSubjectName())
                .subjectId(subjectDto.getSubjectId())
                .gradeType(subjectDto.getGradeType())
                .build();
    }
}
