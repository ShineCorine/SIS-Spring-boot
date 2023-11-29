package com.kernel360.sisspring.service;


import com.kernel360.sisspring.db.*;
import com.kernel360.sisspring.model.SubjectEnrollDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;
    private final ScoreRepository scoreRepository;

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

    public List<Subject> getSubjectsAvailableForEnrollment(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new RuntimeException("해당하는 학생이 없습니다: "+studentId));

        List<Score> scoresEnrolledByStudent = student.getScoreList();

        // 현재 학생이 수강 중인 과목의 ID 목록을 가져옴
        List<Long> enrolledSubjectIds = scoresEnrolledByStudent.stream()
                .map(score -> score.getSubject().getId())
                .toList();

        return subjectRepository.findByIdNotIn(enrolledSubjectIds);

    }

    public List<Subject> getClasses(Long studentId) {
        List<Long> enrolledSubjectIdList = scoreRepository.findAllByStudentId(studentId).stream()
                .map(score -> score.getSubject().getId())
                .toList();
        List<Long> entireSubjects = subjectRepository.findAll().stream().map(
                Subject::getId
        ).toList();

        // Filter out the subjects that are in enrolledSubjectIdList
        List<Long> remainingSubjectIds = entireSubjects.stream()
                .filter(subjectId -> !enrolledSubjectIdList.contains(subjectId))
                .toList();

        // Use the remainingSubjectIds to fetch the corresponding subjects from the subjectRepository
        return subjectRepository.findAllById(remainingSubjectIds);
    }
}
