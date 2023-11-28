package com.kernel360.sisspring.db;

import com.kernel360.sisspring.utils.Define;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;



@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int subjectId;      // 과목 고유번호
	private String subjectName;  //과목 이름
	private int gradeType;      // 과목 평가 방법 기본은 A,B 방식
	
	//수강 신청한 학생 리스트
	//register() 메서드를 호출하면 리스트에 추가 됨
	@OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
	private List<Student> studentList = new ArrayList<>();
	
	public Subject(String subjectName, int subjectId){
		this.subjectName = subjectName;
		this.subjectId = subjectId;
		this.gradeType = Define.AB_TYPE;   //기본적으로 A, B 타입
	}

	public void register(Student student){  //수강신청
		studentList.add(student);
		student.setSubject(this);
	}

}
