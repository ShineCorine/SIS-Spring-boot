package com.kernel360.sisspring.service;


import com.kernel360.sisspring.db.Score;
import com.kernel360.sisspring.db.Student;
import com.kernel360.sisspring.db.Subject;
import com.kernel360.sisspring.model.GradeDto;
import com.kernel360.sisspring.utils.GradeType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final StudentService studentService;

    public List<GradeDto> getGradeList(List<Score> scores){
        List<GradeDto> gradeDtoList = new ArrayList<>();
        for(Score score: scores){
            gradeDtoList.add(getGradeDtoFromScore(score));
        }
        return gradeDtoList;
    }

    private GradeDto getGradeDtoFromScore(Score score) {
        // 그레이드 산정
        // 학생 필수 과목 여부 확인
        // 과목 타입 확인
        Student student = score.getStudent();
        Subject subject = score.getSubject();

        int gradeType = subject.getGradeType();
        boolean isMjaor = studentService.isMajorSubject(student, subject);

        char evaluatedGrade = getGradeFromPoint(gradeType, isMjaor, score.getPoint());

        return  GradeDto.builder()
                .id(score.getId())
                .studentName(score.getStudent().getStudentName())
                .studentId(score.getStudent().getStudentId())
                .score(score.getPoint())
                .grade(evaluatedGrade)
                .build();
    }

    private char getGradeFromPoint(int gradeType, boolean isMajor, int point) {

        if(gradeType == GradeType.PF.getCode()){
            return getPFGrade(point);
        }

        if(isMajor){
            return  getMajorGrade(point);
        }
        return getGeneralGrade(point);

    }

    private char getPFGrade(int point) {
        if (point >= 70)
            return 'P';
        return 'F';
    }

    private char getGeneralGrade(int point) {
        if(point >=90) return 'A';
        if(point >= 80) return 'B';
        if(point >= 70) return 'C';
        if(point >= 55) return 'D';
        return 'F';
    }

    private char getMajorGrade(int point) {
        if(point >=95) return 's';
        if(point >=90) return 'A';
        if(point >= 80) return 'B';
        if(point >= 70) return 'C';
        if(point >= 60) return 'D';
        return 'F';
    }
}
