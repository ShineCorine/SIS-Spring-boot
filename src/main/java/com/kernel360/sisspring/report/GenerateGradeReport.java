package com.kernel360.sisspring.report;

import com.kernel360.sisspring.db.*;
import com.kernel360.sisspring.service.*;
import com.kernel360.sisspring.utils.Define;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class GenerateGradeReport {

	School school = School.getInstance();
	public static final String TITLE = " 수강생 학점 \t\t\n";
	public static final String HEADER = " 이름  |  학번  |중점과목| 점수   \n";
	public static final String LINE = "-------------------------------------\n";
	private StringBuilder buffer = new StringBuilder();

	
	public String getReport(){
		List<Subject> subjectList = school.getSubjectList();  // 모든 과목에 대한 학점 산출
		for( Subject subject : subjectList) {
			makeHeader(subject);
//			makeBody(subject);
			makeFooter();
		}
		return buffer.toString();  // String 으로 반환
	}
	
	public void makeHeader(Subject subject){
		buffer.append(GenerateGradeReport.LINE);
		buffer.append("\t" + subject.getSubjectName());
		buffer.append(GenerateGradeReport.TITLE );
		buffer.append(GenerateGradeReport.HEADER );
		buffer.append(GenerateGradeReport.LINE);
	} 
	
//	public void makeBody(Subject subject){
//
//		List<Student> studentList = subject.getStudentList();  // 각 과목의 학생들
//
//		for(int i=0; i<studentList.size(); i++){
//			Student student = studentList.get(i);
//			buffer.append(student.getStudentName());
//			buffer.append(" | ");
//			buffer.append(student.getStudentId());
//			buffer.append(" | ");
//			buffer.append(student.getMajorSubject().getSubjectName() + "\t");
//			buffer.append(" | ");
//
//			getScoreGrade(student, subject);  //학생별 해당과목 학점 계산
//			buffer.append("\n");
//			buffer.append(LINE);
//		}
//	}
//
	public void getScoreGrade(Student student, Subject subject){
		
		List<Score> scoreList = student.getScoreList();
		int majorId = student.getMajorSubject().getSubjectId();

		
		for(int i=0; i<scoreList.size(); i++){  // 학생이 가진 점수들 
			
			Score score = scoreList.get(i);
			if(score.getSubject().getSubjectId() == subject.getSubjectId()) {  // 현재 학점을 산출할 과목

				evaluateGradeByScoreAndStrategy(subject, score, majorId);
			}
		}
	}
	
	public void evaluateGradeByScoreAndStrategy( Subject subject, Score score, int majorId) {
		
		GradeEvaluation[] gradeEvaluation = {new BasicEvaluation(), new MajorEvaluation(), new PassFailEvaluation()};
		String grade;
		
		if( subject.getGradeType() == Define.PF_TYPE) {
			grade = gradeEvaluation[Define.PF_TYPE].getGrade(score.getPoint());
		}
		else {
			if(score.getSubject().getSubjectId() == majorId)  // 중점 과목인 경우
				grade = gradeEvaluation[Define.SAB_TYPE].getGrade(score.getPoint());  //중점 과목 학점 평가 방법
			else
				grade = gradeEvaluation[Define.AB_TYPE].getGrade(score.getPoint()); // 중점 과목이 아닌 경우
		}
		
		buffer.append(score.getPoint());
		buffer.append(":");
		buffer.append(grade);
		buffer.append(" | ");
		
	}
	
	public void makeFooter(){
		buffer.append("\n");
	}
}